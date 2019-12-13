package jp.co.jalinfotec.soraguide.ui.topmenu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.topics.Topics
import jp.co.jalinfotec.soraguide.model.topics.TopicsRepository
import jp.co.jalinfotec.soraguide.ui.NavigationActivity
import kotlinx.android.synthetic.main.activity_top_menu.*

class TopMenuActivity :
    AppCompatActivity()
{
    private val logTag = this::class.java.simpleName
    private val topicsRepository = TopicsRepository()
    private val mTopicsList = ArrayList<Topics>()
    private var isLoading = false

    //画像スライドショー初期設定
    //private var isSlideshow = true
    //private val handler = Handler()

    //どの画像を表示しているかを保持する変数
    //private var position = 0

    //Topicsを格納する変数
    //private var topics:MutableList<Topics> = mutableListOf()
    //private lateinit var topicsService: TopicsService
    // private val permissionRequestCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // https://github.com/android/sunflower/issues/295
        setContentView(R.layout.activity_top_menu)

        /**
         * 各画面への遷移
         */
        airport.setOnClickListener { /* TODO 施設案内に置き換える */ Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show() }
        stamp_rally.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.STAMP_RALLY) }
        taxi.setOnClickListener { intentNavigationActivity(NavigationActivity.NavigationType.TAXI) }
        sightseeing.setOnClickListener  { intentNavigationActivity(NavigationActivity.NavigationType.SIGHT) }

        // 広告表示
        if (supportFragmentManager.findFragmentById(R.id.adsense_layout) == null) {
            supportFragmentManager.beginTransaction().add(R.id.adsense_layout, AdSenseFragment().newInstance()).commit()
        }


        /**
         * 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.topicsUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        topicsService = retrofit.create(TopicsService::class.java)
        */
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
            val topicsList = topicsRepository.getTopics()
            topicsList.forEach { new ->
                if (mTopicsList.find { local -> local.topic_id == new.topic_id } == null) { // 新しいTopicsの場合
                    mTopicsList.add(new)
                    // ImageViewの作成
                    val iv = ImageView(this)
                    Glide.with(this).load(new.topic_image).into(iv)
                    iv.setOnClickListener { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(new.topic_url))) }
                    viewFlipper.addView(iv)
                }
            }
            viewFlipper.startFlipping()
        }


        /*
        topicsService.getTopic().enqueue(object: Callback<Array<Topics>> {

            // 通信が失敗したときの処理
            override fun onFailure(call: Call<Array<Topics>>?, t: Throwable?) {
                // 今回は失敗したときは無視しています。
                Log.d(logTag,"通信エラー:$t")
            }

            // 通信が成功したときの処理
            override fun onResponse(call: Call<Array<Topics>>?, response: Response<Array<Topics>>) {

                // TODO response 20x以外の処理を追加する
                //topicsにデータを格納
                for(topic in response.body()!!){
                    topics.add(topic)
                }

                //ImageViewに初期画像をセット
                Glide.with(this@TopMenuActivity).load(topics[position].topic_image).into(imageView)

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
         */
    }

    private fun setTopicsImage(topics: Topics) {

    }

    /*
    //画像切り替えのメソッド
    private fun movePosition(move: Int, topics : MutableList<Topics> ){
        position += move
        //Positionが画像配列のサイズよりも大きい場合は0に戻す。
        if(position >= topics.size){
            position = 0
            //positionが0以下になった場合
        }else if(position < 0){
            position = topics.size - 1
        }

        //ImageViewに画像をセットし直す
         Glide.with(this).load(topics[position].topic_image).into(imageView)
    }
    */


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
