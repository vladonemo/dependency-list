package com.vladonemo.tools.dependencycollector.input.config

import com.beust.klaxon.Klaxon
import java.io.File

class InputConfigParser {
    fun parse(file: String): InputConfig? {
        return Klaxon().parse<InputConfig>(File(file))
    }
}
