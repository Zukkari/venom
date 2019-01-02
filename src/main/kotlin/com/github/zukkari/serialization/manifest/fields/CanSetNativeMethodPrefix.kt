package com.github.zukkari.serialization.manifest.fields

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.serialization.manifest.ManifestField

object CanSetNativeMethodPrefix : ManifestField {
    override val name: String = "Can-Set-Native-Method-Prefix"

    override fun value(configuration: AgentConfiguration<*>): String = configuration.canSetNativeMethodPrefix.toString()
}