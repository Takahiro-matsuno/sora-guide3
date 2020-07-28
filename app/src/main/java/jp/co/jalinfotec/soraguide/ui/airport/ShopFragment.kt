package jp.co.jalinfotec.soraguide.ui.airport

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.airport.Shop
import jp.co.jalinfotec.soraguide.model.airport.ShopService
import jp.co.jalinfotec.soraguide.ui.base.RecyclerClickListener
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.fragment_airport.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShopFragment: Fragment() {

    private lateinit var adapter: ShopListAdapter
    private lateinit var shopService: ShopService
    private var isLoading = false

    fun newInstance(): ShopFragment {
        return ShopFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_airport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ShopListAdapter(context)
        shopRecyclerView.adapter = adapter
        // RecyclerViewの設定
        shopRecyclerView.layoutManager = LinearLayoutManager(context)
        shopRecyclerView.setHasFixedSize(true)
        shopRecyclerView.addOnItemTouchListener(
            RecyclerClickListener(
                context,
                object : RecyclerClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        /** todo ShopDetailActivityへの遷移を追加
                        val data = adapter.getItem(position)
                        val intent = Intent(context, ShopDetailActivity::class.jav
                        startActivity(intent)
                        */
                    }
                })
        )

        // 通信設定
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.resourceApiUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        shopService = retrofit.create(ShopService::class.java)
        loadShop()
    }

    private fun loadShop() {
        if (!isLoading) {
            isLoading = true
            shopService.getShop(Constants.airportCompanyId).enqueue(object: Callback<List<Shop>> {
                override fun onFailure(call: Call<List<Shop>>, t: Throwable) {
                    Toast.makeText(context, "通信エラーが発生しました", Toast.LENGTH_SHORT).show()
                    isLoading = false
                }

                override fun onResponse(call: Call<List<Shop>>, response: Response<List<Shop>>) {
                    when (response.code()) {
                        in 200..299 -> {
                            if (response.body() != null) {
                                adapter.appendMember(response.body()!!)
                            }
                        }
                        404 -> Toast.makeText(context, "検索結果が0件です", Toast.LENGTH_SHORT).show()
                        else -> Toast.makeText(context, "通信エラーが発生しました", Toast.LENGTH_SHORT).show()
                    }
                    isLoading = false
                }
            })
        }
    }
}
