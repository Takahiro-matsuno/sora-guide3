package jp.co.jalinfotec.soraguide.ui.topmenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.topics.Topic
import jp.co.jalinfotec.soraguide.model.topics.TopicService
import jp.co.jalinfotec.soraguide.ui.NavigationActivity
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.activity_top_menu.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TopMenuActivity :
    AppCompatActivity() {
    private val logTag = this::class.java.simpleName
    private val mTopicsList = ArrayList<Topic>()
    private var isLoading = false

    //Topicsを格納する変数
    private var topics: MutableList<Topic> = mutableListOf()
    private lateinit var topicService: TopicService


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // https://github.com/android/sunflower/issues/295
        setContentView(R.layout.activity_top_menu)

        toolbar.title = resources.getString(R.string.app_name)

        /**
         * 各画面への遷移
         */
        airport.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.AIRPORT) }
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
            .baseUrl(Constants.resourceApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        topicService = retrofit.create(TopicService::class.java)

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

        if (!isLoading) {
            isLoading = true
            viewFlipper.stopFlipping()

            topicService.getTopic(Constants.airportCompanyId).enqueue(object : Callback<List<Topic>> {

                override fun onResponse(call: Call<List<Topic>>, response: Response<List<Topic>>) {
                    //topicsにデータを格納
                    if (response.body() !=null && response.code() == 200){
                        for (topic in response.body()!!) {
                            topics.add(topic)
                        }
                    }

                    topics.forEach { new ->
                        if (mTopicsList.find { local -> local.id == new.id } == null) { // 新しいTopicsの場合
                            mTopicsList.add(new)
                            // ImageViewの作成
                            val iv = ImageView(this@TopMenuActivity)
                            Glide.with(this@TopMenuActivity).load(new.imageUrl).into(iv)
                            iv.setOnClickListener {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(new.url)
                                    )
                                )
                            }
                            viewFlipper.addView(iv)
                        }
                    }
                    isLoading = false
                }

                override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
                    Log.d(logTag, "通信エラー:$t")
                    isLoading = false
                }
            })
            viewFlipper.startFlipping()
        }
    }

}
