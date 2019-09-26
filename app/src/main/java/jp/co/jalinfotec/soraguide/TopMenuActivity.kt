package jp.co.jalinfotec.soraguide

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest
import com.squareup.picasso.Picasso
import jp.co.jalinfotec.soraguide.topMenu.Topic
import kotlinx.android.synthetic.main.activity_top_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer
import jp.co.jalinfotec.soraguide.topMenu.GetTopicsApiService


class TopMenuActivity : AppCompatActivity() {


    //Admob生成用
    lateinit var mAdView : AdView

    //画像スライドショー初期設定
    private var isSlideshow = true
    private val handler = Handler()

    //どの画像を表示しているかを保持する変数
    private var position = 0

    //Topicsを格納する変数
    var topics:MutableList<Topic> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        //Admobの設定　ここから

        //Admobアカウント設定
        // Sample AdMob app ID: ca-app-pub-2003234893806822~5059310598
        MobileAds.initialize(this, "ca-app-pub-2003234893806822~5059310598")

        //ViewにAdmobの情報を設定
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        //Admobの設定　ここまで


        //Topicsの生成　ここから

        // Retrofitクライアントの取得
        val retrofit = Retrofit.Builder().baseUrl("http://10.0.2.2:8080/").addConverterFactory(
            GsonConverterFactory.create()).build()

        // APIエンドポイントの生成
        val api = retrofit.create(GetTopicsApiService::class.java)

        // 引数によってapiエンドポイントを指定し、リクエスト
        val a = api.getTopics().enqueue(object: Callback<Array<Topic>> {

            // 通信が失敗したときの処理
            override fun onFailure(call: Call<Array<Topic>>?, t: Throwable?) {
                // 今回は失敗したときは無視しています。
                Log.d("test","$t")
            }

            // 通信が成功したときの処理
            override fun onResponse(call: Call<Array<Topic>>?, response: Response<Array<Topic>>?){

                //topicsにデータを格納
                for(topic in response?.body()!!){
                    topics.add(topic)
                }


                //ImageViewに初期画像をセット
                Picasso.get()
                    .load(topics[position].topic_image)
                    .fit()
                    .centerInside()
                    .into(imageView)


                //period秒ごとにImageViewの表示画像を切り替える
                timer(period = 5000){
                    handler.post {
                        if(isSlideshow){

                            //画像切り替えのメソッド呼び出し
                            movePosition(1,topics)
                        }
                    }
                }


                //画像タップされた時の動作
                imageView.setOnClickListener {
                    Log.d("TAG", position.toString())
                    val intent = Intent(Intent.ACTION_VIEW)
                    //topicsに設定されているURLのページへ遷移
                    intent.data = Uri.parse(topics[position].topic_url)
                    startActivity(intent)
                }



            }
        })

        //空港案内画面へ遷移
        val toAirportButton = findViewById<ImageButton>(R.id.airport)
        toAirportButton.setBackgroundColor(Color.GRAY)
        toAirportButton.setOnClickListener{

            //startActivity<AirportGuideActivity>()
        }

        //フライト情報画面へ遷移
        val toFlightInfoButton = findViewById<ImageButton>(R.id.flight)
        toFlightInfoButton.setBackgroundColor(Color.GRAY)
        toFlightInfoButton.setOnClickListener{

            //startActivity<AirportGuideActivity>()
        }

        //タクシー予約画面へ遷移
        val toTaxyButton = findViewById<ImageButton>(R.id.taxi)
        toTaxyButton.setOnClickListener{

            //startActivity<AirportGuideActivity>()
        }

        //観光案内画面へ遷移
        val toSightseeingButton = findViewById<ImageButton>(R.id.sightseeing)
        toSightseeingButton.setOnClickListener{

            // startActivity<AirportGuideActivity>()
        }

    }

    //画像切り替えのメソッド
    private fun movePosition(move: Int, topics : MutableList<Topic> ){
        position += move
        //Positionが画像配列のサイズよりも大きい場合は0に戻す。
        if(position >= topics.size){
            position = 0
            //positionが0以下になった場合
        }else if(position < 0){
            position = topics.size - 1
        }

        //ImageViewに画像をセットし直す
        Picasso.get()
            .load(topics[position].topic_image)
            .fit()
            .centerInside()
            .into(imageView)

    }
}
