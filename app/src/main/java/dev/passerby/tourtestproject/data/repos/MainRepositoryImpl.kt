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
import dev.passerby.tourtestproject.domain.models.BlogModel
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.MainModel
import dev.passerby.tourtestproject.domain.models.RoomsModel
import dev.passerby.tourtestproject.domain.models.TourModel
import dev.passerby.tourtestproject.domain.repos.MainRepository
import retrofit2.Response

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

        val mainInfoLiveData = MutableLiveData<MainModel>()

        val mainInfo: MainDto =
            baseLoader(apiService.loadMainInfo(), mainInfoResult, "loadMainInfo")!!

        mainInfoLiveData.value = mainInfoMapper.mapDtoToEntity(mainInfo)

        return mainInfoLiveData
    }

    override suspend fun loadBlogContent(): LiveData<BlogModel> {
        val blogLiveData = MutableLiveData<BlogModel>()

        val blogResponse: BlogDto =
            baseLoader(apiService.loadBlogContent(), blogContentResult, "loadBlogContent")!!

        val blogContent: List<BlogModel.BlogItem> = blogResponse.blogList.map {
            blogContentMapper.mapDtoContentToEntityContent(it)
        }

        blogLiveData.value = BlogModel(blogContent)
        return blogLiveData
    }

    override suspend fun loadRoomsContent(): LiveData<RoomsModel> {
        val roomsLiveData = MutableLiveData<RoomsModel>()

        val roomsContent: RoomsDto =
            baseLoader(apiService.loadRoomContent(), roomsContentResult, "loadRoomsContent")!!

        roomsLiveData.value = roomsContentMapper.mapDtoToEntity(roomsContent)

        return roomsLiveData
    }

    override suspend fun loadToursContent(): LiveData<TourModel> {
        val toursLiveData = MutableLiveData<TourModel>()

        val toursContent: ToursDto =
            baseLoader(apiService.loadToursContent(), toursContentResult, "loadToursContent")!!

        toursLiveData.value = toursContentMapper.mapDtoToEntity(toursContent)

        return toursLiveData
    }

    override suspend fun loadFunContent(type: String): LiveData<FunModel> {
        val funLiveData = MutableLiveData<FunModel>()

        val funContent: FunDto = baseLoader(
            apiService.loadFunContent(type = type),
            funContentResult,
            "loadFunContent$type"
        )!!

        funLiveData.value = funContentMapper.mapDtoToEntity(funContent)
        return funLiveData
    }

    override suspend fun loadBlogDetail(blogId: Int): LiveData<BlogDetailModel> {
        val blogDetailLiveData = MutableLiveData<BlogDetailModel>()

        val blogDetail: BlogDetailDto = baseLoader(
            apiService.loadBlogDetail(blogId = blogId),
            blogDetailResult,
            "loadBlogDetail"
        )!!

        blogDetailLiveData.value = blogDetailMapper.mapDtoToEntity(blogDetail)
        return blogDetailLiveData
    }

    private fun <T> baseLoader(
        response: Response<T>,
        result: MutableLiveData<BaseResponse<T>>,
        title: String
    ): T? {

        val body: T

        result.postValue(BaseResponse.Loading())
        try {
            if (response.code() == 200) {
                result.postValue(BaseResponse.Success(response.body()))
                body = response.body()!!
                Log.d(TAG, "${title}Try: ${response.isSuccessful}")
                return body
            } else {
                mainInfoResult.postValue(BaseResponse.Error(response.message()))
                Log.d(TAG, "loadMainInfoElse: ${response.message()}")
            }
        } catch (ex: Exception) {
            Log.d(TAG, "loadMainInfoCatch: $ex")
            mainInfoResult.postValue(BaseResponse.Error(ex.message))
        }
        return null
    }

    companion object {
        private const val TAG = "MainRepositoryImplTag"
    }
}