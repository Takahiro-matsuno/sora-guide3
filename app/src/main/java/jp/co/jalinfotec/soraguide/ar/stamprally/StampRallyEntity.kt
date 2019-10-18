package jp.co.jalinfotec.soraguide.ar.stamprally

import java.util.*

data class StampRallyEntity(
    val stampRallyId: Long,
    val stampRallyName: String,
    val startDate: Date,
    val endDate: Date,
    val isCompleted: Boolean,
    val isCouponUsed: Boolean,
    val couponUri: String,
    val markers: List<Marker>
) {
    data class Marker(
        val markerId: Long,
        val latitude: Double,   // 緯度
        val longitude: Double,  // 軽度
        val altitude: Double,   // 高度
        val markerUri: String, // 画像URI
        val isAcquired: Boolean // 獲得済みフラグ
    )

    // 獲得済みマーカー数の取得
    fun getAcquiredNum(): Int {
        var n = 0
        if (isCompleted) n = markers.size else for (m in markers) if (m.isAcquired) n++
        return n
    }
}