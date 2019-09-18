package jp.co.jalinfotec.soraguide.ar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.wikitude.architect.ArchitectStartupConfiguration
import jp.co.jalinfotec.soraguide.Constants
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.activity_ar.*
import java.io.IOException

class ARActivity : AppCompatActivity() {

    private var toast: Toast? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        // Android6対応。権限をアプリで実装しないとエラーになる。SDKのターゲットを23にしちゃうと必要。
        // requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.CAMERA}, 0);

        //wikitude初期設定
        val config = ArchitectStartupConfiguration()
        config.licenseKey = Constants.wikitudeLicenseKey
        architectView.onCreate(config)
        architectView.callJavascript("setSakura('" + "" + "')")

        architectView.addArchitectJavaScriptInterfaceListener {
            // todo javascriptからのコールバック処理
        }
    }

    //onCreate直後に実行
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        architectView.onPostCreate()
        try {
            architectView.load(Constants.wikitudeResourcePath)
        } catch (ex: IOException) {
            ex.printStackTrace()
            showToast("index.htmlの読み込みでエラーが発生しました", Toast.LENGTH_SHORT)
            this.finish() // activityを終了する
        }
    }

    override fun onResume() {
        super.onResume()
        this.architectView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.architectView?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        toast?.cancel()
        // call mandatory live-cycle method of architectView
        if (this.architectView != null) {
            this.architectView.onDestroy()
        }
    }

    private fun showToast(msg: String, length: Int) {
        toast = Toast.makeText(this, msg, length)
        toast!!.show()
    }
}
