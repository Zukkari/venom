package com.github.zukkari.injection

class AgentConfiguration<T>(val agentClass: Class<T>) {
    var canRedefineClasses: Boolean = false
    var canRetransformClasses: Boolean = false
    var canSetNativeMetodPrefix: Boolean = false

    inline fun <reified T> new(): AgentConfiguration<T> = AgentConfiguration(T::class.java)
}