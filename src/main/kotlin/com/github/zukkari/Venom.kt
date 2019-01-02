package com.github.zukkari

import com.github.zukkari.injection.AgentConfiguration
import com.github.zukkari.injection.InjectionResult

object Venom {

    fun <T> inject(agent: Class<T>): InjectionResult = TODO()

    fun <T> inject(config: AgentConfiguration<T>): InjectionResult = TODO()
}