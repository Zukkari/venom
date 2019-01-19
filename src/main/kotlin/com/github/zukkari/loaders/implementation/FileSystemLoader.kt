package com.github.zukkari.loaders.implementation

import arrow.core.*
import arrow.instances.option.monad.monad
import com.github.zukkari.loaders.LoadException
import com.github.zukkari.loaders.Loader
import com.github.zukkari.loaders.ResourceNotFound
import com.github.zukkari.serialization.PathFormer
import java.io.FileInputStream
import java.io.InputStream

class FileSystemLoader(private val agentClass: Class<*>) : Loader {

    override fun load(): Either<LoadException, InputStream> {
        val f = Option.monad()
            .binding {
                val formed = PathFormer.form(agentClass)
                val resource = ClassLoader.getSystemClassLoader().getResource(formed)
                val path = Option.fromNullable(resource).bind()
                FileInputStream(path.file)
            }.fix()


        return f.toEither { ResourceNotFound }
    }

}