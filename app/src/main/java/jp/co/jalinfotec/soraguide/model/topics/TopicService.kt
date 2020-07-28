package jp.co.jalinfotec.soraguide.model.topics

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TopicService {
        @GET("airport/{airportCompanyId}/topic")
        fun getTopic(@Path("airportCompanyId") airportCompanyId: Long): Call<Array<Topic>>
}
