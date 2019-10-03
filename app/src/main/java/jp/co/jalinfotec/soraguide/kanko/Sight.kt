package jp.co.jalinfotec.soraguide

data class Sight(
    val Address: String,
    val Title: String,
    val PhotoList: List<Photo>,
//    val Chiku: Chiku,
//    val Closed: String,
//    val GenreList: List<ListGenre>,
//    val HasCoupon: Int,
//    val Homen: Homen,
//    val InquiryName: String,
//    val JIS: JIS,
//    val Kana: String,
//    val Ken: Ken,
    val Latitude: String,
//    val LatitudeW10: String,
    val Longitude: String,
//    val LongitudeW10: String,
    val Mesh: Mesh,
//    val Parking: Parking,
//
    val Price: String?,
//    val Rank: String,
//    val SightID: Int,
//    val SightOptionList: List<SightOption>,
//    val Station: Station,
//    val SubArea: SubArea,
    val Summary: String,
//    val Tel: String,
//    val TelAnnotation: String,
    val Time: String
//    val Traffic1: String,
//    val Traffic2: String,
//    val Tresure: String
)

data class Mesh(
    val Code:Int,
    val name:String
)
