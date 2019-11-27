package jp.co.jalinfotec.soraguide

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.squareup.picasso.Picasso
import jp.co.jalinfotec.soraguide.taxi.TaxiActivity
import jp.co.jalinfotec.soraguide.topMenu.GetTopicsApiService
import jp.co.jalinfotec.soraguide.topMenu.Topic
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.activity_top_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer

class TopMenuActivity : AppCompatActivity() {

    private val logTag = this::class.java.simpleName

    //画像スライドショー初期設定
    private var isSlideshow = true
    private val handler = Handler()

    //どの画像を表示しているかを保持する変数
    private var position = 0

    //Topicsを格納する変数
    private var topics:MutableList<Topic> = mutableListOf()
    private lateinit var topicsApiService: GetTopicsApiService
    private val permissionRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        // 権限リクエスト
        requestPermission()

        /**
         * 各画面への遷移
         */
        // 観光案内画面へ遷移
        sightseeing.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        // タクシー予約画面へ遷移
        taxi.setOnClickListener { startActivity(Intent(this, TaxiActivity::class.java)) }

        // TODO 施設案内機能実装後、変更する
        // airport.setOnClickListener { startActivity<AirportGuideActivity>() }
        // flight.setOnClickListener { startActivity(Intent(this, ARCameraActivity::class.java)) }
        airport.setBackgroundColor(Color.GRAY)
        flight.setBackgroundColor(Color.GRAY)

        // Admobの設定
        MobileAds.initialize(this, "ca-app-pub-2003234893806822~5059310598")
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.topicsUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        topicsApiService = retrofit.create(GetTopicsApiService::class.java)
        loadTopics()
    }

    private fun loadTopics() {

        topicsApiService.getTopics().enqueue(object: Callback<Array<Topic>> {

            // 通信が失敗したときの処理
            override fun onFailure(call: Call<Array<Topic>>?, t: Throwable?) {
                // 今回は失敗したときは無視しています。
                Log.d(logTag,"通信エラー:$t")
            }

            // 通信が成功したときの処理
            override fun onResponse(call: Call<Array<Topic>>?, response: Response<Array<Topic>>) {

                // TODO response 20x以外の処理を追加する
                //topicsにデータを格納
                for(topic in response.body()!!){
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

    // アプリの権限リクエスト
    private fun requestPermission () {
        val permList = ArrayList<String>()
        // Here, thisActivity is the current activity
        for (permission in Constants.permissionMap) {
            // 許可がない場合
            if (ContextCompat.checkSelfPermission(this, permission.key) != PackageManager.PERMISSION_GRANTED) {
                permList.add(permission.key)
                /*
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission.key)) {
                    // リクエスト済みではない場合
                }
                */

            }
        }
        // 許可されていない権限をリクエスト
        if (permList.any()) {
            ActivityCompat.requestPermissions(this, permList.toTypedArray(), permissionRequestCode)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray
    ) {
        when (requestCode) {
            permissionRequestCode -> {
                if (grantResults.contains(-1)) {
                    // 権限が許可されなかった場合、注意メッセージを表示する
                    val  builder =  AlertDialog.Builder(this)
                    builder.setMessage(
                        """
                            アプリの権限により使用できる機能が制限されます。
                            
                            権限の追加は[設定]->[アプリ]->[そらガイド]->[権限]から行えます。
                        """.trimIndent())
                    builder.setPositiveButton("追加しない", DialogInterface.OnClickListener() { dialog, _ ->
                        dialog.dismiss()
                    })
                    builder.setNegativeButton("追加する", DialogInterface.OnClickListener() { dialog, _ ->
                        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        intent.data = Uri.parse("package:jp.co.jalinfotec.soraguide")
                        startActivity(intent)
                        dialog.dismiss()
                    })
                    builder.show()
                }
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {} // Ignore all other requests.
        }
    }
}
