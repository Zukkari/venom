package com.github.zukkari.loaders.implementation

import arrow.core.Either
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkConstructor
import org.junit.jupiter.api.TestInstance
import java.io.InputStream
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FileSystemLoaderTest {

    private val loader: FileSystemLoader = FileSystemLoader(FileSystemLoader::class.java)

    @Test
    fun `Path is correctly formed for class`() {
        val path = loader.path()

        assertEquals(
            "com/github/zukkari/loaders/implementation/FileSystemLoader.class",
            path,
            "Path should be correctly formed!"
        )
    }

    @Test
    fun `File is found with well formed path`() {
        val content = loader.load()

        assert(content is Either.Right<InputStream>)
    }

    @Test
    fun `File is not found with not well formed path`() {
        mockkConstructor(FileSystemLoader::class)
        every { anyConstructed<FileSystemLoader>().path() } returns UUID.randomUUID().toString()

        val content = FileSystemLoader(this::class.java).load()
        assert(content is Either.Left)

        clearAllMocks()
    }
}