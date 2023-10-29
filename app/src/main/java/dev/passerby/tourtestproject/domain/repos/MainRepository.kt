package dev.passerby.tourtestproject.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.MainModel

interface MainRepository {

    suspend fun loadMainInfo(): LiveData<MainModel>
    suspend fun loadBlogContent(): LiveData<BlogModel>
}