package dev.passerby.tourtestproject.domain.usecases

import dev.passerby.tourtestproject.domain.repos.MainRepository

class LoadFunContentUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(type: String) = repository.loadFunContent(type)
}