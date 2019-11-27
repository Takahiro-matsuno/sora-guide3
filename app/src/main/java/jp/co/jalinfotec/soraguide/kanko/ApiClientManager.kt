package jp.co.jalinfotec.soraguide.kanko

import com.google.gson.Gson
import jp.co.jalinfotec.soraguide.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//RxJava用の設定値
open class ApiClientManager {
    companion object{
        private const val baseApiUrl = "https://www.j-jti.com/"

        val apiClient:APIService
            get() = Retrofit.Builder()
                .client(getclient())
                .baseUrl(baseApiUrl)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(APIService::class.java)

        private fun getclient():OkHttpClient{
            return OkHttpClient
                .Builder()
                .connectTimeout(120,TimeUnit.SECONDS)
                .readTimeout(120,TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
    }
}
