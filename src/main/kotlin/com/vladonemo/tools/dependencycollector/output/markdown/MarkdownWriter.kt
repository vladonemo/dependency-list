package com.vladonemo.tools.dependencycollector.output.markdown

import com.vladonemo.tools.dependencycollector.Group
import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.output.OutputWriter
import java.io.File
import java.io.PrintWriter

class MarkdownWriter(file: File): OutputWriter(file) {
    override fun write(writer: PrintWriter, data: List<Group>) {
        data.forEach {
            writer.println("# ${it.name}")
            writer.println()
            writer.println("|Name|Version|Link|")
            writer.println("|---|---|---|")
            it.deps.forEach { dep ->
                run {
                    writer.println("|${dep.name}|${dep.version}|${dep.link}|")
                    writeInnerDependency(writer, dep)
                }
            }
            writer.println()
        }
    }

    private fun writeInnerDependency(writer: PrintWriter, dependency: Dependency, depth: Int = 1) {
        dependency.dependencies?.forEach { dep ->
            run {
                writer.println("|${generateTabs(depth)}└─ ${dep.name}|${dep.version}|${dep.link}|")
                writeInnerDependency(writer, dep, depth + 1)
            }
        }
    }

    private fun generateTabs(depth: Int): String {
        var output = ""
        repeat(depth) { output += "&emsp;"; }

        return output
    }
}
