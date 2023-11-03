package dev.passerby.tourtestproject.domain.usecases

import dev.passerby.tourtestproject.domain.repos.MainRepository

class LoadToursContentUseCase(private val repository: MainRepository) {
    suspend operator fun invoke() = repository.loadToursContent()
}