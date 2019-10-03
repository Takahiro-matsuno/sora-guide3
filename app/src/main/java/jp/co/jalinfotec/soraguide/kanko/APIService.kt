package jp.co.jalinfotec.soraguide

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("appif/sight")
    fun getResponse(@Query("appid")appid:String,
              @Query("keywords") keywords: String,
              @Query("ken") ken:String,
              @Query("SOC0") tachiyori:String,
              @Query("pagecount") pagecount:Int,
              @Query("responsetype") respnsetype: String):Call<List<ResponseData>>
}
//fun createService():APIService{
//    val baseApiUrl = "https://www.j-jti.com/"
//
//    val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//    val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging)
//    //retrofitクライアント取得
//    val retrofit = Retrofit.Builder()
//        .baseUrl(baseApiUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(httpClientBuilder.build())
//        .build()
//
//    return retrofit.create(APIService::class.java)
//}
//
