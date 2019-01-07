package com.github.zukkari.serialization

import java.io.File

internal object PathFormer {

    fun form(agentClass: Class<*>): String = "${agentClass.canonicalName.replace(".", File.separator)}.class"

    fun getSubDirectories(agentClass: Class<*>): List<String> {
        tailrec fun getPaths(split: List<String>, acc: List<String>, result: List<String>): List<String> {
            return if (split.isEmpty()) {
                result
            } else {
                val curr = split[0]
                val newPath = if (acc.isEmpty()) curr else acc.joinToString(separator = File.separator) + File.separator + curr
                val addedEnd = if (split.size > 1) newPath + File.separator else newPath

                getPaths(split.subList(1, split.size), acc + curr, result + addedEnd)
            }
        }

        val formed = form(agentClass)
        val split = formed.split(File.separator)

        return getPaths(split, listOf(), listOf())
    }
}