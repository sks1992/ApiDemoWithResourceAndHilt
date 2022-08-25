package sk.sandeep.myapplication.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sk.sandeep.myapplication.model.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val newsLiveData get() =newsRepository.newsData

    fun getNews(){
        viewModelScope.launch {
            newsRepository.getTopHeadlines("in")
        }
    }
}