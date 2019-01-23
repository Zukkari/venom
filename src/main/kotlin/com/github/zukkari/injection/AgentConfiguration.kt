package com.github.zukkari.injection

class AgentConfiguration<T>(
    val agentClass: Class<T>,
    val canRedefineClasses: Boolean = false,
    val canReTransformClasses: Boolean = false,
    val canSetNativeMethodPrefix: Boolean = false
) {

    companion object {
        inline fun <reified T> new(): AgentConfiguration<T> = AgentConfiguration(T::class.java)

        inline fun <reified T> new(
            canRedefineClasses: Boolean = false,
            canReTransformClasses: Boolean = false,
            canSetNativeMethodPrefix: Boolean = false
        ): AgentConfiguration<T> = AgentConfiguration(
            T::class.java,
            canRedefineClasses = canRedefineClasses,
            canReTransformClasses = canReTransformClasses,
            canSetNativeMethodPrefix = canSetNativeMethodPrefix
        )
    }
}