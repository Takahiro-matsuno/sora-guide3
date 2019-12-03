package jp.co.jalinfotec.soraguide.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.ui.ar.stamprally.StampRallyFragment
import jp.co.jalinfotec.soraguide.ui.sight.SightFragment
import jp.co.jalinfotec.soraguide.ui.taxi.TaxiFragment
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity:
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener
{

    private lateinit var mNavigationType: NavigationType

    enum class NavigationType {
        SIGHT,
        TAXI,
        STAMP_RALLY
        // FLIGHT フライト情報
        // AIRPORT 施設案内
    }

    companion object {
        const val NAVIGATION_KEY = "NAVIGATION_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        // Navigation Drawerの設定
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)

        // Main Contentの設定
        if (savedInstanceState == null) { // 初回起動時
            changeMainContent(intent.getSerializableExtra(NAVIGATION_KEY) as NavigationType)
        } else { // 復帰時
            changeMainContent(savedInstanceState.getSerializable(NAVIGATION_KEY) as NavigationType)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(NAVIGATION_KEY, mNavigationType)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> this.finish()
            R.id.nav_airport -> { /* todo 遷移先画面を設定する */ }
            R.id.nav_flight -> { /* todo 遷移先画面を設定する */ }
            R.id.sight -> changeMainContent(NavigationType.SIGHT)
            R.id.nav_taxi -> changeMainContent(NavigationType.TAXI)
            R.id.nav_stamp_rally -> changeMainContent(NavigationType.STAMP_RALLY)
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // メインコンテンツの切り替え
    private fun changeMainContent(navigationType: NavigationType) {
        mNavigationType = navigationType
        val preFragment = supportFragmentManager.findFragmentById(R.id.main_content)
        val aftFragment = when (mNavigationType) {
            NavigationType.SIGHT -> {
                if (preFragment is SightFragment) null else {
                    toolbar.title = resources.getString(R.string.sight_info)
                    SightFragment().newInstance()
                }
            }
            NavigationType.TAXI -> {
                if (preFragment is TaxiFragment) null else {
                    toolbar.title = resources.getString(R.string.taxi_reservation)
                    TaxiFragment().newInstance()
                }
            }
            NavigationType.STAMP_RALLY -> {
                if (preFragment is StampRallyFragment) null else {
                    toolbar.title = resources.getString(R.string.stamp_rally)
                    StampRallyFragment().newInstance()
                }
            }
        }
        aftFragment?.let { f -> supportFragmentManager.beginTransaction().replace(R.id.main_content, f).commit() }
    }
}