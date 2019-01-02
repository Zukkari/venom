package com.github.zukkari.loaders

import arrow.core.Either
import java.io.InputStream

interface Loader {
    fun load(): Either<LoadException, InputStream>
}

sealed class LoadException {
    object ResourceNotFound : LoadException()
}

typealias ResourceNotFound = LoadException.ResourceNotFound

