package com.vladonemo.tools.dependencycollector.output.markdown

import com.vladonemo.tools.dependencycollector.Group
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
            it.deps.forEach { dep -> writer.println("|${dep.name}|${dep.version}|${dep.link}|") }
            writer.println()
        }
    }
}
