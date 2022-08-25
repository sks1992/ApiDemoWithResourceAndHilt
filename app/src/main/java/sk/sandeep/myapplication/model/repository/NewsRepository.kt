package sk.sandeep.myapplication.model.repository

import sk.sandeep.myapplication.model.remote.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi
) {
}