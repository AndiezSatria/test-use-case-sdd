package org.andiez.core.usecase

import org.andiez.core.exception.Failure
import kotlinx.coroutines.*
import org.andiez.core.functional.Either

abstract class UseCase<out Type, in Params> where Type : Any {
    abstract suspend fun run(params: Params): Either<Failure, Type>

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}) {
        val job = GlobalScope.async(Dispatchers.IO) { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
    }

    class None
}