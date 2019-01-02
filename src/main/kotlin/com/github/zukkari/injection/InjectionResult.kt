package com.github.zukkari.injection

sealed class InjectionResult

object Success : InjectionResult()

class Failure(val error: InjectionError): InjectionResult()

enum class InjectionError {
    FILE_NOT_FOUND
}