package jp.co.jalinfotec.soraguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.co.jalinfotec.soraguide.taxi.TaxiActivity
import jp.co.jalinfotec.soraguide.ar.ARCameraActivity
import kotlinx.android.synthetic.main.activity_top_menu.*
import jp.co.jalinfotec.soraguide.R

class TopMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)


        taxi_btn.setOnClickListener { startActivity(Intent(this, TaxiActivity::class.java)) }
        kanko_btn.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        ar_btn.setOnClickListener { startActivity(Intent(this, ARCameraActivity::class.java)) }
        // todo リリース前に消す
        arClear_btn.setOnClickListener {
            val intent = Intent(this, ARCameraActivity::class.java)
            intent.putExtra(ARCameraActivity.clearFlgKey, true)
            startActivity(intent)
        }
    }
}
