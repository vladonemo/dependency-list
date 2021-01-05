package com.vladonemo.tools.dependencycollector.input.maven

import com.paypal.digraph.parser.GraphParser
import com.vladonemo.tools.dependencycollector.input.Dependency
import com.vladonemo.tools.dependencycollector.input.Parser
import java.io.File
import java.io.FileInputStream


class MavenParser(settings: Map<String, String>?) : Parser {
    private val scope: String = settings?.getValue("scope")!!

    override fun parse(file: File): List<Dependency>? {
        FileInputStream(file).use { stream ->
            val parser = GraphParser(stream)
            return parser.edges.filter { it.value.node1.id == parser.graphId }
                    .map { MavenDependency fromString it.value.node2.id }
                    .filter { it.scope == scope }
        }
    }
}
