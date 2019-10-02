package jp.co.jalinfotec.soraguide.taxi

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_navigation.*
import android.webkit.*
import androidx.constraintlayout.widget.Constraints
import jp.co.jalinfotec.soraguide.Constants
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.base.BaseNavigationActivity

class TaxiActivity :
    BaseNavigationActivity()
{
    private val logTag = this::class.java.simpleName
    private lateinit var webView: WebView
    private lateinit var cookieManager: CookieManager

    // toolbarタイトル設定
    override fun setToolbarTitle() {
        toolbar.title = resources.getString(R.string.taxi_reservation)
    }

    // main content設定
    override fun setMainContent() {
        webView = WebView(this)
        webView.layoutParams = Constraints.LayoutParams(
            Constraints.LayoutParams.MATCH_PARENT,
            Constraints.LayoutParams.MATCH_PARENT
        )
        main_content.addView(webView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // WebViewの設定
        initWebViewSetting()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    // Android標準ボタンの制御
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 戻るボタンを押された場合
        return if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            // ブラウザバックができる場合はブラウザバックする
            webView.goBack()
            true
        } else super.onKeyDown(keyCode, event) // ブラウザバックできない場合はActivityしTopMenuへ戻る
    }

    private fun initWebViewSetting() {
        // todo check https://appkitbox.com/knowledge/android/20130819-84
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient() // loadURLをwebViewで表示
        // cookieの有効化
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // V21未満ではブラウザのCookieと動機が必要
            CookieSyncManager.createInstance(applicationContext)
            CookieSyncManager.getInstance().startSync()
        }
        cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)

        // user-agent
        webView.settings.userAgentString += Constants.taxiUserAgent
        // webアクセス
        webView.loadUrl(Constants.taxiUrl)
    }
}
