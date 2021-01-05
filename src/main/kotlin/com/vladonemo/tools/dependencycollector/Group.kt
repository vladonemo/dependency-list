package com.vladonemo.tools.dependencycollector

import com.vladonemo.tools.dependencycollector.input.Dependency
import java.util.*

data class Group(val name: String,
                 val deps: SortedSet<Dependency>)
