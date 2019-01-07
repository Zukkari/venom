package com.github.zukkari.serialization.writer

import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import java.io.File
import java.lang.Exception

object FileCreator {

    fun createFile(name: String): Either<FileException, File> {
        val file = File(name)

        return when {
            file.exists() -> Left(ExistsException(name))
            !file.canWrite() -> Left(NoWriteAccess)
            else -> Right(file)
        }
    }
}

sealed class FileException : Exception()

data class ExistsException(val name: String) : FileException()

object NoWriteAccess : FileException()