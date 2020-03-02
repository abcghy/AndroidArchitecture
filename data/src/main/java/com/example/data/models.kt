package com.example.data
import com.google.gson.annotations.SerializedName
import java.util.*

data class LatestRes(
    // todo 替换为 只表示日期，不表示时间的类（因为知乎的这个字段只有日期，不过这个不紧急）
    @SerializedName("date")
    val date: Date?,
    @SerializedName("stories")
    val stories: List<Story>?,
    @SerializedName("top_stories")
    val topStories: List<TopStory>?
)

data class Story(
    @SerializedName("ga_prefix")
    val gaPrefix: String?,
    @SerializedName("hint")
    val hint: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image_hue")
    val imageHue: String?,
    @SerializedName("images")
    val images: List<String>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("url")
    val url: String?
)

data class TopStory(
    @SerializedName("ga_prefix")
    val gaPrefix: String?,
    @SerializedName("hint")
    val hint: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("image_hue")
    val imageHue: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("type")
    val type: Int?,
    @SerializedName("url")
    val url: String?
)