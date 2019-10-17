package jp.co.jalinfotec.soraguide.ar.stamprally

import java.util.*

data class StampRallyEntity(
    val stampRallyId: Long,
    val stampRallyName: String,
    val startDate: Date,
    val endDate: Date,
    val num: Int,
    val done: Int,
    val couponResource: String,
    val markers: List<Marker>
) {
    data class Marker(
        val markerId: Long,
        val latitude: Double,
        val longitude: Double,
        val altitude: Double,
        val markerResource: String
    )
}