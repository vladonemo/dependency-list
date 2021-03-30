package com.vladonemo.tools.dependencycollector.input.npm

data class NpmDependency(val version: String = "",
                         val required: NpmRequiredDependency? = null,
                         val from: String = "",
                         var resolved: String = "",
                         var peerMissing: Boolean = false
)
