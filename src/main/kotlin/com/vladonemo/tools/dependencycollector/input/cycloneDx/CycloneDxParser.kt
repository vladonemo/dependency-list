package com.vladonemo.tools.dependencycollector.input.cycloneDx

import com.beust.klaxon.Klaxon
import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.input.Parser
import java.io.File

class CycloneDxParser : Parser {
    override fun parse(file: File): List<Dependency>? {
        return parseDependencies(Klaxon().parse<CycloneDxLs>(file)?.components)
    }

    private fun parseDependencies(compoments: ArrayList<CycloneDxDependency>?): List<Dependency>? {
        return compoments?.map {
            Dependency().apply {
                name = it.purl
                version = it.version
                link = it.externalReferences?.find { i-> i.comment == "As set via `support.source` in composer package definition." }?.url ?: ""
            }
        }
    }
}
