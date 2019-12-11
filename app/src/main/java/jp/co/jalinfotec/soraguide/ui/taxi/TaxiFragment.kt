package jp.co.jalinfotec.soraguide.ui.taxi

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.fragment_taxi.*

class TaxiFragment: Fragment() {

    private val logTag = this::class.java.simpleName

    fun newInstance(): TaxiFragment {
        return TaxiFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_taxi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo check https://appkitbox.com/knowledge/android/20130819-84
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object: WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                // ロード開始時
                progressBar.visibility = View.VISIBLE
            }
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // ロード終了時
                progressBar.visibility = View.GONE
            }
        }
        // cookieの有効化
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // V21未満ではブラウザのCookieと動機が必要
            CookieSyncManager.createInstance(this.activity)
            CookieSyncManager.getInstance().startSync()
        }
        val cookieManager = CookieManager.getInstance()
        cookieManager.setAcceptCookie(true)

        // user-agent
        webView.settings.userAgentString += Constants.taxiUserAgent
    }

    override fun onStart() {
        super.onStart()
        webView.loadUrl(Constants.taxiUrl)
    }

    override fun onStop() {
        super.onStop()
        webView.stopLoading()
    }
}