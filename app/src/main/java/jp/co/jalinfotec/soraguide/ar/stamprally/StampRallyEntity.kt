package jp.co.jalinfotec.soraguide.ar.stamprally

import java.util.*

data class StampRallyEntity(
    val id: Long,
    val name: String,
    val sta: Date,
    val end: Date,
    val num: Int,
    val done: Int
) {
    /*
    data class StampRallyData(
        val stampId: Long,
        val imageResource: String
    )
     */
}