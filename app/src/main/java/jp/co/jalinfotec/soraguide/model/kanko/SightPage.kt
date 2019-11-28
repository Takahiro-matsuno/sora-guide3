package jp.co.jalinfotec.soraguide.model.kanko

data class SightPage(
    val PageNo: Int?,
    val SightList: List<Sight>?,
    val TotalPages: Int?,
    val TotalResults: Int?
)
