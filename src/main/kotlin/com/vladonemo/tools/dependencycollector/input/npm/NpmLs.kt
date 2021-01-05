package com.vladonemo.tools.dependencycollector.input.npm

data class NpmLs(
    var dependencies: Map<String, NpmDependency>
)
