package org.andiez.core.exception

sealed class Failure {
    object NetworkException : Failure()
    data class ServerError(val message: String?) : Failure()
    object LocalDataNotFound : Failure()

    object Nothing : Failure()
}