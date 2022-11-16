package com.korneliuszbarwinski.dailynews.common

class NewsException(override val message: String, val code: NewsExceptionCode) : Throwable() {

}

enum class NewsExceptionCode{
    apiKeyDisabled,
    apiKeyExhausted,
    apiKeyInvalid,
    apiKeyMissing,
    parameterInvalid,
    parametersMissing,
    rateLimited,
    sourcesTooMany,
    sourceDoesNotExist,
    unexpectedError,
    parsingError,
    unhandledException
}