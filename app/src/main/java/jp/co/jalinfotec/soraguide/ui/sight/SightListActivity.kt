package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import jp.co.jalinfotec.soraguide.model.kanko.APIService
import jp.co.jalinfotec.soraguide.model.kanko.SightPage
import jp.co.jalinfotec.soraguide.model.kanko.Sight
import jp.co.jalinfotec.soraguide.ui.base.RecyclerClickListener
import kotlinx.android.synthetic.main.activity_sight_list.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import jp.co.jalinfotec.soraguide.R

class SightListActivity : AppCompatActivity(),
    SearchErrorDialog.CallbackListener {

    private val logTag = this::class.java.simpleName
    private val sampleTag = "RESULT_DIALOG"

    /**
     * ResultDialogのCallbackMethodを実装
     */
    override fun ok() {
        Log.d(logTag,  "callback_ok")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sight_list)

        setSupportActionBar(toolbar_result)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessException("Toolbar cannot be null")

        progress_bar.visibility = android.widget.ProgressBar.VISIBLE

        //intentの値受け取り
        val keyword= intent.getStringExtra("keyword")
        val ken= intent.getStringExtra("ken")
        val tachiyori= intent.getStringExtra("tachiyori")

        //retrofit用のパラメータ
        val baseApiUrl = "https://www.j-jti.com/"
        val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging).readTimeout(30,TimeUnit.SECONDS).connectTimeout(10,TimeUnit.SECONDS)

        //retrofitクライアント取得
        val retrofit = Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()

        //APIエンドポイント生成
        val api = retrofit.create(APIService::class.java)

        //TODO:最終的には消す！！ ↓ここはリクエスト結果確認用変数でごわす
        val request = api.getResponse(
            "jtzY6LZYK8226ibN",
            keyword,
            ken,
            tachiyori,
            100,
            "json"
        ).request().url().toString()

        //引数でapiエンドポイント指定、リクエスト
        api.getResponse("jtzY6LZYK8226ibN",keyword,ken,tachiyori,100,"json").enqueue(object :retrofit2.Callback<List<SightPage>>{
            override fun onFailure(call: Call<List<SightPage>>?, t: Throwable?) {
                //”検索中”を非表示へ
                progress_bar.visibility = android.widget.ProgressBar.GONE
                progress_text.visibility = android.widget.ProgressBar.GONE

                Log.d("TEST","取得失敗")
                Log.d("Test","throwable:$t")
                Log.d("GETかけたAPI：","$request")

                val dialog = SearchErrorDialog().newInstance(this@SightListActivity,"通信エラーです。\n通信環境が良い状態で再検索をお願いします。")
                dialog.show(supportFragmentManager, sampleTag)
            }
            override fun onResponse(call: Call<List<SightPage>>?, response: Response<List<SightPage>>) {
                //”検索中”を非表示へ
                progress_bar.visibility = android.widget.ProgressBar.GONE
                progress_text.visibility = android.widget.ProgressBar.GONE

                Log.d("TEST","取得せいこう！")

                if (response.isSuccessful){
                    val res = response.body()?:return
                    //ResponseAdapterへ渡すデータセットを作成
                    //TODO:取得データ全ページ分表示させる
                    //TODO:SwipeRefreshLayoutが使えるかも
                    val dataset:List<Sight>? = res[0].SightList?.filterNotNull()

                    if (dataset == null){
                            val dialog = SearchErrorDialog().newInstance(this@SightListActivity,"検索結果が0件でした。\n条件を変更し、再検索をお願いします。")
                            dialog.show(supportFragmentManager, sampleTag)
                    }else {
                        Toast.makeText(applicationContext,"検索結果は${res[0].TotalResults}件です",Toast.LENGTH_SHORT).show()
                    }

                    recycler_list.apply() {
                        //リストの罫線設定
                        addItemDecoration(
                            androidx.recyclerview.widget.DividerItemDecoration(
                                this@SightListActivity,
                                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
                            )
                        )
                        //生成したLinearLayoutManagerをセット
                        layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@SightListActivity)
                        //RecyclerViewの生成したResponseAdapter をセット
                        adapter = dataset?.let { it1 -> SightListAdapter(this@SightListActivity, it1) }
                    }
                    recycler_list.addOnItemTouchListener(
                        RecyclerClickListener(
                            this@SightListActivity,
                            object :
                                RecyclerClickListener.OnItemClickListener {
                                override fun onItemClick(view: View, position: Int) {
                                    val intent = Intent(this@SightListActivity, SightDetailActivity::class.java)
                                    //渡す引数のセット
                                    Log.d("mesh", "resultのmeshの値は$dataset[position].Mesh.name")
                                    intent.putExtra("title", dataset?.get(position)?.Title)
                                    intent.putExtra("address", dataset?.get(position)?.Address)
                                    intent.putExtra("time", dataset?.get(position)?.Time)
                                    intent.putExtra("image", dataset?.get(position)?.PhotoList?.get(0)?.URL)
                                    intent.putExtra("price", dataset?.get(position)?.Price)
                                    intent.putExtra("summary", dataset?.get(position)?.Summary)
                                    //                        intent.putExtra("mesh",dataset[position].Mesh.name)
                                    intent.putExtra("latitude", dataset?.get(position)?.Latitude)
                                    intent.putExtra("Longitude", dataset?.get(position)?.Longitude)

                                    startActivity(intent)
                                }
                            })
                    )
                }
            }
        })
    }
    //toolbarの戻るボタン押下時の戻る処理
    override fun onBackPressed() {
        // Do something
        super.onBackPressed()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
