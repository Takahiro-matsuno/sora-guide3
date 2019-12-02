package jp.co.jalinfotec.soraguide.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionUtil {

    fun requestPermission(context: Context): Array<String> {
        val permNameList = getPermissionNameList(context)
        val isNotAllowed = ArrayList<String>()
        if (permNameList != null) {
            for (permName in permNameList) {
                if (ContextCompat.checkSelfPermission(context, permName) != PackageManager.PERMISSION_GRANTED) {
                    // 許可がない場合は見許可リストに追加
                    isNotAllowed.add(permName)
                }
            }
        }
        return isNotAllowed.toTypedArray()
    }

    // マニフェストに設定された権限の一覧を返す
    private fun getPermissionNameList(context: Context): Array<String>? {
        return try {
            val pm = context.packageManager
            pm.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
        } catch (ex: PackageManager.NameNotFoundException) {
            null
        }
    }
}