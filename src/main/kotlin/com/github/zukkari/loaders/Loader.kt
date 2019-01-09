package com.github.zukkari.loaders

import arrow.core.Either
import java.io.InputStream

interface Loader {
    fun load(): Either<LoadException, InputStream>
}

sealed class LoadException : Exception() {
    object ResourceNotFound : LoadException()
}

typealias ResourceNotFound = LoadException.ResourceNotFound

