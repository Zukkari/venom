package com.github.zukkari.injection

class AgentConfiguration<T>(val agentClass: Class<T>) {
    var canRedefineClasses: Boolean = false
    var canRetransformClasses: Boolean = false
    var canSetNativeMethodPrefix: Boolean = false

    companion object {
        inline fun <reified T> new(configurator: AgentConfiguration<T>.() -> Unit): AgentConfiguration<T> {
            val cfg = AgentConfiguration(T::class.java)
            configurator(cfg)
            return cfg
        }

        inline fun <reified T> new(): AgentConfiguration<T> = AgentConfiguration(T::class.java)
    }
}