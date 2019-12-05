package jp.co.jalinfotec.soraguide.model.topics

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Topics (

    @SerializedName("topic_id")
    val topic_id: Int,

    @SerializedName("topic_name")
    val topic_name: String,

    @SerializedName("topic_image")
    val topic_image: String,

    @SerializedName("topic_url")
    val topic_url: String
/* 表示・非表示は端末に必要ないため一旦削除
    @SerializedName("display")
    val display: Int)

 */
): Serializable