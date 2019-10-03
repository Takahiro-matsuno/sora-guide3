package jp.co.jalinfotec.soraguide

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_result.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
/* ---------------------------------------------------------------------- */
/* 前画面から値受け取り                                                      */
/* ---------------------------------------------------------------------- */
        toolbar_detail.title  = intent.getStringExtra("title")//toolbarのタイトルに表示するため
        kankouti_name.text    = intent.getStringExtra("title")
        address.text          = intent.getStringExtra("address")
        time.text             = intent.getStringExtra("time")
        price.text            = intent.getStringExtra("price")
        summary.text          = intent.getStringExtra("summary")
//        val mesh_name  = intent.getStringExtra("mesh")
        val imageBasis = intent.getStringExtra("image")//表示するために加工するため変数に入れる
        val ido_notuse = intent.getStringExtra("latitude")//mapで使用する緯度
        val keido_notuse = intent.getStringExtra("Longitude")//mapで使用する経度

        //TODO:緯度経度がnullの場合もある？その場合の処理をここで記載する
        setSupportActionBar(toolbar_detail)//

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessException("Toolbar cannot be null")

        //緯度、経度はAPIの生の値だと使えないので加工する
        val ido_front = ido_notuse.substring(0..1)
        val ido_back = ido_notuse.substring(2)
        val ido = ido_front + "." + ido_back

        val keido_front = keido_notuse.substring(0..2)
        val keido_back = keido_notuse.substring(3)
        val keido = keido_front + "." + keido_back


        //API上、値が空(nullではない)の場合の補足
        if(price.text == ""){
            price.text = "※料金情報は公開されておりません"
        }
        if(time.text == ""){
            time.text = "※営業時間は公開されておりません"
        }

        //画像イメージ表示の処理
        val image = "https://www.j-jti.com/Storage/Image/Product/SightImage/S/$imageBasis"
        val options = RequestOptions().centerCrop()

        Glide.with(this).load(image).apply(options).into(image_view)//nullのPhotolistも含まれるためこんな書き方


        //googlemap案内 いまは高松空港発で固定にしている
        route_button.setOnClickListener {
            val uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=34.215207,134.018567&destination=$ido,$keido")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            Log.d("maplog","mapのurlは$uri")
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        // Do something
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
