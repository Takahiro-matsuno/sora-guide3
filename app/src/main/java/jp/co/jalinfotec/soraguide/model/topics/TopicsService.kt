package jp.co.jalinfotec.soraguide.model.topics

import retrofit2.Call
import retrofit2.http.GET

interface TopicsService {
        @GET("soraguideapi/")
        fun getTopic(): Call<Array<Topics>>
}