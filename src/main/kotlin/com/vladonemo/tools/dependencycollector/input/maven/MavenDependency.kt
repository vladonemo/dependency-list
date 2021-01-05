package com.vladonemo.tools.dependencycollector.input.maven

import com.vladonemo.tools.dependencycollector.input.Dependency

class MavenDependency() : Dependency() {

    lateinit var scope: String

    companion object {
        infix fun fromString(id: String): MavenDependency {
            val splits = id.trimStart('"').trimEnd('"').split(':')
            return MavenDependency().apply {
                name = "${splits[0]}:${splits[1]}"
                version = splits[3]
                scope = splits[4]
            }
        }
    }
}


