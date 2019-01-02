package com.github.zukkari.serialization

import com.github.zukkari.loaders.implementation.FileSystemLoader
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PathFormerTest {

    @Test
    fun `Path is correctly formed for class`() {
        val path = PathFormer.form(FileSystemLoader::class.java)

        assertEquals(
            "com/github/zukkari/loaders/implementation/FileSystemLoader.class",
            path,
            "Path should be correctly formed!"
        )
    }

}