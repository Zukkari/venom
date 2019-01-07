package com.github.zukkari.serialization.manifest

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.serialization.manifest.fields.AgentClass
import com.github.zukkari.serialization.manifest.fields.CanRedefineClass
import com.github.zukkari.serialization.manifest.fields.CanRetransformClasses
import com.github.zukkari.serialization.manifest.fields.CanSetNativeMethodPrefix
import java.util.jar.Attributes
import java.util.jar.Manifest

object ManifestFactory {
    private const val MANIFEST_VERSION: String = "1.0"

    private val manifestFields: List<ManifestField> = listOf(
        AgentClass,
        CanRedefineClass,
        CanRetransformClasses,
        CanSetNativeMethodPrefix
    )

    fun create(config: AgentConfiguration<*>): Manifest {
        val manifest = Manifest()
        manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = MANIFEST_VERSION

        manifestFields.forEach { field ->
            manifest.mainAttributes[field.name] = field.value(config)
        }

        return manifest
    }

}