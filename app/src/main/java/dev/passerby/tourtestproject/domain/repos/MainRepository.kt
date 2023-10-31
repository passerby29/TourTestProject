package dev.passerby.tourtestproject.domain.repos

import androidx.lifecycle.LiveData
import dev.passerby.tourtestproject.domain.models.BlogDetailModel
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel

interface MainRepository {

    suspend fun loadMainInfo(): LiveData<MainModel>

    suspend fun loadBlogContent(): LiveData<BlogModel>

    suspend fun loadRoomsContent(): LiveData<RoomsModel>
    suspend fun loadToursContent(): LiveData<TourModel>


    suspend fun loadFunContent(type: String): LiveData<FunModel>
    suspend fun loadBlogDetail(blogId: Int): LiveData<BlogDetailModel>
}