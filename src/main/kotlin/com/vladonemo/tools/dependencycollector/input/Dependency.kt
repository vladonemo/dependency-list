package com.vladonemo.tools.dependencycollector.input

open class Dependency {
    var name: String = ""
    var version: String = ""
    var link: String = ""
    var dependencies: List<Dependency>? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dependency

        if (name != other.name) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + version.hashCode()
        return result
    }
}
