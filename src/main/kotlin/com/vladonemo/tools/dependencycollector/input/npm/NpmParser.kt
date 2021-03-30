package com.vladonemo.tools.dependencycollector.input.npm

import com.beust.klaxon.Klaxon
import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.input.Parser
import java.io.File

class NpmParser : Parser {
    override fun parse(file: File): List<Dependency>? {
        return Klaxon().parse<NpmLs>(file)?.dependencies
                ?.map {
                    Dependency().apply {
                        name = it.key
                        version = it.value.required?.version ?: it.value.version
                        link = it.value.required?._resolved ?: it.value.resolved
                    }
                }
    }
}
