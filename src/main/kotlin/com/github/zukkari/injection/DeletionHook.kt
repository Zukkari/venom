package com.github.zukkari.injection

import arrow.core.Try
import java.nio.file.Files
import java.nio.file.Paths

class DeletionHook(private val jarName: String) : Runnable {

    override fun run() {
        Try {
            Files.delete(Paths.get(jarName))
        }
    }

    fun attach() {
        Runtime.getRuntime().addShutdownHook(Thread(this))
    }
}