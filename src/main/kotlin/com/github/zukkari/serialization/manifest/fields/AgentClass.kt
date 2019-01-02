package com.github.zukkari.serialization.manifest.fields

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.serialization.manifest.ManifestField

object AgentClass : ManifestField {
    override val name: String = "Agent-Class"

    override fun value(configuration: AgentConfiguration<*>): String = configuration.agentClass.canonicalName
}