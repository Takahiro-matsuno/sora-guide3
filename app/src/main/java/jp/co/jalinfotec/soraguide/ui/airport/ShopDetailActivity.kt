package jp.co.jalinfotec.soraguide.ui.airport

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.common.internal.ShowFirstParty
import jp.co.jalinfotec.soraguide.R

// todo ShopFragmentからの遷移を追加
class ShopDetailActivity : AppCompatActivity() {

    companion object {
        const val SHOP_DATA = "SHOP_DATA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_detail)
    }
}
