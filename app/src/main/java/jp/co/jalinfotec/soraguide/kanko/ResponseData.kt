package jp.co.jalinfotec.soraguide

data class ResponseData(
    val PageNo: Int,
    val SightList: List<Sight>,
    val TotalPages: Int,
    val TotalResults: Int
)
