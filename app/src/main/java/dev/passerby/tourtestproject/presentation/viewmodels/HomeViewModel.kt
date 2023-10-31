package dev.passerby.tourtestproject.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.passerby.tourtestproject.data.repos.MainRepositoryImpl
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.domain.usecases.LoadBlogContentUseCase
import dev.passerby.tourtestproject.domain.usecases.LoadFunContentUseCase
import dev.passerby.tourtestproject.domain.usecases.LoadMainInfoUseCase
import dev.passerby.tourtestproject.domain.usecases.LoadRoomsContentUseCase
import dev.passerby.tourtestproject.domain.usecases.LoadToursContentUseCase
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MainRepositoryImpl()
    private val loadMainInfoUseCase = LoadMainInfoUseCase(repository)
    private val loadBlogContentUseCase = LoadBlogContentUseCase(repository)
    private val loadRoomsContentUseCase = LoadRoomsContentUseCase(repository)
    private val loadToursContentUseCase = LoadToursContentUseCase(repository)
    private val loadFunContentUseCase = LoadFunContentUseCase(repository)

    private val _mainInfo = MutableLiveData<MainModel>()
    val mainInfo: LiveData<MainModel>
        get() = _mainInfo

    private val _blogContent = MutableLiveData<BlogModel>()
    val blogContent: LiveData<BlogModel>
        get() = _blogContent

    private val _roomsContent = MutableLiveData<RoomsModel>()
    val roomsContent: LiveData<RoomsModel>
        get() = _roomsContent

    private val _toursContent = MutableLiveData<TourModel>()
    val toursContent: LiveData<TourModel>
        get() = _toursContent

    private val _foodContent = MutableLiveData<FunModel>()
    val foodContent: LiveData<FunModel>
        get() = _foodContent

    private val _funContent = MutableLiveData<FunModel>()
    val funContent: LiveData<FunModel>
        get() = _funContent

    private val _childContent = MutableLiveData<FunModel>()
    val childContent: LiveData<FunModel>
        get() = _childContent

    private val _placeContent = MutableLiveData<FunModel>()
    val placeContent: LiveData<FunModel>
        get() = _placeContent


    init {
        viewModelScope.launch {
            _mainInfo.value = loadMainInfoUseCase().value
            _blogContent.value = loadBlogContentUseCase().value
            _roomsContent.value = loadRoomsContentUseCase().value
            _toursContent.value = loadToursContentUseCase().value
            _foodContent.value = loadFunContentUseCase(TYPE_FOOD).value
            _funContent.value = loadFunContentUseCase(TYPE_FUN).value
            _childContent.value = loadFunContentUseCase(TYPE_CHILD).value
            _placeContent.value = loadFunContentUseCase(TYPE_PLACE).value
        }
    }

    companion object {
        private const val TYPE_FOOD = "food"
        private const val TYPE_FUN = "fun"
        private const val TYPE_CHILD = "child"
        private const val TYPE_PLACE = "place"
    }
}