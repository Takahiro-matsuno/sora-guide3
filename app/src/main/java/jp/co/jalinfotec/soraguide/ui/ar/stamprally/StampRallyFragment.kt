package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyEntity
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyRepository
import jp.co.jalinfotec.soraguide.ui.ar.ARCameraActivity
import jp.co.jalinfotec.soraguide.utils.PermissionUtil
import kotlinx.android.synthetic.main.activity_stamprally.*

class StampRallyFragment:
    Fragment(),
    StampRallyViewHolder.CallbackListener,
    CouponDialog.CallbackListener
{
    private val logTag = this::class.java.simpleName
    private lateinit var stampRallyRepository: StampRallyRepository
    private lateinit var stampRallyAdapter: StampRallyAdapter
    private val requestPermStampRally = 1
    private var currentEntity: StampRallyEntity? = null
    private val stampRallyBackupKey = "STAMP_RALLY_BACK_UP"
    private val couponDialogTag = "COUPON_DIALOG"

    fun newInstance(): StampRallyFragment {
        return StampRallyFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 初期化
        stampRallyRepository = StampRallyRepository(this.context!!)
        stampRallyAdapter = StampRallyAdapter(this.context!!, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stamp_rally, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerViewにアダプターを設定
        stamp_rally_list.adapter = stampRallyAdapter
        stamp_rally_list.layoutManager = LinearLayoutManager(this.context)
        stamp_rally_list.setHasFixedSize(true)

        if (savedInstanceState == null) {
            // 初回起動時のみデータを取得
            getStampRallyData()
        } else {
            val backupData = savedInstanceState.getSerializable(stampRallyBackupKey)
            if (backupData == null) {
                getStampRallyData()
            } else {
                @Suppress("UNCHECKED_CAST")
                stampRallyAdapter.appendMembers(backupData as ArrayList<StampRallyEntity>)
            }
        }
    }
    // 権限リクエスト
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            requestPermStampRally -> {
                if (grantResults.contains(-1)) {
                    // 権限が許可されなかった場合
                    Toast.makeText(this.context, "AR機能を利用するには権限の許可が必要です", Toast.LENGTH_SHORT).show()
                } else {
                    // 権限が許可された場合、ARCameraActivityに遷移する
                    startArContents()
                }
            }
            else -> { }
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
        val perms = PermissionUtil().requestPermission(this.context!!)
        if (perms.any()) {
            ActivityCompat.requestPermissions(this.activity!!, perms, requestPermStampRally)
        } else { startArContents() }
    }
    // クーポン確認タップ時
    override fun couponTapped(data: StampRallyEntity?) {
        data?.let { d -> showCouponDialog(d) }
    }
    // AR画面に遷移
    private fun startArContents() {
        if (currentEntity != null) {
            val intent = Intent(this.activity, ARCameraActivity::class.java)
            intent.putExtra(ARCameraActivity.arResourceKey, Gson().toJson(currentEntity))
            startActivity(intent)
        }
    }

    /**
     * Coupon Dialog
     */
    // ダイアログ表示
    private fun showCouponDialog(entity: StampRallyEntity) {
        val dialogFragment: CouponDialog = CouponDialog().newInstance(this, entity)
        dialogFragment.show(fragmentManager!!, couponDialogTag)
    }
    // ダイアログコールバック
    override fun useCoupon(entity : StampRallyEntity) {
        stampRallyAdapter.usedCoupon(entity)
    }
}