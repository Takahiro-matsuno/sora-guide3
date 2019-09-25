package jp.co.jalinfotec.soraguide.ar.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.activity_ar.*

open class BaseARActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)
    }

    fun callJavaScript(funcName: String, vararg args: Any = arrayOf()) {
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