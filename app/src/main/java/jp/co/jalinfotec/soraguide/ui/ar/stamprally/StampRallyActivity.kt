package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.Constraints
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import jp.co.jalinfotec.soraguide.utils.PermissionUtil
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyEntity
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyRepository
import jp.co.jalinfotec.soraguide.ui.ar.ARCameraActivity
import jp.co.jalinfotec.soraguide.ui.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_stamprally.*

class StampRallyActivity :
    BaseNavigationActivity(),
    StampRallyViewHolder.CallbackListener,
    CouponDialog.CallbackListener
{

    private val logTag = this::class.java.simpleName
    private lateinit var stampRallyRepository: StampRallyRepository
    private lateinit var stampRallyAdapter: StampRallyAdapter
    private val requestPermStampRally = 1
    private var currentEntity: StampRallyEntity? = null
    private val stampRallyBackupKey = "STAMP_RALLY_BACK_UP"
    private val couponTag = "COUPON_DIALOG"

    // toolbarタイトル設定
    override fun setToolbarTitle() {
        toolbar.title = resources.getString(R.string.stamp_rally)
    }

    // main content設定
    @SuppressLint("InflateParams")
    override fun setMainContent() {
        val view = layoutInflater.inflate(R.layout.activity_stamprally, null)
        view.layoutParams = Constraints.LayoutParams(
            Constraints.LayoutParams.MATCH_PARENT,
            Constraints.LayoutParams.MATCH_PARENT
        )
        main_content.addView(view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初期化
        stampRallyRepository = StampRallyRepository(this)
        stampRallyAdapter = StampRallyAdapter(this)

        // RecyclerViewにアダプターを設定
        stamp_rally_list.adapter = stampRallyAdapter
        stamp_rally_list.layoutManager = LinearLayoutManager(this)
        stamp_rally_list.setHasFixedSize(true)

        if (savedInstanceState == null) {
            // 初回起動時のみデータを取得
            getStampRallyData()
        } else {
            val backupData = savedInstanceState.getSerializable(stampRallyBackupKey)
            if (backupData == null) {
                getStampRallyData()
            } else {
                Log.d(logTag, "バックアップ復元")
                @Suppress("UNCHECKED_CAST")
                stampRallyAdapter.appendMembers(backupData as ArrayList<StampRallyEntity>)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            requestPermStampRally -> {
                if (grantResults.contains(-1)) {
                    // 権限が許可されなかった場合
                    Toast.makeText(this, "AR機能を利用するには権限の許可が必要です", Toast.LENGTH_SHORT).show()
                } else {
                    // 権限が許可された場合
                    startArContents()
                }
            }
            else -> { }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 取得済みデータを保存する
        val backupData = stampRallyAdapter.getList()
        if (backupData.any()) {
            outState.putSerializable(stampRallyBackupKey, backupData)
        }
    }

    // スタンプラリーデータの取得
    private fun getStampRallyData() {
        Log.d(logTag, "スタンプラリーデータ取得")
        // TODO 仮のリポジトリ、サーバができたら置き換え
        val list = stampRallyRepository.getStampRallyData()
        if (list != null && list.any()) {
            stampRallyAdapter.appendMembers(list)
        }
    }

    /**
     * RecyclerView内ItemのClickListener
     */
    // スタンプラリーレイアウトタップ時
    override fun itemTapped(data: StampRallyEntity?) {
        currentEntity = data // 権限チェックのコールバック後に画面遷移するのでグローバルに保存
        // 権限チェック
        val perms = PermissionUtil().requestPermission(this)
        if (perms.any()) {
            ActivityCompat.requestPermissions(this, perms, requestPermStampRally)
        } else { startArContents() }
    }

    // クーポン確認タップ時
    override fun couponTapped(data: StampRallyEntity?) {
        if (data != null) {
            // CouponDialogのインスタンスを取得
            val dialogFragment: CouponDialog = CouponDialog().newInstance(this, data)
            dialogFragment.show(supportFragmentManager, couponTag)
        }
    }

    //クーポンを使用済みに設定する
    override fun useCoupon(entity : StampRallyEntity) {
        stampRallyAdapter.setUsed(entity)
    }

    // AR画面に遷移
    private fun startArContents() {
        if (currentEntity != null) {
            val intent = Intent(this, ARCameraActivity::class.java)
            intent.putExtra(ARCameraActivity.arResourceKey, Gson().toJson(currentEntity))
            startActivity(intent)
        }
    }

}
