package jp.co.jalinfotec.soraguide.ar.stamprally

import android.content.Context
import java.util.*
import kotlin.collections.ArrayList

class StampRallyRepository(context: Context) {

    fun getStampRallyData(): ArrayList<StampRallyEntity>? {
        val list = ArrayList<StampRallyEntity>()
        //list.add(StampRallyEntity(1L, "ほげほげを探せ！！！", Date() , Date(), 3, 3))
        //list.add(StampRallyEntity(2L, "ぴよぴよを探せ！！！", Date() , Date(), 3, 1))
        //list.add(StampRallyEntity(3L, "ふがふがを探せ！！！", Date() , Date(), 3, 0))
        return list
    }
}