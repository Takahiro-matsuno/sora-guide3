package jp.co.jalinfotec.soraguide.ui.airport

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.airport.Shop
import jp.co.jalinfotec.soraguide.ui.base.RecyclerClickListener
import kotlinx.android.synthetic.main.fragment_airport.*

class ShopFragment: Fragment() {

    //TODO:DBからの取得に変更
    //店舗情報を格納する配列
    private var shopDataList: ArrayList<Shop> = ArrayList<Shop>()

    fun newInstance(): ShopFragment {
        return ShopFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_airport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // データ作成
        createShopDataList()

        //Adapterの作成
        val adapter = ShopListAdapter(this, shopDataList)


        // LayoutManagerの設定
        val layoutManager = LinearLayoutManager(context)
        // RecyclerViewにLayoutManagerを設定
        shopRecyclerView.layoutManager = layoutManager

        // RecyclerViewにAdapterを設定
        shopRecyclerView.adapter = adapter

        shopRecyclerView.addOnItemTouchListener(
            RecyclerClickListener(
                context,
                object : RecyclerClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val data = adapter.getItem(position)
                        val intent = Intent(context, ShopDetailActivity::class.java)
                        intent.putExtra(ShopDetailActivity.SHOP_DATA,data)
                        startActivity(intent)
                    }
                })
        )

    }


    private fun createShopDataList(){


        //1件目
        shopDataList.add(Shop(
            1,
            "おさかなショップ",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shoposakana, null)!!,
            "7:30～19:15",
            "空港売店",
            "087-814-3291",
            "香川県漁連の直営店として伊吹島のいりこやわかめ、また瀬戸内産上乾ちりめん等の水産加工品を販売しております。",
            "1F"
        ))

        //2件目
        shopDataList.add(Shop(
            2,
            "四季の里",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shopshikinosato, null)!!,
            "7:30～19:15",
            "空港売店",
            "087-814-3298",
            "JA香川県のアンテナショップとして開港以来、香川県の農畜産物の宣伝及び販売を目的として営業させて頂いております。",
            "1F"
        ))

        //3件目
        shopDataList.add(Shop(
            3,
            "はやし家製麺所",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shophayasiya, null)!!,
            "6:30～20:00 (Lo.19:45)",
            "飲食店",
            "087-879-0884",
            "常に茹でたての麺にこだわり、茹であがって３０分以上たった麺は使用せず、いつでも美味しい讃岐うどんを提供いたします。",
            "1F"
        ))

        //4件目
        shopDataList.add(Shop(
            2,
            "四国空市場",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shopsoraitiba, null)!!,
            "7:00～20:00",
            "土産店",
            "087-814-3552",
            "空港直営の「四国セレクトショップ」として19年8月にオープン！バイヤーが厳選した商品が勢揃いしています。",
            "2F"
        ))

        //5件目
        shopDataList.add(Shop(
            5,
            "エリエール",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shoperieru, null)!!,
            "6:15～19:50(Lo.19:20)",
            "飲食店",
            "087-814-3483",
            "お天気が良い日には店舗から朝日、夕日、夜景と高松中心が一望できます。",
            "2F"
        ))

        //6件目
        shopDataList.add(Shop(
            6,
            "CafeBuono",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shopbuno, null)!!,
            "6:15〜19:50(Lo.19:20)",
            "飲食店",
            "087-814-3496",
            "本格エスプレッソマシーン(Marzocco)を使用し、こだわりのカフェラテを提供しているcaféです！",
            "2F"
        ))

        //7件目
        shopDataList.add(Shop(
            2,
            "高松商運スカイショップ",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shopskyshop, null)!!,
            "7:00～19:50",
            "土産店",
            "087-814-3489",
            "四国香川の銘菓、銘うどんの他、豊富な品揃えでお待ちしております。",
            "2F"
        ))

        //8件目
        shopDataList.add(Shop(
            2,
            "skyJロビー店",
            ResourcesCompat.getDrawable(getResources(), R.drawable.shopskyj, null)!!,
            "7:00～19:50",
            "空港売店",
            "087-814-3419",
            "",
            "2F"
        ))

//        //X件目
//        shopDataList.add(Shop(
//            2,
//            "四季の里",
//            ResourcesCompat.getDrawable(getResources(), R.drawable.shopshikinosato, null)!!,
//            "7:30～19:15",
//            "空港売店",
//            "087-814-3298",
//            "",
//            "1F"
//        ))


//        for (i in 1..20){
//            shopDataList.add(Shop(
//                i,
//                "ショップ" + i.toString(),
//                ResourcesCompat.getDrawable(getResources(), R.drawable.shopimage, null)!!,
//                "9:00-17:00",
//                "レストラン",
//                "000-0000-0000",
//                "ショップの説明ショップの説明ショップの説明",
//                "2F"
//            ))
//        }
    }


}
