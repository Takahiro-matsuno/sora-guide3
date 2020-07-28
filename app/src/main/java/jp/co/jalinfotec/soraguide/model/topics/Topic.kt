package jp.co.jalinfotec.soraguide.model.topics

import java.io.Serializable

data class Topic (
    var id: Long,
    var airportCompanyId: Long,
    var name: String,
    var url: String,
    var imageUrl: String
): Serializable
