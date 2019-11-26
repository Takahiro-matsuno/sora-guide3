package jp.co.jalinfotec.soraguide.util

import android.Manifest

object Constants {

    val permissionMap = mapOf<String, String>(
        Manifest.permission.CAMERA to "",
        Manifest.permission.ACCESS_FINE_LOCATION to "",
        Manifest.permission.ACCESS_COARSE_LOCATION to "",
        Manifest.permission.WRITE_EXTERNAL_STORAGE to ""
    )
}