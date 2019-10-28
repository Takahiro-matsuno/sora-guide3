package jp.co.jalinfotec.soraguide.ar.stamprally

import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.constraintlayout.widget.Constraints
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.ar.ARCameraActivity
import jp.co.jalinfotec.soraguide.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_stamprally.*

// TODO クーポン確認タップ時にQR表示ダイアログを表示
// TODO 起動時に操作説明ダイアログを表示
class StampRallyActivity :
    BaseNavigationActivity(),
    StampRallyViewHolder.CallbackListener,
    StampDialog.CallbackListener
{

    override fun ok() {
        // OKボタン押下の処理
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,"OKボタン",Toast.LENGTH_LONG).show()
    }

    override fun cancel() {
        // Negativeボタン押下時の処理
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Toast.makeText(this,"cancelボタン",Toast.LENGTH_LONG).show()
    }

    private val logTag = this::class.java.simpleName
    private val stampRallyBackupKey = "STAMP_RALLY_BACK_UP"
    private lateinit var stampRallyRepository: StampRallyRepository
    private lateinit var stampRallyAdapter: StampRallyAdapter
    private var currentEntity: StampRallyEntity? = null
    private val REQUEST_STAMP_RALLY = 1

    private val stampTag = "DIALOG"

    // toolbarタイトル設定
    override fun setToolbarTitle() {
        toolbar.title = resources.getString(R.string.stamp_rally)
    }

    // main content設定
    override fun setMainContent() {
        val view = layoutInflater.inflate(R.layout.activity_stamprally, null)
        view.layoutParams = Constraints.LayoutParams(
            Constraints.LayoutParams.MATCH_PARENT,
            Constraints.LayoutParams.MATCH_PARENT
        )
        main_content.addView(view)
    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        stampRallyRepository = StampRallyRepository(this)
        stampRallyAdapter = StampRallyAdapter(this)

        // RecyclerViewにアダプターを設定
        stamp_rally_list.adapter = stampRallyAdapter
        stamp_rally_list.layoutManager = LinearLayoutManager(this)
        stamp_rally_list.setHasFixedSize(true)

        if (savedInstanceState == null) {
            // 初回起動時のみデータを取得
            setStampRallyData()
        } else {
            // Activity再起動時はbackupしたデータを取得
            val backupData = savedInstanceState.getString(stampRallyBackupKey)
            if (backupData != null) {
                val type = object : TypeToken<List<StampRallyEntity>>() {}.type
                stampRallyAdapter.appendMembers(Gson().fromJson(backupData, type) as ArrayList<StampRallyEntity>)
            } else {
                // バックアップなしの場合
                setStampRallyData()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // 取得済みデータを保存する
        // Activity側にlistを持ちたくないのでJsonで受け取る
        val backupData = stampRallyAdapter.getBackupData()
        if (backupData != null ) {
            outState.putString(stampRallyBackupKey, backupData)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_STAMP_RALLY -> {
                if (grantResults.contains(-1)) {
                    // 権限が許可されなかった場合
                    Toast.makeText(this, "AR機能を利用するには権限の許可が必要です", Toast.LENGTH_SHORT).show()
                } else {
                    // 権限が許可された場合
                    if (currentEntity != null) {
                        val intent = Intent(this, ARCameraActivity::class.java)
                        intent.putExtra(ARCameraActivity.arResourceKey, "")
                        startActivity(intent)
                    }
                }
            }
            else -> { }
        }
    }

    private fun setStampRallyData() {
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
    override fun itemTapped(data:StampRallyEntity?) {
        currentEntity = data
        requestPermission(this, REQUEST_STAMP_RALLY)
    }

    // クーポン確認タップ時
    override fun couponTapped(data: StampRallyEntity?) {
        //val data = stampRallyAdapter.findById(id)
        if (data != null) {
            Log.d(logTag, "クーポンタップ:${data.stampRallyName}")
            couponDialog(data.couponUri)
        }
    }

    private fun requestPermission(context: Context, requestCode: Int) {
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
        if (isNotAllowed.any()) {
            // 未許可リストのリクエストする
            ActivityCompat.requestPermissions(this, isNotAllowed.toTypedArray(), requestCode)
        }
    }

    // マニフェストに設定された権限の一覧を返す
    private fun getPermissionNameList(context: Context): Array<String>? {
        return try {
            val pm = context.packageManager
            packageManager.getPackageInfo(context.packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
        } catch (ex: PackageManager.NameNotFoundException) {
            null
        }
    }

    //ダイアログを出す
    fun couponDialog(couponURI: String){
        // ダイアログのフラグメントのインスタンスを取得
        val dialogFragment: StampDialog = StampDialog().newInstance(this, couponURI)
        dialogFragment.show(supportFragmentManager, stampTag)
    }
}
