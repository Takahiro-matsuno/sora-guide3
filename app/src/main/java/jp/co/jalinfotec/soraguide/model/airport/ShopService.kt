package jp.co.jalinfotec.soraguide.model.airport

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ShopService {
    @GET("airport/{airportCompanyId}/shop")
    fun getShop(@Path("airportCompanyId") airportCompanyId: Long): Call<List<Shop>>
}
