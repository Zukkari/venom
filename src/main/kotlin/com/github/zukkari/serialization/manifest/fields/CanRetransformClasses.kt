package com.github.zukkari.serialization.manifest.fields

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.serialization.manifest.ManifestField

object CanRetransformClasses : ManifestField {
    override val name: String = "Can-Retransform-Classes"

    override fun value(configuration: AgentConfiguration<*>): String = configuration.canRetransformClasses.toString()
}