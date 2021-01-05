package com.vladonemo.tools.dependencycollector.input.config

data class InputConfig(val output: OutputConfig,
                       val toProcess: List<GroupConfig>)
