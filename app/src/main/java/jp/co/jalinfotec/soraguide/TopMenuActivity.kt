package jp.co.jalinfotec.soraguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.co.jalinfotec.soraguide.ar.StampRallyActivity
import kotlinx.android.synthetic.main.activity_top_menu.*

class TopMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        ar_btn.setOnClickListener { startActivity(Intent(this, StampRallyActivity::class.java)) }
        // todo リリース前に消す
        arClear_btn.setOnClickListener {
            val intent = Intent(this, StampRallyActivity::class.java)
            intent.putExtra(StampRallyActivity.clearFlgKey, true)
            startActivity(intent)
        }
    }
}
