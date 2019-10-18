package jp.co.jalinfotec.soraguide.ar.stamprally

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.Constraints
import androidx.recyclerview.widget.LinearLayoutManager
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.ar.ARCameraActivity
import jp.co.jalinfotec.soraguide.base.BaseNavigationActivity
import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.activity_stamprally.*

// TODO クーポン確認タップ時にQR表示ダイアログを表示
// TODO 起動時に操作説明ダイアログを表示
class StampRallyActivity :
    BaseNavigationActivity(),
    StampRallyViewHolder.CallbackListener
{
    private val logTag = this::class.java.simpleName
    private lateinit var stampRallyRepository: StampRallyRepository
    private lateinit var stampRallyAdapter: StampRallyAdapter

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
        //val data = stampRallyAdapter.findById(id)
        if (data != null) {
            // TODO アプリの権限チェック
            Log.d(logTag, "アイテムタップ${data.stampRallyName}")
            val intent = Intent(this, ARCameraActivity::class.java)
            intent.putExtra(ARCameraActivity.arResourceKey, "")
            startActivity(intent)
        }
    }
    // クーポン確認タップ時
    override fun couponTapped(data: StampRallyEntity?) {
        //val data = stampRallyAdapter.findById(id)
        if (data != null) {
            Log.d(logTag, "クーポンタップ:${data.stampRallyName}")
        }
    }
}
