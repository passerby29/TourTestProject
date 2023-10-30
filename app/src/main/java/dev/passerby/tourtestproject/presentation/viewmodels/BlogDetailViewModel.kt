package dev.passerby.tourtestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.tourtestproject.data.repos.MainRepositoryImpl
import dev.passerby.tourtestproject.domain.models.BlogDetailModel
import dev.passerby.tourtestproject.domain.usecases.LoadBlogDetailUseCase
import kotlinx.coroutines.launch

class BlogDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl()
    private val loadBlogDetailUseCase = LoadBlogDetailUseCase(repository)

    private val _blogDetail = MutableLiveData<BlogDetailModel>()
    val blogDetail: LiveData<BlogDetailModel>
        get() = _blogDetail

    fun loadBlogDetail(blogId: Int) {
        viewModelScope.launch {
            _blogDetail.value = loadBlogDetailUseCase(blogId).value
        }
    }
}