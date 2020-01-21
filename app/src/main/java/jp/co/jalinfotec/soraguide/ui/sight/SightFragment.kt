package jp.co.jalinfotec.soraguide.ui.sight

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private val searchDialogTag = "SEARCH_DIALOG"
    private val errorDialogTag = "ERROR_DIALOG"

    private lateinit var adapter: SightListAdapter
    // 通信用
    private var isSearching = false
    private lateinit var rurubuService: RurubuService
    private lateinit var rurubuRequest: Call<List<SightPage>>
    private val httpLogging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClientBuilder = OkHttpClient.Builder().addInterceptor(httpLogging)
        .readTimeout(30, TimeUnit.SECONDS).connectTimeout(10, TimeUnit.SECONDS)

    // 検索項目用
    private var spring = "0,1,2"
    private var summer = "0,1,2"
    private var autumn = "0,1,2"
    private var winter = "0,1,2"
    private var inspiteOfRain = "0,1,2"

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
        // RecyclerViewの設定
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
        // 画面上の検索用ボタン押下で検索ダイアログ出力
        search_button.setOnClickListener{ showSearchDialog() }

        // 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.RURUBU_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClientBuilder.build())
            .build()
        rurubuService = retrofit.create(RurubuService::class.java)

        updateSightProgressView()
    }

    private fun updateSightProgressView() {
        // 検索結果テキスト
        sightDefaultText.visibility = if (adapter.itemCount == 0 ) View.VISIBLE else View.GONE
        // プログレス
        if (isSearching) {
            sight_progressBar.visibility = View.VISIBLE
            sight_progressText.visibility = View.VISIBLE
            sight_caution.visibility = View.VISIBLE
        } else {
            sight_progressBar.visibility = View.GONE
            sight_progressText.visibility = View.GONE
            sight_caution.visibility = View.GONE
        }
    }

    override fun onStop() {
        super.onStop()
        if (isSearching) rurubuRequest.cancel()
    }

    /**
     * 検索ダイアログ
     */
    // 表示
    private fun showSearchDialog() {
        if (fragmentManager!!.findFragmentByTag(searchDialogTag) == null) {
            val dialog = SightSearchDialog().newInstance(this)
            dialog.show(fragmentManager!!, searchDialogTag)
        }
    }
    // コールバック・検索開始
    override fun search(ken: String, keyword: String, tachiyori: String,season:String,rain:String) {
        when(season){//検索時、季節に指定があれば検索パラメータを修正
            "春" -> spring = "0"
            "夏" -> summer = "0"
            "秋" -> autumn = "0"
            "冬" -> winter = "0"
        }

        if (!isSearching) {
            adapter.removeAllMember() // アダプターの要素を削除
            isSearching = true
            updateSightProgressView()

            sightDefaultText.visibility = View.GONE

            rurubuRequest = rurubuService.getResponse("jtzY6LZYK8226ibN", keyword, ken, tachiyori, spring, summer, autumn, winter,inspiteOfRain,20, "json")
            rurubuRequest.enqueue(object : Callback<List<SightPage>> {
                // 通信失敗
                override fun onFailure(call: Call<List<SightPage>>, t: Throwable) {
                    isSearching = false
                    if (!call.isCanceled) {
                        val dialog = SearchErrorDialog().newInstance(this@SightFragment,"通信エラーです。\n通信環境が良い状態で再検索をお願いします。")
                        dialog.show(fragmentManager!!, errorDialogTag)
                        // 通信中解除
                        updateSightProgressView()
                    }
                }
                // 通信成功
                @SuppressLint("NewApi")
                override fun onResponse(call: Call<List<SightPage>>, rurubuResponse: Response<List<SightPage>>) {
                    if (rurubuResponse.isSuccessful && rurubuResponse.body() != null) {
                        val data = rurubuResponse.body()!![0].SightList
                        if (data.isNullOrEmpty()) {
                            val dialog = SearchErrorDialog().newInstance(this@SightFragment,"検索結果が0件でした。\n再検索をお願いします。")
                            dialog.show(fragmentManager!!, errorDialogTag)
                        }
                        else{
                            adapter.appendMember(data)
                            Toast.makeText(context, "検索結果:${data.size}件", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        val dialog = SearchErrorDialog().newInstance(this@SightFragment,"検索結果が0件でした。\n再検索をお願いします。")
                        dialog.show(fragmentManager!!, errorDialogTag)
                    }
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
