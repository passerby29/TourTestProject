package dev.passerby.tourtestproject.data.network

import dev.passerby.tourtestproject.data.models.BlogDetailDto
import dev.passerby.tourtestproject.data.models.BlogDto
import dev.passerby.tourtestproject.data.models.MainDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("main")
    suspend fun loadMainInfo(
        @Query(QUERY_PARAM_ID) id: Int = ID
    ): Response<MainDto>

    @GET("blog")
    suspend fun loadBlogContent(
        @Query(QUERY_PARAM_ID) id: Int = ID,
        @Query(QUERY_PARAM_FORMAT) format: String = FORMAT
    ): Response<BlogDto>

    @GET("fun")
    suspend fun loadFunContent(
        @Query(QUERY_PARAM_ID) id: Int = ID,
        @Query(QUERY_PARAM_TYPE) type: String
    ): Response<BlogDto>

    @GET("blog-info")
    suspend fun loadBlogDetail(
        @Query(QUERY_PARAM_ID) id: Int = ID,
        @Query(QUERY_PARAM_BLOG_ID) blogId: Int
    ): Response<BlogDetailDto>

    companion object {
        private const val QUERY_PARAM_ID = "id"
        private const val QUERY_PARAM_FORMAT = "format"
        private const val QUERY_PARAM_TYPE = "type"
        private const val QUERY_PARAM_BLOG_ID = "blog_id"
        private const val ID = 117
        private const val FORMAT = "card"
    }
}
