package com.github.zukkari.loaders.implementation

import arrow.core.Either
import com.github.zukkari.serialization.PathFormer
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.TestInstance
import java.io.InputStream
import java.util.*
import kotlin.test.Test

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class FileSystemLoaderTest {

    private val loader: FileSystemLoader = FileSystemLoader(FileSystemLoader::class.java)

    @Test
    fun `File is found with well formed path`() {
        val content = loader.load()

        assert(content is Either.Right<InputStream>)
    }

    @org.junit.Test
    fun `File is not found with not well formed path`() {
        mockkObject(PathFormer)
        every { PathFormer.form(FileSystemLoaderTest::class.java) } returns UUID.randomUUID().toString()

        val content = FileSystemLoader(FileSystemLoaderTest::class.java).load()
        assert(content is Either.Left)

        clearAllMocks()
    }
}