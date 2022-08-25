package sk.sandeep.myapplication.model.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sk.sandeep.myapplication.model.data_models.NewsApiResponse
import sk.sandeep.myapplication.util.API_KEY

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsApiResponse>
}