package jp.co.jalinfotec.soraguide.ar

import android.content.Context
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.wikitude.architect.ArchitectStartupConfiguration
import com.wikitude.architect.ArchitectView
import jp.co.jalinfotec.soraguide.Constants
import jp.co.jalinfotec.soraguide.ar.base.ArchitectViewHolderInterface
import jp.co.jalinfotec.soraguide.ar.base.BaseARActivity
import kotlinx.android.synthetic.main.activity_ar.*
import java.io.IOException

class ARCameraActivity : BaseARActivity() {

    companion object {
        const val arResourceKey = "AR_RESOURCE"
    }

    private val logTag = this::class.java.simpleName
    // 遷移元取得データ
    private var arResource: String? = null

    /* AR 設定*/
    private var sensorAccuracyListener: ArchitectView.SensorAccuracyChangeListener? = null
    private var lastKnownLocation: Location? = null
    private var locationProvider: ArchitectViewHolderInterface.ILocationProvider? = null
    private var lastCalibrationToastShownTimeMillis = System.currentTimeMillis()

    /* 端末内保存データ */
    private var arData: String? = null  // AR用JSONデータ

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            // 遷移元からARデータを受け取る
            arResource = intent.getStringExtra(arResourceKey)
        } else {
            arResource = savedInstanceState.getString(arResourceKey, null)
        }

        if (arResource != null) {

            // Wikitudeの初期設定
            val config = ArchitectStartupConfiguration()
            config.licenseKey = Constants.wikitudeLicenseKey
            architectView.onCreate(config)
            // データをJSへ通知
            noticeJavaScript("setResourceObjectFn", arResource!!)

            // 方位トラッキングリスナー
            this.sensorAccuracyListener = this.getSensorAccuracyListener()

            // 位置情報トラッキングリスナー
            this.locationProvider = LocationProvider(this, object : LocationListener {
                // 位置情報が変更された時
                override fun onLocationChanged(location: Location?) {
                    // forward location updates fired by LocationProvider to architectView, you can set lat/lon from any location-strategy
                    if (location != null) {
                        // sore last location as member, in case it is needed somewhere (in e.g. your adjusted project)
                        // 位置をセット
                        this@ARCameraActivity.lastKnownLocation = location
                        if (this@ARCameraActivity.architectView != null) {
                            // check if location has altitude at certain accuracy level & call right architect method (the one with altitude information)
                            // 誤差が7m未満の時.hasAltitudeは標高。
                            // 位置情報をARのビューの位置情報に設定。これでJavaScriptで設定した。AR.context.onLocationChangedの関数が動く。
                            if (location.hasAltitude() && location.hasAccuracy() && location.accuracy < 7) {
                                this@ARCameraActivity.architectView.setLocation(
                                    location.latitude,
                                    location.longitude,
                                    location.altitude,
                                    location.accuracy
                                )
                            } else {
                                this@ARCameraActivity.architectView.setLocation(
                                    location.latitude,
                                    location.longitude,
                                    if (location.hasAccuracy()) location.accuracy.toDouble() else 1000.0
                                )
                            }
                        }
                    }
                }

                //
                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

                //
                override fun onProviderEnabled(p0: String?) {}

                //
                override fun onProviderDisabled(p0: String?) {}
            })

            // JSからの通知
            architectView.addArchitectJavaScriptInterfaceListener { jsonObj ->
                when (jsonObj.getString("type")) {
                    Constants.arCollectedDataKey -> {
                        updateArData(jsonObj.getString("data"))
                    }
                    else -> {
                        Log.d(logTag, "不正なJSONを受け取りました")
                    }
                }
            }

        } else {

            // データがない場合は画面を終了する
            Toast.makeText(this, "データの読み込みに失敗しました", Toast.LENGTH_SHORT).show()
            this@ARCameraActivity.finish()
        }
    }

    // onCreate直後に実行
    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        architectView.onPostCreate()
        try {
            architectView.load(Constants.wikitudeStampResourcePath)
        } catch (ex: IOException) {
            ex.printStackTrace()
            Toast.makeText(this,"index.htmlの読み込みでエラーが発生しました", Toast.LENGTH_SHORT).show()
            this.finish() // activityを終了する
        }
    }

    // 画面表示時に実行
    override fun onResume() {
        super.onResume()

        this.architectView?.onResume()
        if (this.sensorAccuracyListener != null) {
            this.architectView?.registerSensorAccuracyChangeListener(this.sensorAccuracyListener)
        }
        this.locationProvider?.onResume()
    }

    // 画面停止前に実行
    override fun onPause() {
        super.onPause()

        this.architectView?.onPause()
        if (this.sensorAccuracyListener != null) {
            this.architectView?.unregisterSensorAccuracyChangeListener(this.sensorAccuracyListener)
        }
        this.locationProvider?.onPause()

        saveArData()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (arResource != null) {
            outState.putString(arResourceKey, arResource)
        }
    }

    // Activity破棄前に実行
    override fun onDestroy() {
        super.onDestroy()

        // call mandatory live-cycle method of architectView
        if (this.architectView != null) {
            this.architectView.onDestroy()
        }
    }

    // センサーの精度が悪い時にワーニングを表示
    private fun getSensorAccuracyListener(): ArchitectView.SensorAccuracyChangeListener {
        return ArchitectView.SensorAccuracyChangeListener { accuracy ->
            /* UNRELIABLE = 0, LOW = 1, MEDIUM = 2, HIGH = 3 */
            if (accuracy < SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM && !this@ARCameraActivity.isFinishing && System.currentTimeMillis() - this@ARCameraActivity.lastCalibrationToastShownTimeMillis > 5 * 1000) {
                Toast.makeText(this, "Please re-calibrate compass by waving your device in a figure 8 motion.", Toast.LENGTH_LONG).show()
                this@ARCameraActivity.lastCalibrationToastShownTimeMillis = System.currentTimeMillis()
            }
        }
    }

    // 端末内からAR用JSONデータを取得
    override fun loadArData() {
        //SharedPreferenceから端末内データを取得する
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        this.arData = data.getString(Constants.arCollectedDataKey, "[false, false, false]")
    }

    // AR用のJSONデータを更新
    override fun updateArData(data: String) {
        Log.d(logTag, "データを更新しました:$data")
        this.arData = data
    }

    // 端末内へAR用JSONデータを保存
    override fun saveArData() {
        //SharedPreferenceへデータを保存する
        val data = getSharedPreferences("DataSave", Context.MODE_PRIVATE)
        val editor = data.edit()
        editor.putString(Constants.arCollectedDataKey ,this.arData)
        editor.apply()
    }
}
