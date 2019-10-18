package jp.co.jalinfotec.soraguide.ar.stamprally

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class StampRallyRepository(context: Context) {

    fun getStampRallyData(): ArrayList<StampRallyEntity>? {
        val list = ArrayList<StampRallyEntity>()
        list.add(StampRallyEntity(1L, "ほげほげを探せ！！！", Date() , Date(), false, false,"",
            listOf(
                StampRallyEntity.Marker(1L, 1.0, 1.0, 1.0, "", false),
                StampRallyEntity.Marker(2L, 1.0, 1.0, 1.0, "", false),
                StampRallyEntity.Marker(3L, 1.0, 1.0, 1.0, "", false))
        ))
        list.add(StampRallyEntity(2L, "ぴよぴよを探せ！！！", Date() , Date(), false, false,"",
            listOf(
                StampRallyEntity.Marker(1L, 1.0, 1.0, 1.0, "", false),
                StampRallyEntity.Marker(2L, 1.0, 1.0, 1.0, "", true),
                StampRallyEntity.Marker(3L, 1.0, 1.0, 1.0, "", false))
        ))
        list.add(StampRallyEntity(3L, "ふーを探せ！！！", Date() , Date(), false, false,"",
            listOf(
                StampRallyEntity.Marker(1L, 1.0, 1.0, 1.0, "", true),
                StampRallyEntity.Marker(2L, 1.0, 1.0, 1.0, "", false),
                StampRallyEntity.Marker(3L, 1.0, 1.0, 1.0, "", true))
        ))
        list.add(StampRallyEntity(4L, "ばーを探せ！！！", Date() , Date(), true,  false,"wikitude/stamp-rally/assets/qr.png",
            listOf(
                StampRallyEntity.Marker(1L, 1.0, 1.0, 1.0, "", true),
                StampRallyEntity.Marker(2L, 1.0, 1.0, 1.0, "", true),
                StampRallyEntity.Marker(3L, 1.0, 1.0, 1.0, "", true))
        ))
        return list
    }
}