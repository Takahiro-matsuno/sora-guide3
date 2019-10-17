package jp.co.jalinfotec.soraguide

import android.Manifest

object Constants {

    /**
     * Taxi Reservation
     */
    val taxiUrl =
        if (BuildConfig.DEBUG) {
            "https://taxiapptest.azurewebsites.net/taxiReservation-0.1.2/app/registration"
        } else {
            "https://taxiapptest.azurewebsites.net/taxiReservation-0.1.2/app/registration"
        }
    const val taxiUserAgent = "sora-GuideApp"


    /**
     * AR
     */
    const val wikitudeLicenseKey = "IHuui6L8t2PPPXEmhFXdgjxEWXniJtjFDU85MGzN90bGfGFDEvlAA+v3JuZu7LnwmwsYZeDZrU1P+OWgzw9KEDCJD9QLOOs8N5VoKanyUUvvxSwAXX8mwM1Rpvy8CMseHqnbrdvKne3U15DazdsNmUkdvpgYSj60cpQGLxAdZG9TYWx0ZWRfXwDvG9unnDYINjkYPey1dppBrZ4gU5g410Lva2gdNdpBkoxt46X0avnC+8HnADazmGD3H14P5/LpIrXyUgeckNnIMRfwIIV/K3etW08ex726bzPT56UsPLZqRulrQP59lbznchj8MTFmAHihMmq63gB/+ph6VKrhImi3dj9Ngsnd37ktYpqllUWk/Ko7IofbCrDu2Lw9T9rKRsWCyrxEJ3dVm/JcAlBgfup2xX2j4pixBXxfY7rbiAYYUIYHsoz45Ie5Xmkg1j0647Z914LY24Fb3caWdCLpCLUzblhlS22SWgw+8P1Kxio3GSrNfOBKFYnFJUYNWiYfRnJNVOqPWTBuFQ4MjHJpm4pQr2OE+buIZ/ALRqlNzcL+CVpmxZnYxicLQv1YpWocmaYIVFRIfvxN+GEAR2Urf680kC1ylvmIMOYrTQXrZC7O1+TizTI24yL8Kn6Bl7xgVYOtmBlTcKnW1xNJhOfKDHKu2OYThDdKxG85WtJhuBoAfq0QPY5kWpLoOJ64QQTWGX2biO6Y/zx4QvJebgjBCAK4TpWddn3YyTHQaMS5Cn3tnmU1k5hbGM71Fms8Jh9T16BKEtNB+ZkLc8ktO85qzA=="
    const val wikitudeStampResourcePath = "wikitude/stamp-rally/index.html"
    val permissionMap = mapOf(
        Pair(Manifest.permission.CAMERA, "カメラ"),
        Pair(Manifest.permission.ACCESS_FINE_LOCATION, "位置情報"),
        Pair(Manifest.permission.ACCESS_COARSE_LOCATION, "位置情報"),
        Pair(Manifest.permission.WRITE_EXTERNAL_STORAGE, "ストレージ")
    )
    const val arCollectedDataKey = "AR_COLLECTED_DATA"
}