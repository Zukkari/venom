# venom

A tool to inject Java Agents into JVM at runtime.

Typically to inject an agent you have to compile it into the jar and provide
it as a `javaagent:` attribute when starting JVM. This tool allows you to
do this at runtime, without restarting the JVM.

This tool does:

- [x] Creates a jar for you with correct manifest attributes
- [x] Attaches it to the JVM
- [x] Schedules shutdown hook to delete the `.jar` after before JVM shutdown.

## Usage examples:

```kotlin
val result = Venom.inject(MyAgent::class.java)
when (result) {
    is Either.Success -> // Injected successfully
    is Either.Left -> // Injection failed
}
```


```kotlin
val config = AgentConfiguration.new<MyAgent>(
                canRedefineClasses = false,
                canReTransformClasses = false,
                canSetNativeMethodPrefix = false)

val inject = Venom.inject(config)
when (inject) {
    is Either.Left -> // success
    is Either.Right -> // err
}
```
