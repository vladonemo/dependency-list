package com.vladonemo.tools.dependencycollector.input.cycloneDx

data class CycloneDxRequireDependency(
    val type: String = "",
    val url: String = "",
    val comment: String = ""
)
