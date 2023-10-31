package dev.passerby.tourtestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.tourtestproject.data.repos.MainRepositoryImpl
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.usecases.LoadBlogContentUseCase
import dev.passerby.tourtestproject.domain.usecases.LoadMainInfoUseCase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl()
    private val loadMainInfoUseCase = LoadMainInfoUseCase(repository)
    private val loadBlogContentUseCase = LoadBlogContentUseCase(repository)

    private val _mainInfo = MutableLiveData<MainModel>()
    val mainInfo: LiveData<MainModel>
        get() = _mainInfo

    private val _blogContent = MutableLiveData<BlogModel>()
    val blogContent: LiveData<BlogModel>
        get() = _blogContent

    init {
        viewModelScope.launch {
            _mainInfo.value = loadMainInfoUseCase().value
            _blogContent.value = loadBlogContentUseCase().value
        }
    }
}