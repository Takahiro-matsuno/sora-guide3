package jp.co.jalinfotec.soraguide.model.stamprally

import android.content.Context
import java.util.*

class StampRallyRepository(context: Context) {

    fun getStampRallyData(): List<StampRallyEntity>? {
        val date = Date()
        return listOf(
            StampRallyEntity(
                stampRallyId = 1,
                stampRallyName = "高松ラーメン具材探し",
                stampRallyImageUri = "wikitude/stamp-rally/assets/ramen.png",
                startDate = date,
                endDate = date,
                isCompleted = false,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRallyEntity.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = false
                    )
                )
            ),
            StampRallyEntity(
                stampRallyId = 2,
                stampRallyName = "高松お土産探し",
                stampRallyImageUri = "wikitude/stamp-rally/assets/omiyage.png",
                startDate = date,
                endDate = date,
                isCompleted = true,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRallyEntity.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = true
                    )
                )
            ),
                StampRallyEntity(
                stampRallyId = 3,
                stampRallyName = "高松ラーメン素材探し",
                stampRallyImageUri = "wikitude/stamp-rally/assets/ramen.png",
                startDate = date,
                endDate = date,
                isCompleted = false,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                StampRallyEntity.Marker(
                    markerId = 1,
                    latitude = 1.0,
                    longitude = 1.0,
                    altitude = 1.0,
                    markerUri = "cha-syu.png",
                    isAcquired = false
                ),
                StampRallyEntity.Marker(
                    markerId = 2,
                    latitude = 1.0,
                    longitude = 1.0,
                    altitude = 1.0,
                    markerUri = "menma.png",
                    isAcquired = false
                ),
                StampRallyEntity.Marker(
                    markerId = 3,
                    latitude = 1.0,
                    longitude = 1.0,
                    altitude = 1.0,
                    markerUri = "tamago.png",
                    isAcquired = false
                ),
                StampRallyEntity.Marker(
                    markerId = 4,
                    latitude = 1.0,
                    longitude = 1.0,
                    altitude = 1.0,
                    markerUri = "shiraga_negi.png",
                    isAcquired = false
                )
            )
        ),
            StampRallyEntity(
                stampRallyId = 4,
                stampRallyName = "高松おみやげおみやげ",
                stampRallyImageUri = "wikitude/stamp-rally/assets/omiyage.png",
                startDate = date,
                endDate = date,
                isCompleted = true,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRallyEntity.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = true
                    ),
                    StampRallyEntity.Marker(
                        markerId = 4,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "shiraga_negi.png",
                        isAcquired = true
                    )
                )
            ),
            StampRallyEntity(
                stampRallyId = 5,
                stampRallyName = "高松ラーメン屋台さがし",
                stampRallyImageUri = "wikitude/stamp-rally/assets/ramen.png",
                startDate = date,
                endDate = date,
                isCompleted = false,
                isCouponUsed = false,
                couponUri = "wikitude/stamp-rally/assets/qr.png",
                markers = listOf(
                    StampRallyEntity.Marker(
                        markerId = 1,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "cha-syu.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
                        markerId = 2,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "menma.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
                        markerId = 3,
                        latitude = 1.0,
                        longitude = 1.0,
                        altitude = 1.0,
                        markerUri = "tamago.png",
                        isAcquired = false
                    ),
                    StampRallyEntity.Marker(
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