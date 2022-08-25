package sk.sandeep.myapplication.model.data_models


import com.google.gson.annotations.SerializedName

data class NewsApiResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)