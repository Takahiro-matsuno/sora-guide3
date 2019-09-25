package jp.co.jalinfotec.soraguide.topMenu

import com.google.gson.annotations.SerializedName

data class Topic (

    @SerializedName("topic_id")
    val topic_id: Int,

    @SerializedName("topic_title")
    val topic_title: String,

    @SerializedName("topic_content")
    val topic_content: String,

    @SerializedName("topic_image")
    val topic_image: String,

    @SerializedName("display")
    val display: Int)