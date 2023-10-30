package dev.passerby.tourtestproject.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import dev.passerby.tourtestproject.data.mappers.BlogContentMapper
import dev.passerby.tourtestproject.data.mappers.MainInfoMapper
import dev.passerby.tourtestproject.data.models.BlogDto
import dev.passerby.tourtestproject.data.models.MainDto
import dev.passerby.tourtestproject.data.network.ApiFactory
import dev.passerby.tourtestproject.data.network.BaseResponse
import dev.passerby.tourtestproject.domain.models.BlogItem
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.repos.MainRepository

class MainRepositoryImpl : MainRepository {

    private val apiService = ApiFactory.apiService
    private val mainInfoMapper = MainInfoMapper()
    private val blogContentMapper = BlogContentMapper()
    private val mainInfoResult = MutableLiveData<BaseResponse<MainDto>>()
    private val blogContentResult = MutableLiveData<BaseResponse<BlogDto>>()

    override suspend fun loadMainInfo(): LiveData<MainModel> {
        val mainModel = MutableLiveData<MainDto>()
        mainInfoResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadMainInfo()
            if (response.code() == 200) {
                mainInfoResult.postValue(BaseResponse.Success(response.body()))
                mainModel.postValue(response.body())
                Log.d(TAG, "loadMainInfoTry: ${response.isSuccessful}")
            } else {
                mainInfoResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadMainInfoElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadMainInfoCatch: $ex")
            mainInfoResult.postValue(BaseResponse.Error(ex.message))
        }
        return (mainModel.map { mainInfoMapper.mapDtoToEntity(it) })
    }

    override suspend fun loadBlogContent(): LiveData<BlogModel> {
        var blogContent = listOf<BlogItem>()
        val blogLiveData = MutableLiveData<BlogModel>()
        blogContentResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadBlogContent()
            if (response.code() == 200) {
                blogContentResult.postValue(BaseResponse.Success(response.body()))
                blogContent = response.body()?.blogList?.map {
                    blogContentMapper.mapDtoContentToEntityContent(it)
                } ?: emptyList()
                blogLiveData.value = BlogModel(blogContent)
                Log.d(TAG, "loadBlogContentTry: ${response.isSuccessful}")
                return blogLiveData
            } else {
                blogContentResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadBlogContentElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadBlogContentCatch: $ex")
            blogContentResult.postValue(BaseResponse.Error(ex.message))
        }
        blogLiveData.value = BlogModel(emptyList())
        return blogLiveData
    }

    companion object {
        private const val TAG = "MainRepositoryImplTag"
    }
}