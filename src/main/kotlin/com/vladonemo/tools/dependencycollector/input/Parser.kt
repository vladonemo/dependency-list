package com.vladonemo.tools.dependencycollector.input

import java.io.File

interface Parser {
    fun parse(file: File): List<Dependency>?
}
