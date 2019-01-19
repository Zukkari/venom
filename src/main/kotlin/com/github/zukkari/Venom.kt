package com.github.zukkari

import arrow.core.*
import arrow.instances.either.monad.monad
import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.injection.DeletionHook
import com.github.zukkari.injection.VM
import com.github.zukkari.serialization.manifest.ManifestFactory
import com.github.zukkari.serialization.writer.JarWriter

object Venom {

    inline fun <reified T> inject(): Either<Throwable, Unit> {
        val config = AgentConfiguration.new<T>()
        return attachWithConfiguration(config)
    }


    fun inject(config: AgentConfiguration<*>): Either<Throwable, Unit> = attachWithConfiguration(config)

    fun attachWithConfiguration(config: AgentConfiguration<*>): Either<Throwable, Unit> {
        val manifest = ManifestFactory.create(config)
        val writer = JarWriter(config, manifest)

        return Either.monad<Throwable>().binding {
            val jarName = writer.write().bind()
            VM.attach(jarPath = jarName).bind()
        }.fix()
    }
}