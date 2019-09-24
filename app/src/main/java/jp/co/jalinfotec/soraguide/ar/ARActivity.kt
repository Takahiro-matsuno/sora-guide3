package jp.co.jalinfotec.soraguide.ar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wikitude.architect.ArchitectStartupConfiguration
import jp.co.jalinfotec.soraguide.Constants
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.activity_ar.*
import org.json.JSONObject
import java.io.IOException

class ARActivity : AppCompatActivity() {

    private val logTag = this::class.java.simpleName
    private var toast: Toast? = null
    private val hanaCollection = mutableMapOf<String, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ar)

        loadHanaCollection()

        // wikitude初期設定
        val config = ArchitectStartupConfiguration()
        config.licenseKey = Constants.wikitudeLicenseKey
        architectView.onCreate(config)

        architectView.addArchitectJavaScriptInterfaceListener { jObj ->
            when (jObj.getString("type")) {
                "hanaCollection" -> {
                    val data = jObj.getJSONObject("data")
                    updateHanaCollection(data)
                }
                else -> {}
            }
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
        var args = ""
        for (hana in hanaCollection.values) {
            args += if (args != "") ", $hana" else "$hana"
        }
        architectView.callJavascript("sakuraAtsume.set($args)")
        this.architectView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        this.architectView?.onPause()
        saveHanaCollection()
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

    private fun loadHanaCollection() {
        Log.d(logTag, "loadHanaCollection")
        hanaCollection["hana3"] = false
        hanaCollection["hana4"] = true
        hanaCollection["hana5"] = false
    }

    private fun updateHanaCollection(data: JSONObject) {
        Log.d(logTag, "updateHanaCollection")
        val key = data.getString("hanaName")
        val value = data.getBoolean("hanaFlg")
        hanaCollection[key] = value
    }

    private fun saveHanaCollection() {
        Log.d(logTag, "saveHanaCollection")
        hanaCollection
    }
}
