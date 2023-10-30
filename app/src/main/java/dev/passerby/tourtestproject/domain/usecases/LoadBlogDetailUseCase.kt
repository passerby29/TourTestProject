package dev.passerby.tourtestproject.domain.usecases

import dev.passerby.tourtestproject.domain.repos.MainRepository

class LoadBlogDetailUseCase(private val repository: MainRepository) {
    suspend operator fun invoke(blogId: Int) = repository.loadBlogDetail(blogId)
}