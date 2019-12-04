package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_sight_detail.*
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.sight.Sight
import jp.co.jalinfotec.soraguide.utils.Constants

class SightDetailActivity : AppCompatActivity() {
    companion object {
        const val SIGHT_DATA = "SIGHT_DATA"
    }
    private lateinit var sight: Sight

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sight_detail)

        sight = if (savedInstanceState == null) {
            intent.getSerializableExtra(SIGHT_DATA) as Sight
        } else {
            savedInstanceState.getSerializable(SIGHT_DATA) as Sight
        }

        sight.PhotoList?.get(0)?.URL?.let { url -> loadImage(url) }
        sight_name.text = sight.Title
        summary.text    = sight.Summary
        price.text      = sight.Price
        time.text       = sight.Time

        //API仕様上、値が空(nullではない)の補足処理
        if(price.text == ""){
            price.text = "※料金情報は公開されておりません"
        }
        if(time.text == ""){
            time.text = "※営業時間は公開されておりません"
        }

        val ido_notuse = intent.getStringExtra("latitude")//mapで使用する緯度
        val keido_notuse = intent.getStringExtra("Longitude")//mapで使用する経度

        //TODO:緯度経度がnullの場合もある？その場合の処理をここで記載する
        setSupportActionBar(toolbar_detail)//

        //緯度、経度はAPIの生の値だと使えないので加工する
        val ido_front = ido_notuse?.substring(0..1)
        val ido_back = ido_notuse?.substring(2)
        val ido = ido_front + "." + ido_back

        val keido_front = keido_notuse?.substring(0..2)
        val keido_back = keido_notuse?.substring(3)
        val keido = keido_front + "." + keido_back

        //googlemap案内 いまは高松空港発で固定にしている
        route_button.setOnClickListener {
            val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=34.215207,134.018567&destination=$ido,$keido")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            Log.d("maplog","mapのurlは$uri")
            startActivity(intent)
        }
    }

    private fun loadImage(url: String) {
        Glide.with(this).load("${Constants.RURUBU_URL}${Constants.RURUBU_STOAGE_URL}${url}").into(sightImage)
    }
}
