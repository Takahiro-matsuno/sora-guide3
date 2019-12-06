package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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

class SightFragment: Fragment(),SightSearchDialog.CallbackListener,SearchErrorDialog.CallbackListener {
    private lateinit var adapter: SightListAdapter
    private lateinit var rurubuService: RurubuService

    private var isShowDialog = true

    private var isSearching = false
    private val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging).readTimeout(30,
        TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)

    fun newInstance(): SightFragment {
        return SightFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Adapter
        adapter = SightListAdapter(context)
        fragment_sightRecyclerView.adapter = adapter
        fragment_sightRecyclerView.layoutManager = LinearLayoutManager(context)
        fragment_sightRecyclerView.setHasFixedSize(true)
        fragment_sightRecyclerView.addOnItemTouchListener(
            RecyclerClickListener(
                context,
                object : RecyclerClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val data = adapter.getItem(position)
                        val intent = Intent(context, SightDetailActivity::class.java)
                        intent.putExtra(SightDetailActivity.SIGHT_DATA,data)
                        startActivity(intent)
                    }
                })
        )
        // 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.RURUBU_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()

        rurubuService = retrofit.create(RurubuService::class.java)

        if (isShowDialog){
            //初回起動時にダイアログボックスを開くように
            val dialog = SightSearchDialog().newInstance(this)
            dialog.show(fragmentManager!!, "SEARCH_DIALOG")
            isShowDialog = false
        }else{
            //初回起動時以外は何もしない
            return
        }

        search_button.setOnClickListener{//画面上の検索用ボタン押下で検索ダイアログ出力
            val dialog = SightSearchDialog().newInstance(this)
            dialog.show(fragmentManager!!, "SEARCH_DIALOG")
        }
    }

    override fun onResume() {//activityが表示された時に動く処理
        super.onResume()
        updateSightProgressView()
    }
    private fun updateSightProgressView() {
        // 検索結果テキスト
        sightWelcomeText1.visibility = if (adapter.itemCount == 0 ) View.VISIBLE else View.GONE
        sightWelcomeText2.visibility = if (adapter.itemCount == 0 ) View.VISIBLE else View.GONE
        // プログレス
        if (isSearching) {
            sight_progressBar.visibility = View.VISIBLE
            sight_progressText.visibility = View.VISIBLE
        } else {
            sight_progressBar.visibility = View.GONE
            sight_progressText.visibility = View.GONE
        }
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
                        Toast.makeText(context, "通信失敗", Toast.LENGTH_SHORT).show()
                        // 通信中解除
                        isSearching = false
                        updateSightProgressView()
                    }
                    // 通信成功
                    override fun onResponse(call: Call<List<SightPage>>, rurubuResponse: Response<List<SightPage>>) {
                        if (rurubuResponse.isSuccessful && rurubuResponse.body() != null) {
                            val data = rurubuResponse.body()!![0].SightList
                            if (data.isNullOrEmpty()) Toast.makeText(context, "検索結果:0件", Toast.LENGTH_SHORT).show()
                            else adapter.appendMember(data)
                        } else Toast.makeText(context, "検索結果:0件", Toast.LENGTH_SHORT).show()
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
