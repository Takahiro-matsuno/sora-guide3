package jp.co.jalinfotec.soraguide

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

}