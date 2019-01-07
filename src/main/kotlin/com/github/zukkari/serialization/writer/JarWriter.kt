package com.github.zukkari.serialization.writer

import arrow.core.Either
import arrow.core.Right
import arrow.core.fix
import arrow.instances.either.monad.monad
import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.loaders.implementation.FileSystemLoader
import java.io.File
import java.io.FileOutputStream
import java.util.jar.JarOutputStream
import java.util.jar.Manifest

class JarWriter(
    private val configuration: AgentConfiguration<*>,
    private val manifest: Manifest
) {

    fun write(): Either<Throwable, Unit> {
        return Either.monad<Throwable>()
            .binding {
                val classInputStream = FileSystemLoader(configuration.agentClass).load().fix()
                val outFile = FileCreator.createFile(configuration.agentClass.simpleName + JAR_EXTENSION).fix()
                val jarStream = jarStream(outFile)


            }.fix()
    }

    private fun jarStream(outFile: Either<Throwable, File>): Either<Throwable, JarOutputStream> = when (outFile) {
        is Either.Left -> outFile
        is Either.Right -> {
            val fName = outFile.b.absolutePath
            Right(JarOutputStream(FileOutputStream(fName)))
        }
    }


    companion object {
        const val JAR_EXTENSION = ".jar"
        const val MANIFEST = "MANIFEST.MF"
        const val META_INF = "META_INF/"
    }
}