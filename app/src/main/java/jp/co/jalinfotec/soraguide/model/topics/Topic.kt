package jp.co.jalinfotec.soraguide.model.topics

import com.google.gson.annotations.SerializedName

data class Topic (

    @SerializedName("topic_id")
    val topic_id: Int,

    @SerializedName("topic_name")
    val topic_name: String,

    @SerializedName("topic_image")
    val topic_image: String,

    @SerializedName("topic_url")
    val topic_url: String,

    @SerializedName("display")
    val display: Int)