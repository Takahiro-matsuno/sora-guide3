package jp.co.jalinfotec.soraguide.ar.stamprally

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class StampRallyRepository(context: Context) {

    fun getStampRallyData(): ArrayList<StampRallyEntity>? {
        val list = ArrayList<StampRallyEntity>()
        list.add(StampRallyEntity(1, "ほげほげを探せ！！！", Date() , Date(), false, false,"assets/qr.png",
            listOf(
                StampRallyEntity.Marker(1, 1.0, 1.0, 1.0, "stamp_red.png", false),
                StampRallyEntity.Marker(2, 1.0, 1.0, 1.0, "stamp_blue.png", false),
                StampRallyEntity.Marker(3, 1.0, 1.0, 1.0, "stamp_green.png", false))
        ))
        list.add(StampRallyEntity(2, "ぴよぴよを探せ！！！", Date() , Date(), false, false,"assets/qr.png",
            listOf(
                StampRallyEntity.Marker(1, 1.0, 1.0, 1.0, "stamp_red.png", false),
                StampRallyEntity.Marker(2, 1.0, 1.0, 1.0, "stamp_blue.png", true),
                StampRallyEntity.Marker(3, 1.0, 1.0, 1.0, "stamp_green.png", false))
        ))
        list.add(StampRallyEntity(3, "ふーを探せ！！！", Date() , Date(), false, false,"assets/qr.png",
            listOf(
                StampRallyEntity.Marker(1, 1.0, 1.0, 1.0, "stamp_red.png", true),
                StampRallyEntity.Marker(2, 1.0, 1.0, 1.0, "stamp_blue.png", false),
                StampRallyEntity.Marker(3, 1.0, 1.0, 1.0, "stamp_green.png", true))
        ))
        list.add(StampRallyEntity(4, "LONGLONGLONGLONGばーを探せ！！！", Date() , Date(), true,  false,"assets/qr.png",
            listOf(
                StampRallyEntity.Marker(1, 1.0, 1.0, 1.0, "stamp_red.png", true),
                StampRallyEntity.Marker(2, 1.0, 1.0, 1.0, "stamp_blue.png", true),
                StampRallyEntity.Marker(3, 1.0, 1.0, 1.0, "stamp_green.png", true))
        ))
        return list
    }
}