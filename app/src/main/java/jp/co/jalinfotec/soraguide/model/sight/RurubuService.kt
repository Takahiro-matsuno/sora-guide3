package jp.co.jalinfotec.soraguide.model.sight

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RurubuService {
    @GET("appif/sight")
    fun getResponse(@Query("appid")appid:String?,
                    @Query("keywords") keywords: String?,
                    @Query("ken") ken:String?,
                    @Query("SOC0") tachiyori:String?,
                    @Query("SOC5") recomend_spring:String?,
                    @Query("SOC6") recomend_summer:String?,
                    @Query("SOC7") recomend_autumn:String?,
                    @Query("SOC8") recomend_winter:String?,
                    @Query("SOC9") inspiteofRain:String?,
                    @Query("pagecount") pagecount:Int?,
                    @Query("responsetype") respnsetype: String?):Call<List<SightPage>>
}
