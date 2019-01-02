package com.github.zukkari.serialization.manifest.fields

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.serialization.manifest.ManifestField

object CanRedefineClass : ManifestField {

    override val name: String = "Can-Redefine-Class"

    override fun value(configuration: AgentConfiguration<*>): String = configuration.canRedefineClasses.toString()
}