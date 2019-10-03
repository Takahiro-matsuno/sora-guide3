package jp.co.jalinfotec.soraguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.co.jalinfotec.soraguide.taxi.TaxiActivity
import kotlinx.android.synthetic.main.activity_top_menu.*

class TopMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        taxi_btn.setOnClickListener { startActivity(Intent(this, TaxiActivity::class.java)) }
        kanko_btn.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
    }
}
