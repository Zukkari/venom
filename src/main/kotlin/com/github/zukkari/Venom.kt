package com.github.zukkari

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.injection.InjectionResult
import com.github.zukkari.loaders.implementation.FileSystemLoader

object Venom {

    fun inject(agent: Class<*>): InjectionResult {
        val content = FileSystemLoader(agent).load()

        TODO()
    }

    fun inject(config: AgentConfiguration<*>): InjectionResult = TODO()
}