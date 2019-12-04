package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_sigth_search.*
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.sight.RurubuService
import jp.co.jalinfotec.soraguide.model.sight.SightPage
import jp.co.jalinfotec.soraguide.ui.base.RecyclerClickListener
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.fragment_sight.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class SightSearchActivity : AppCompatActivity(),SightSearchDialog.CallbackListener,SearchErrorDialog.CallbackListener {
    private lateinit var adapter: SightListAdapter
    private lateinit var rurubuService: RurubuService
    private var isSearching = false
    private val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging).readTimeout(30,
        TimeUnit.SECONDS).connectTimeout(10,TimeUnit.SECONDS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigth_search)

        //toolbar
        val sightToolbar = findViewById<Toolbar>(R.id.sightToolbar)
        sightToolbar.title = "観光案内"
        setSupportActionBar(sightToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Adapter
        adapter = SightListAdapter(this)
        sightRecyclerView.adapter = adapter
        sightRecyclerView.layoutManager = LinearLayoutManager(this)
        sightRecyclerView.setHasFixedSize(true)
        sightRecyclerView.addOnItemTouchListener(RecyclerClickListener(
            this,
            object : RecyclerClickListener.OnItemClickListener {
                override fun onItemClick(view: View, position: Int) {
                    val data = adapter.getItem(position)
                    val intent = Intent(this@SightSearchActivity, SightDetailActivity::class.java)
//                    intent.putExtra(SightDetailActivity.SIGHT_DATA,data)
                    startActivity(intent)
                }
            }))
        // 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.RURUBU_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()

        rurubuService = retrofit.create(RurubuService::class.java)

    }
    override fun onResume() {//activityが表示された時に動く処理
        super.onResume()
        updateSightProgressView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_sight_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_search -> showSearchDialog()
            else -> {}
        }
        return true
    }

    private fun updateSightProgressView() {
        // 検索結果テキスト
        sightResultText.visibility = if (adapter.itemCount == 0 ) View.VISIBLE else View.GONE
        // プログレス
        if (isSearching) {
            sight_progressBar.visibility = View.VISIBLE
            sight_progressText.visibility = View.VISIBLE
        } else {
            sight_progressBar.visibility = View.GONE
            sight_progressText.visibility = View.GONE
        }
    }
    /**
     * 検索条件ダイアログ
     */
    // 表示
    private fun showSearchDialog() {
        val dialog = SightSearchDialog().newInstance(this)
        dialog.show(supportFragmentManager, "SEARCH_DIALOG")
    }


    // コールバック・検索開始
    override fun search(ken: String, keyword: String, tachiyori: String) {
        if (!isSearching) {
            adapter.removeAllMember() // アダプターの要素を削除
            isSearching = true
            updateSightProgressView()

            rurubuService.getResponse("jtzY6LZYK8226ibN", keyword, ken, tachiyori, 20,"json")
                .enqueue(object : Callback<List<SightPage>> {
                    // 通信失敗
                    override fun onFailure(call: Call<List<SightPage>>, t: Throwable) {
                        Toast.makeText(this@SightSearchActivity, "通信失敗", Toast.LENGTH_SHORT).show()
                        // 通信中解除
                        isSearching = false
                        updateSightProgressView()
                    }
                    // 通信成功
                    override fun onResponse(call: Call<List<SightPage>>, rurubuResponse: Response<List<SightPage>>) {
                        if (rurubuResponse.isSuccessful && rurubuResponse.body() != null) {
                            val data = rurubuResponse.body()!![0].SightList
                            if (data.isNullOrEmpty()) Toast.makeText(this@SightSearchActivity, "検索結果:0件", Toast.LENGTH_SHORT).show()
                            else adapter.appendMember(data)
                        } else Toast.makeText(this@SightSearchActivity, "検索結果:0件", Toast.LENGTH_SHORT).show()
                        // 通信中解除
                        isSearching = false
                        updateSightProgressView()
                    }
                })
        }
    }
    override fun errorcoll() {
    }
}
