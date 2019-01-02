package com.github.zukkari.loaders.implementation

import arrow.core.*
import arrow.instances.option.monad.monad
import com.github.zukkari.loaders.Loader
import com.github.zukkari.loaders.LoadException
import com.github.zukkari.loaders.ResourceNotFound
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class FileSystemLoader(private val agentClass: Class<*>) : Loader {

    override fun load(): Either<LoadException, InputStream> {
        val f = Option.monad()
            .binding {
                val resource = ClassLoader.getSystemClassLoader().getResource(path())
                val path = Option.fromNullable(resource).bind()
                FileInputStream(path.file)
            }.fix()


        return when (f) {
            is Some -> Right(f.t)
            is None -> Left(ResourceNotFound)
        }
    }


    private fun path(): String {
        val pkg = agentClass.`package`.name
        val name = "${agentClass.name}.class"

        val folderPath = pkg.replace(".", File.separator)

        return "$folderPath${File.separator}$name"
    }
}