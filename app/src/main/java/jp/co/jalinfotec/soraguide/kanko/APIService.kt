package jp.co.jalinfotec.soraguide

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface APIService {
    @GET("appif/sight")
    fun getResponse(@Query("appid")appid:String?,
              @Query("keywords") keywords: String?,
              @Query("ken") ken:String?,
              @Query("SOC0") tachiyori:String?,
              @Query("pagecount") pagecount:Int?,
              @Query("responsetype") respnsetype: String?):Call<List<ResponseData>>

    //RxJavaで実装
    @GET("appif/sight")
    fun getResponseWithRxJava(@Query("appid")appid:String?,
                              @Query("keywords") keywords: String?,
                              @Query("ken") ken:String?,
                              @Query("SOC0") tachiyori:String?,
                              @Query("pagecount") pagecount:Int?,
                              @Query("responsetype") respnsetype: String?): Observable<List<ResponseData>>
}

