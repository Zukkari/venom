package com.github.zukkari.serialization.writer

import arrow.core.Either
import arrow.core.fix
import arrow.instances.either.monad.monad
import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.loaders.implementation.FileSystemLoader
import com.github.zukkari.serialization.PathFormer
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.jar.JarOutputStream
import java.util.jar.Manifest
import java.util.zip.ZipEntry

class JarWriter(
    private val configuration: AgentConfiguration<*>,
    private val manifest: Manifest
) {

    fun write(): Either<Throwable, String> {
        return Either.monad<Throwable>()
            .binding {
                val classInputStream = FileSystemLoader(configuration.agentClass).load().bind()
                val outFile = FileCreator.createFile(configuration.agentClass.simpleName + JAR_EXTENSION).bind()
                val jarStream = jarStream(outFile)

                writeJar(jarStream, classInputStream)

                Either.Right(outFile.absolutePath).bind()
            }.fix()
    }

    private fun jarStream(outFile: File): JarOutputStream {
            val fName = outFile.absolutePath
            return JarOutputStream(FileOutputStream(fName), manifest)
    }

    private fun writeJar(jarOutputStream: JarOutputStream, classInputStream: InputStream) {
        val paths = PathFormer.getSubDirectories(configuration.agentClass)
        paths.forEach { jarOutputStream.putNextEntry(ZipEntry(it)) }

        copyClass(jarOutputStream, classInputStream)
    }

    private fun copyClass(jarOutputStream: JarOutputStream, classInputStream: InputStream) {
        val buffSize = 2048
        val buffer = ByteArray(buffSize)
        var read = classInputStream.read(buffer)
        while (read != -1) {
            jarOutputStream.write(buffer, 0, read)
            read = classInputStream.read(buffer)
        }
    }


    companion object {
        const val JAR_EXTENSION = ".jar"
    }
}