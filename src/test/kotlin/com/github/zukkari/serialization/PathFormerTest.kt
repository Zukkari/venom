package com.github.zukkari.serialization

import com.github.zukkari.loaders.implementation.FileSystemLoader
import org.junit.Test
import org.junit.jupiter.api.TestInstance
import java.io.File
import kotlin.test.assertEquals


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PathFormerTest {

    @Test
    fun `Path is correctly formed for class`() {
        val path = PathFormer.form(FileSystemLoader::class.java)

        assertEquals(
            "com${File.separator}github${File.separator}zukkari${File.separator}loaders${File.separator}implementation${File.separator}FileSystemLoader.class",
            path,
            "Path should be correctly formed!"
        )
    }

    @Test
    fun `Subdirectories get correctly formed`() {
        val path = PathFormer.getSubDirectories(PathFormerTest::class.java)

        assertEquals(
            listOf(
                "com${File.separator}",
                "com${File.separator}github${File.separator}",
                "com${File.separator}github${File.separator}zukkari${File.separator}",
                "com${File.separator}github${File.separator}zukkari${File.separator}serialization${File.separator}",
                "com${File.separator}github${File.separator}zukkari${File.separator}serialization${File.separator}PathFormerTest.class"
                ), path
        )
    }

}