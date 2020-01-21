package jp.co.jalinfotec.soraguide.model.topics

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Topics (

    @SerializedName("topicId")
    @Expose
    val topicId: Int,
    @SerializedName("topicName")
    @Expose
    val topicName: String,
    @SerializedName("topicImage")
    @Expose
    val topicImage: String,
    @SerializedName("topicUrl")
    @Expose
    val topicUrl: String,
    @SerializedName("display")
    @Expose
    val display: Int

)