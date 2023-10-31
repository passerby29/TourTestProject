package dev.passerby.tourtestproject.data.repos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.passerby.tourtestproject.data.mappers.BlogContentMapper
import dev.passerby.tourtestproject.data.mappers.BlogDetailMapper
import dev.passerby.tourtestproject.data.mappers.FunContentMapper
import dev.passerby.tourtestproject.data.mappers.MainInfoMapper
import dev.passerby.tourtestproject.data.mappers.RoomsContentMapper
import dev.passerby.tourtestproject.data.mappers.ToursContentMapper
import dev.passerby.tourtestproject.data.models.BlogDetailDto
import dev.passerby.tourtestproject.data.models.BlogDto
import dev.passerby.tourtestproject.data.models.FunDto
import dev.passerby.tourtestproject.data.models.MainDto
import dev.passerby.tourtestproject.data.models.RoomsDto
import dev.passerby.tourtestproject.data.models.ToursDto
import dev.passerby.tourtestproject.data.network.ApiFactory
import dev.passerby.tourtestproject.data.network.BaseResponse
import dev.passerby.tourtestproject.domain.models.BlogDetailModel
import dev.passerby.tourtestproject.domain.models.BlogItem
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.domain.repos.MainRepository

class MainRepositoryImpl : MainRepository {

    private val apiService = ApiFactory.apiService

    private val mainInfoMapper = MainInfoMapper()
    private val blogContentMapper = BlogContentMapper()
    private val roomsContentMapper = RoomsContentMapper()
    private val toursContentMapper = ToursContentMapper()
    private val funContentMapper = FunContentMapper()
    private val blogDetailMapper = BlogDetailMapper()

    private val mainInfoResult = MutableLiveData<BaseResponse<MainDto>>()
    private val blogContentResult = MutableLiveData<BaseResponse<BlogDto>>()
    private val roomsContentResult = MutableLiveData<BaseResponse<RoomsDto>>()
    private val toursContentResult = MutableLiveData<BaseResponse<ToursDto>>()
    private val funContentResult = MutableLiveData<BaseResponse<FunDto>>()
    private val blogDetailResult = MutableLiveData<BaseResponse<BlogDetailDto>>()

    override suspend fun loadMainInfo(): LiveData<MainModel> {
        val mainInfo: MainDto
        val mainInfoLiveData = MutableLiveData<MainModel>()
        mainInfoResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadMainInfo()
            if (response.code() == 200) {
                mainInfoResult.postValue(BaseResponse.Success(response.body()))
                mainInfo = response.body()!!
                mainInfoLiveData.value = mainInfoMapper.mapDtoToEntity(mainInfo)
                Log.d(TAG, "loadMainInfoTry: ${response.body()?.mainInfo?.buttons}")
            } else {
                mainInfoResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadMainInfoElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadMainInfoCatch: $ex")
            mainInfoResult.postValue(BaseResponse.Error(ex.message))
        }
        return mainInfoLiveData
    }

    override suspend fun loadBlogContent(): LiveData<BlogModel> {
        val blogContent: List<BlogItem>
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

    override suspend fun loadRoomsContent(): LiveData<RoomsModel> {
        val roomsContent: RoomsDto
        val roomsLiveData = MutableLiveData<RoomsModel>()
        roomsContentResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadRoomContent()
            if (response.code() == 200) {
                roomsContentResult.postValue(BaseResponse.Success(response.body()))
                roomsContent = response.body()!!
                roomsLiveData.value = roomsContentMapper.mapDtoToEntity(roomsContent)
                Log.d(TAG, "loadRoomsContentTry: ${response.isSuccessful}")
                return roomsLiveData
            } else {
                roomsContentResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadRoomsContentElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadRoomsContentCatch: $ex")
            roomsContentResult.value = BaseResponse.Error(ex.message)
        }
        roomsLiveData.value = RoomsModel(emptyList(), null, false, "")
        return roomsLiveData
    }

    override suspend fun loadToursContent(): LiveData<TourModel> {
        val toursContent: ToursDto
        val toursLiveData = MutableLiveData<TourModel>()
        toursContentResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadToursContent()
            if (response.code() == 200) {
                toursContentResult.postValue(BaseResponse.Success(response.body()))
                toursContent = response.body()!!
                toursLiveData.value = toursContentMapper.mapDtoToEntity(toursContent)
                Log.d(TAG, "loadToursContentTry: ${response.isSuccessful}")
                return toursLiveData
            } else {
                toursContentResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadToursContentElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadToursContentCatch: $ex")
            toursContentResult.value = BaseResponse.Error(ex.message)
        }
        toursLiveData.value = TourModel(emptyList(), null, false, "")
        return toursLiveData
    }

    override suspend fun loadFunContent(type: String): LiveData<FunModel> {
        val funContent: FunDto
        val funLiveData = MutableLiveData<FunModel>()
        funContentResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadFunContent(type = type)
            if (response.code() == 200) {
                funContentResult.postValue(BaseResponse.Success(response.body()))
                funContent = response.body()!!
                funLiveData.value = funContentMapper.mapDtoToEntity(funContent)
                Log.d(TAG, "loadFunContentTry: ${response.isSuccessful}")
                return funLiveData
            } else {
                funContentResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadFunContentElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadFunContentCatch: $ex")
            funContentResult.value = BaseResponse.Error(ex.message)
        }
        funLiveData.value = FunModel(emptyList(), null, false, "")
        return funLiveData
    }

    override suspend fun loadBlogDetail(blogId: Int): LiveData<BlogDetailModel> {
        val blogDetail: BlogDetailDto
        val blogDetailLiveData = MutableLiveData<BlogDetailModel>()
        blogDetailResult.postValue(BaseResponse.Loading())
        try {
            val response = apiService.loadBlogDetail(blogId = blogId)
            if (response.code() == 200) {
                blogDetailResult.postValue(BaseResponse.Success(response.body()))
                blogDetail = response.body()!!
                blogDetailLiveData.value = blogDetailMapper.mapDtoToEntity(blogDetail)
                Log.d(TAG, "loadBlogDetailTry: ${response.body()?.blogDetail?.date}")
            } else {
                blogDetailResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadBlogDetailElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadBlogDetailCatch: $ex")
            blogDetailResult.postValue(BaseResponse.Error(ex.message))
        }
        return blogDetailLiveData
    }

    companion object {
        private const val TAG = "MainRepositoryImplTag"
    }
}