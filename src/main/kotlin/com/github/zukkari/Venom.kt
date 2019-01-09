package com.github.zukkari

import arrow.core.Either
import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.injection.InjectionResult
import com.github.zukkari.loaders.implementation.FileSystemLoader
import com.github.zukkari.serialization.manifest.ManifestFactory
import com.github.zukkari.serialization.writer.JarWriter

object Venom {

    inline fun <reified T> inject(): Either<Throwable, Unit> {
        val config = AgentConfiguration.new<T>()
        val manifest = ManifestFactory.create(config)
        val writer = JarWriter(config, manifest)

        return writer.write()
    }

    fun inject(config: AgentConfiguration<*>): Either<Throwable, Unit> = TODO()
}