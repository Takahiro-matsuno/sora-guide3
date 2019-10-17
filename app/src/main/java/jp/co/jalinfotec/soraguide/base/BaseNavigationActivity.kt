package jp.co.jalinfotec.soraguide.base

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import jp.co.jalinfotec.soraguide.MainActivity
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.TopMenuActivity
import jp.co.jalinfotec.soraguide.ar.ARCameraActivity
import jp.co.jalinfotec.soraguide.taxi.TaxiActivity
import kotlinx.android.synthetic.main.activity_navigation.*

/**
 * Left Navigation抽象クラス
 */
abstract class BaseNavigationActivity:
    AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener
{

    abstract fun setMainContent()

    abstract fun setToolbarTitle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        setToolbarTitle()
        setMainContent()

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
    }

    /**
     * left navigation選択時の設定
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, TopMenuActivity::class.java))
                finish()
            }
            R.id.nav_airport -> { // todo 遷移先画面を設定する
                //startActivity(Intent(this, Activity::class.java))
                finish()
            }
            R.id.nav_flight -> { // todo 遷移先画面を設定する
                //startActivity(Intent(this, Activity::class.java))
                finish()
            }
            R.id.nav_tourism -> {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            R.id.nav_taxi -> {
                startActivity(Intent(this, TaxiActivity::class.java))
                finish()
            }
            R.id.nav_stamp_rally -> {
                startActivity(Intent(this, ARCameraActivity::class.java))
                finish()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
