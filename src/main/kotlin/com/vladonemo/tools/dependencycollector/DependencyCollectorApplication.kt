package com.vladonemo.tools.dependencycollector

import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.input.Parser
import com.vladonemo.tools.dependencycollector.input.cycloneDx.CycloneDxParser
import com.vladonemo.tools.dependencycollector.input.config.FileConfig
import com.vladonemo.tools.dependencycollector.input.config.FilterConfig
import com.vladonemo.tools.dependencycollector.input.config.InputConfigParser
import com.vladonemo.tools.dependencycollector.input.config.OutputConfig
import com.vladonemo.tools.dependencycollector.input.maven.MavenParser
import com.vladonemo.tools.dependencycollector.input.npm.NpmParser
import com.vladonemo.tools.dependencycollector.output.OutputWriter
import com.vladonemo.tools.dependencycollector.output.markdown.MarkdownWriter
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File

@SpringBootApplication
class DependencyCollectorApplication : CommandLineRunner {

    private fun getParser(file: FileConfig): Parser {
        return when (file.type) {
            "npm" -> NpmParser()
            "cycloneDx" -> CycloneDxParser()
            "maven" -> MavenParser(file.settings)
            else -> throw Throwable("File type unknown. Supported are only 'npm' and 'maven'")
        }
    }

    override fun run(vararg args: String?) {
        val input = args[0]?.let { InputConfigParser().parse(it) }
            ?: throw Throwable("Input config file not specified")
        val parent = File(args[0]!!).parent
        input.toProcess
            .map { toProcess ->
                Group(toProcess.group,
                    toProcess.files
                        .map { getParser(it).parse(File(parent, it.path)) }
                        .flatMap { it.orEmpty() }
                        .distinct()
                        .filter { apply(toProcess.filter, it) }
                        .toSortedSet(compareBy(Dependency::name, Dependency::version))
                )
            }
            .let { getOutputWriter(input.output, parent).write(it) }
    }

    private fun apply(filter: FilterConfig?, dep: Dependency): Boolean {
        return filter?.let { !it.exclude.contains(dep.name) } ?: true
    }

    private fun getOutputWriter(output: OutputConfig?, parent: String): OutputWriter {
        return output?.let {
            val file = File(parent, it.file)
            when (it.type) {
                "markdown" -> MarkdownWriter(file)
                else -> throw Throwable("Don't know the ${it.type} type.")
            }
        } ?: throw Throwable("Output needs to be provided")
    }
}

fun main(args: Array<String>) {
    runApplication<DependencyCollectorApplication>(*args)
}
