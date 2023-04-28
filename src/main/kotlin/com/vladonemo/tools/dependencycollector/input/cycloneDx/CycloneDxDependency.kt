package com.vladonemo.tools.dependencycollector.input.cycloneDx

data class CycloneDxDependency(
    val name: String = "",
    val version: String = "",
    val purl: String = "",
    val externalReferences: List<CycloneDxRequireDependency>? = null
)
