package jp.co.jalinfotec.soraguide.model.stamprally

import java.io.Serializable
import java.util.*

data class StampRallyEntity(
    val stampRallyId: Int,
    val stampRallyName: String,
    var startDate: Date,
    var endDate: Date,
    var isCompleted: Boolean,
    var isCouponUsed: Boolean,
    val couponUri: String,
    var markers: List<Marker>
) :Serializable {
    data class Marker(
        val markerId: Int,
        val latitude: Double,   // 緯度
        val longitude: Double,  // 軽度
        val altitude: Double,   // 高度
        val markerUri: String, // 画像URI
        var isAcquired: Boolean // 獲得済みフラグ
    ): Serializable

    // 獲得済みマーカー数の取得
    fun getAcquiredNum(): Int {
        var n = 0
        if (isCompleted) n = markers.size else for (m in markers) if (m.isAcquired) n++
        return n
    }

}


