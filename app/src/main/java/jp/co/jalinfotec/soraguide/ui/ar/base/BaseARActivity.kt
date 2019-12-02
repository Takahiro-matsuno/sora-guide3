package jp.co.jalinfotec.soraguide.ui.ar.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.activity_ar.*
import org.json.JSONObject

abstract class BaseARActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
    }

    abstract fun loadArData()

    abstract fun updateArData(data: JSONObject)

    abstract fun saveArData()

    // 端末内データのアプリへの通知
    fun noticeJavaScript(funcName: String, vararg args: Any = arrayOf()) {
        var argStr = ""
        for (a in args) {
            // String型のときは「'」をつける
            val arg = if (a is String) "'$a'" else a
            argStr += if (argStr == "") arg else ", $arg"
        }

        // JavaScript呼び出し
        architectView.callJavascript("$funcName($argStr)")
    }
}