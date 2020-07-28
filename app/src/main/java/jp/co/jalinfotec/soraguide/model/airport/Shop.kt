package jp.co.jalinfotec.soraguide.model.airport

import java.io.Serializable

class Shop(
    var id: Long = -1,
    var airportCompanyId: Long ,
    var name: String,
    var phoneNumber: String,
    var url: String,
    var imageUrl: String,
    var openHour: String,
    var message: String
): Serializable
