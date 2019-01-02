package com.github.zukkari.serialization

import java.io.File

internal object PathFormer {

    fun form(agentClass: Class<*>): String = "${agentClass.canonicalName.replace(".", File.separator)}.class"
}