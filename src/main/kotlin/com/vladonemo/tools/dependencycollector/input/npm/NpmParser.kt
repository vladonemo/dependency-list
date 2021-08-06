package com.vladonemo.tools.dependencycollector.input.npm

import com.beust.klaxon.Klaxon
import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.input.Parser
import java.io.File

class NpmParser : Parser {
    override fun parse(file: File): List<Dependency>? {
        return parseDependencies(Klaxon().parse<NpmLs>(file)?.dependencies)
    }

    private fun parseDependencies(dependencyMap: Map<String, NpmDependency>?): List<Dependency>? {
        return dependencyMap?.map {
            Dependency().apply {
                name = it.key
                version = it.value.required?.version ?: it.value.version
                link = it.value.required?._resolved ?: it.value.resolved
                dependencies = parseDependencies(it.value.dependencies)
            }
        }
    }
}
