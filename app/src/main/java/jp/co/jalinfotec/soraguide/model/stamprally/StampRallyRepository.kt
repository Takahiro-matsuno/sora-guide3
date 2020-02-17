package jp.co.jalinfotec.soraguide.model.stamprally

import android.content.Context
import java.util.*

class StampRallyRepository(context: Context) {

    fun getStampRallyData(): List<StampRally>? {
        val date = Date()
        return listOf(
            StampRally(
                stampRallyId = 1,
                stampRallyName = "高松ラーメン具材探し",
                stampRallyImageUri = "wikitude/stamp-rally/assets/ramen.png",
                startDate = date,
                endDate = date,
                isCompleted = false,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRally.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = false
                    )
                )
            ),
            StampRally(
                stampRallyId = 2,
                stampRallyName = "高松お土産探し",
                stampRallyImageUri = "wikitude/stamp-rally/assets/omiyage.png",
                startDate = date,
                endDate = date,
                isCompleted = true,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRally.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = true
                    ),
                    StampRally.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = true
                    ),
                    StampRally.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = true
                    ),
                    StampRally.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = true
                    )
                )
            ),
            StampRally(
                stampRallyId = 3,
                stampRallyName = "ダミー",
                stampRallyImageUri = "wikitude/stamp-rally/assets/stampR.png",
                startDate = date,
                endDate = date,
                isCompleted = true,
                isCouponUsed = true,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRally.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = false
                    )
                )
            ),
            StampRally(
                stampRallyId = 4,
                stampRallyName = "ダミー",
                stampRallyImageUri = "wikitude/stamp-rally/assets/stampB.png",
                startDate = date,
                endDate = date,
                isCompleted = true,
                isCouponUsed = true,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRally.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = false
                    ),
                    StampRally.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = false
                    )
                )
            )
        )
    }
}