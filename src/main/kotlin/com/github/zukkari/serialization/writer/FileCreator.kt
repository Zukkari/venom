package com.github.zukkari.serialization.writer

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import java.io.File

object FileCreator {

    fun createFile(name: String): Either<FileException, File> {
        val file = File(name)

        return when {
            file.exists() -> ExistsException(name).left()
            file.exists() && !file.canWrite() -> NoWriteAccess.left()
            else -> file.right()
        }
    }
}

sealed class FileException : Exception()

data class ExistsException(val name: String) : FileException()

object NoWriteAccess : FileException()