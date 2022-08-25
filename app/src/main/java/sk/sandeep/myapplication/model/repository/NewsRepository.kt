package sk.sandeep.myapplication.model.repository

import sk.sandeep.myapplication.model.data_models.NewsApiResponse
import sk.sandeep.myapplication.model.remote.NewsApi
import sk.sandeep.myapplication.util.Resource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {

//    suspend fun getTopHeadlines(countryCode:String):Resource<NewsApiResponse>{
//        return newsApi.getTopHeadlines(countryCode)
//    }
}