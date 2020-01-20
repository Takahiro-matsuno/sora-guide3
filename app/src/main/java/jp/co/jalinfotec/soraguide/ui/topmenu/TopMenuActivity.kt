package jp.co.jalinfotec.soraguide.ui.topmenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.topics.Topics
import jp.co.jalinfotec.soraguide.model.topics.TopicsRepository
import jp.co.jalinfotec.soraguide.model.topics.TopicsService
import jp.co.jalinfotec.soraguide.ui.NavigationActivity
import kotlinx.android.synthetic.main.activity_top_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.timer


class TopMenuActivity :
    AppCompatActivity() {
    private val logTag = this::class.java.simpleName
    private val topicsRepository = TopicsRepository()
    private val mTopicsList = ArrayList<Topics>()
    private var isLoading = false

    //画像スライドショー初期設定
    private var isSlideshow = true
    private val handler = Handler()

    //どの画像を表示しているかを保持する変数
    private var position = 0

    //Topicsを格納する変数
    private var topics: MutableList<Topics> = mutableListOf()
    private lateinit var topicsService: TopicsService
    private val permissionRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // https://github.com/android/sunflower/issues/295
        setContentView(R.layout.activity_top_menu)

        /**
         * 各画面への遷移
         */
        airport.setOnClickListener {
            /* TODO 施設案内に置き換える */ Toast.makeText(
            this,
            "coming soon",
            Toast.LENGTH_SHORT
        ).show()
        }
        stamp_rally.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.STAMP_RALLY) }
        taxi.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.TAXI) }
        sightseeing.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.SIGHT) }

        // 広告表示
        if (supportFragmentManager.findFragmentById(R.id.adsense_layout) == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.adsense_layout, AdSenseFragment().newInstance()).commit()
        }


        // 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl("https://topicsapi.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        topicsService = retrofit.create(TopicsService::class.java)

        viewFlipper.flipInterval = 5000
        // Topicsのロード
        loadTopics()
    }

    // NavigationActivityへの遷移
    private fun intentNavigationActivity(navigationType: NavigationActivity.NavigationType) {
        val intent = Intent(this, NavigationActivity::class.java)
        intent.putExtra(NavigationActivity.NAVIGATION_KEY, navigationType)
        startActivity(intent)
    }

    private fun loadTopics() {

       var iv = ImageView(this)

        if (!isLoading) {
            isLoading = true
            viewFlipper.stopFlipping()

            topicsService.getTopic().enqueue(object : Callback<Array<Topics>?> {

                override fun onResponse(
                    call: Call<Array<Topics>?>,
                    response: Response<Array<Topics>?>
                ) {
                    //topicsにデータを格納
                    for (topic in response.body()!!) {
                        topics.add(topic)
                    }

                    //ImageViewに初期画像をセット
                    Glide.with(this@TopMenuActivity).load(topics[position].topicImage).into(iv)

                    topics.forEach { new ->
                        if (mTopicsList.find { local -> local.topicId == new.topicId } == null) { // 新しいTopicsの場合
                            mTopicsList.add(new)
                            // ImageViewの作成
                             iv = ImageView(this@TopMenuActivity)
                            Glide.with(this@TopMenuActivity).load(new.topicImage).into(iv)
                            iv.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(new.topicUrl))) }
                            viewFlipper.addView(iv)
                        }
                    }

                    viewFlipper.addView(iv)
                }

                override fun onFailure(
                    call: Call<Array<Topics>?>,
                    t: Throwable
                ) {
                    Log.d(logTag,"通信エラー:$t")
                }
            })
            viewFlipper.startFlipping()
        }
    }


    /*
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

     */
}
