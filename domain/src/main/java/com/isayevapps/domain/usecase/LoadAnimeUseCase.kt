package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.LoadResult
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.domain.repository.Repository
import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoadAnimeUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(loadType: LoadType): Result<Unit, CloudError> =
        withContext(dispatcher) { repository.loadItems(loadType) }
}