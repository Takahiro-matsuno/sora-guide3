package jp.co.jalinfotec.soraguide.utils

import android.Manifest
import jp.co.jalinfotec.soraguide.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

object Constants {

    const val airportCompanyId = 1L // todo 動的に取得するようにする

    /**
     * 共通
     */
    val permissionMap = mapOf(
        Pair(Manifest.permission.CAMERA, "カメラ"),
        Pair(Manifest.permission.ACCESS_FINE_LOCATION, "位置情報"),
        Pair(Manifest.permission.ACCESS_COARSE_LOCATION, "位置情報"),
        Pair(Manifest.permission.WRITE_EXTERNAL_STORAGE, "ストレージ")
    )
    val df = SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN)

    /**
     * 空港リソースAPI
     */
    const val resourceApiUrl = "https://soraguide-resource-api-develop.azurewebsites.net/"

    /**
     * タクシー予約
     */
    const val taxiUrl = "https://soraguide-taxi-reservation-develop.azurewebsites.net"
    const val taxiUserAgent = "sora-GuideApp"

    /**
     * AR
     */
    const val wikitudeLicenseKey = "IHuui6L8t2PPPXEmhFXdgjxEWXniJtjFDU85MGzN90bGfGFDEvlAA+v3JuZu7LnwmwsYZeDZrU1P+OWgzw9KEDCJD9QLOOs8N5VoKanyUUvvxSwAXX8mwM1Rpvy8CMseHqnbrdvKne3U15DazdsNmUkdvpgYSj60cpQGLxAdZG9TYWx0ZWRfXwDvG9unnDYINjkYPey1dppBrZ4gU5g410Lva2gdNdpBkoxt46X0avnC+8HnADazmGD3H14P5/LpIrXyUgeckNnIMRfwIIV/K3etW08ex726bzPT56UsPLZqRulrQP59lbznchj8MTFmAHihMmq63gB/+ph6VKrhImi3dj9Ngsnd37ktYpqllUWk/Ko7IofbCrDu2Lw9T9rKRsWCyrxEJ3dVm/JcAlBgfup2xX2j4pixBXxfY7rbiAYYUIYHsoz45Ie5Xmkg1j0647Z914LY24Fb3caWdCLpCLUzblhlS22SWgw+8P1Kxio3GSrNfOBKFYnFJUYNWiYfRnJNVOqPWTBuFQ4MjHJpm4pQr2OE+buIZ/ALRqlNzcL+CVpmxZnYxicLQv1YpWocmaYIVFRIfvxN+GEAR2Urf680kC1ylvmIMOYrTQXrZC7O1+TizTI24yL8Kn6Bl7xgVYOtmBlTcKnW1xNJhOfKDHKu2OYThDdKxG85WtJhuBoAfq0QPY5kWpLoOJ64QQTWGX2biO6Y/zx4QvJebgjBCAK4TpWddn3YyTHQaMS5Cn3tnmU1k5hbGM71Fms8Jh9T16BKEtNB+ZkLc8ktO85qzA=="
    const val wikitudeStampResourcePath = "wikitude/stamp-rally/index.html"

    /**
     * 観光案内
     */
    const val RURUBU_URL = "https://www.j-jti.com/"
    const val RURUBU_STOAGE_URL = "Storage/Image/Product/SightImage/S/"
    const val googleMap_URL="https://www.google.com/maps/dir/?api=1&origin=34.215207,134.018567&destination="
}
