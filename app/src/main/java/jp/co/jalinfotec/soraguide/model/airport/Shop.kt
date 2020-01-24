package jp.co.jalinfotec.soraguide.model.airport

import android.graphics.drawable.Drawable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Shop (

    //店舗ID
    //TODO：不要？
    var shopId: Int,

    //店舗名
    val shopTitle: String,

    //店舗画像
    val shopImage: Drawable,

    //営業時間
    val shopTime: String,

    //店舗区分
    val shopCategory: String,

    //電話番号
    val shopTel: String,

    //店舗紹介文
    val shopText: String,

    //場所(フロアー)
    val shopLocation: String

): Serializable