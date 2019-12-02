package jp.co.jalinfotec.soraguide.ui.ar.base

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import jp.co.jalinfotec.soraguide.utils.Constants


class LocationProvider(
    /** the context in which we're running  */
    private val context: Context,
    /** location listener called on each location update  */
    private val locationListener: LocationListener?
) : ArchitectViewHolderInterface.ILocationProvider {

    companion object {

        /** location updates should fire approximately every second  */
        private const val LOCATION_UPDATE_MIN_TIME_GPS = 1000

        /** location updates should fire, even if last signal is same than current one (0m distance to last location is OK)  */
        private const val LOCATION_UPDATE_DISTANCE_GPS = 0

        /** location updates should fire approximately every second  */
        private const val LOCATION_UPDATE_MIN_TIME_NW = 1000

        /** location updates should fire, even if last signal is same than current one (0m distance to last location is OK)  */
        private const val LOCATION_UPDATE_DISTANCE_NW = 0

        /** to faster access location, even use 10 minute old locations on start-up  */
        private const val LOCATION_OUTDATED_WHEN_OLDER_MS = 1000 * 60 * 10
    }

    /** system's locationManager allowing access to GPS / Network position  */
    private val locationManager: LocationManager?

    /** is gpsProvider and networkProvider enabled in system settings  */
    private var gpsProviderEnabled: Boolean = false
    private var networkProviderEnabled: Boolean = false

    init {
        this.locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.gpsProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        this.networkProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    override fun onPause() {
        if (this.locationListener != null && this.locationManager != null && (this.gpsProviderEnabled || this.networkProviderEnabled)) {
            this.locationManager.removeUpdates(this.locationListener)
        }
    }

    override fun onResume() {
        if (this.locationManager != null && this.locationListener != null) {

            // check which providers are available are available
            this.gpsProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            this.networkProviderEnabled = this.locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            /** is GPS provider enabled?  */
            if (this.gpsProviderEnabled && checkPermission()) {
                val lastKnownGPSLocation = this.locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (lastKnownGPSLocation != null && lastKnownGPSLocation.time > System.currentTimeMillis() - LOCATION_OUTDATED_WHEN_OLDER_MS) {
                    locationListener.onLocationChanged(lastKnownGPSLocation)
                }
                if (locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
                    this.locationManager.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME_GPS.toLong(),
                        LOCATION_UPDATE_DISTANCE_GPS.toFloat(),
                        this.locationListener
                    )
                }
            }

            /** is Network / WiFi positioning provider available?  */
            if (this.networkProviderEnabled && checkPermission()) {
                val lastKnownNWLocation = this.locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (lastKnownNWLocation != null && lastKnownNWLocation.time > System.currentTimeMillis() - LOCATION_OUTDATED_WHEN_OLDER_MS) {
                    locationListener.onLocationChanged(lastKnownNWLocation)
                }
                if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null) {
                    this.locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        LOCATION_UPDATE_MIN_TIME_NW.toLong(),
                        LOCATION_UPDATE_DISTANCE_NW.toFloat(),
                        this.locationListener
                    )
                }
            }

            /** user didn't check a single positioning in the location settings, recommended: handle this event properly in your app, e.g. forward user directly to location-settings, new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS )  */
            if (!this.gpsProviderEnabled || !this.networkProviderEnabled) {
                Toast.makeText(
                    this.context,
                    "Please enable GPS and Network positioning in your Settings ",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkPermission(): Boolean {
        for (p in Constants.permissionMap.keys) {
            if (ContextCompat.checkSelfPermission(context, p) != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }
}