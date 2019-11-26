package jp.co.jalinfotec.soraguide.topMenu

import retrofit2.Call
import retrofit2.http.GET

interface GetTopicsApiService {
        @GET("index/getAllTopics")
        fun getTopics(): Call<Array<Topic>>
}