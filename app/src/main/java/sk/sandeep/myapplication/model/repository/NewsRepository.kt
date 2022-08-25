package sk.sandeep.myapplication.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import org.json.JSONObject
import sk.sandeep.myapplication.model.data_models.NewsApiResponse
import sk.sandeep.myapplication.model.remote.NewsApi
import sk.sandeep.myapplication.util.Resource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
    private val _newsData = MutableLiveData<Resource<NewsApiResponse>>()
    val newsData: LiveData<Resource<NewsApiResponse>>
        get() = _newsData

    suspend fun getTopHeadlines(countryCode: String) {
        _newsData.postValue(Resource.Loading())
        val response = newsApi.getTopHeadlines(countryCode)
        if (response.isSuccessful && response.body() != null) {
            _newsData.postValue(Resource.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _newsData.postValue(Resource.Error(errorObj.getString("message")))
        } else {
            _newsData.postValue(Resource.Error("Something went wrong"))
        }
    }
}