package com.vladonemo.tools.dependencycollector.input.config

data class GroupConfig(val group: String,
                       val files: List<FileConfig>,
                       val filter: FilterConfig? = null)
