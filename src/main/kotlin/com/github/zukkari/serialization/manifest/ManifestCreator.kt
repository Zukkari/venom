package com.github.zukkari.serialization.manifest

import com.github.zukkari.injection.AgentConfiguration
import java.util.jar.Attributes
import java.util.jar.Manifest

class ManifestCreator(private val config: AgentConfiguration<*>) {

    fun create(): Manifest {
        val manifest = Manifest()
        manifest.mainAttributes[Attributes.Name.MANIFEST_VERSION] = MANIFEST_VERSION
        manifest.mainAttributes["Agent-Class"] = ""

        return manifest
    }

    companion object {
        const val MANIFEST_VERSION: String = "1.0"
    }
}