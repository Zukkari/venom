package com.github.zukkari.serialization.manifest

import com.github.zukkari.injection.AgentConfiguration

interface ManifestField {
    val name: String

    fun value(configuration: AgentConfiguration<*>): String
}