package com.vladonemo.tools.dependencycollector.output

import com.vladonemo.tools.dependencycollector.Group
import java.io.File
import java.io.PrintWriter

abstract class OutputWriter(private val file: File) {
    fun write(data: List<Group>) {
        file.printWriter().use {
            write(it, data)
            it.flush()
        }
    }

    abstract fun write(writer: PrintWriter, data: List<Group>)
}
