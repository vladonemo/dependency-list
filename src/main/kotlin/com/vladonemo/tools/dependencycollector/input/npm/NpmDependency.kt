package com.vladonemo.tools.dependencycollector.input.npm

data class NpmDependency(val version: String = "",
                         val from: String = "",
                         var resolved: String = "",
                         var peerMissing: Boolean = false
)
