package com.vladonemo.tools.dependencycollector.input.config

data class FileConfig(val path: String,
                      val type: String,
                      val settings: Map<String, String>? = null)
