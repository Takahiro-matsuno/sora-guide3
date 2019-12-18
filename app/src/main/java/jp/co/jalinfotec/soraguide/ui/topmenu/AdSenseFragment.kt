package jp.co.jalinfotec.soraguide.ui.topmenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.fragment_adsense.*

class AdSenseFragment: Fragment() {

    fun newInstance(): AdSenseFragment {
        return AdSenseFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_adsense, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Admobの設定
         * // TODO アプリをgoogleへ提出し、審査を受ける
         * Caused by: com.google.android.gms.dynamite.DynamiteModule$LoadingException:
         *      Failed to instantiate module class: com.google.android.gms.ads.omid.DynamiteOmid
         */
        MobileAds.initialize(this.context, "ca-app-pub-2003234893806822~5059310598")
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }


}