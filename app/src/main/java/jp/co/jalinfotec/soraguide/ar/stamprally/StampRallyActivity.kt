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
import java.util.*
import kotlin.collections.ArrayList

class StampRallyActivity :
    BaseNavigationActivity(),
    StampRallyViewHolder.CallbackListener
{
    private val logTag = this::class.java.simpleName
    private lateinit var stampRallyAdapter: StampRallyAdapter

    override fun setToolbarTitle() {
        toolbar.title = resources.getString(R.string.stamp_rally)
    }

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

        stampRallyAdapter = StampRallyAdapter(this)

        stamp_rally_list.adapter = stampRallyAdapter
        stamp_rally_list.layoutManager = LinearLayoutManager(this)
        stamp_rally_list.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setStampRallyData()
        }
    }

    private fun setStampRallyData() {
        val list = ArrayList<StampRallyEntity>()
        list.add(StampRallyEntity(1L, "ほげほげを探せ！！！", Date() ,Date(), 3, 3))
        list.add(StampRallyEntity(2L, "ぴよぴよを探せ！！！", Date() ,Date(), 3, 1))
        list.add(StampRallyEntity(3L, "ふがふがを探せ！！！", Date() ,Date(), 3, 0))
        stampRallyAdapter.appendMembers(list)
    }

    override fun itemTapped(data:StampRallyEntity?) {
        //val data = stampRallyAdapter.findById(id)
        if (data != null) {
            // TODO 権限がない場合の処理を実装する
            Log.d(logTag, "アイテムタップ${data.name}")
            val intent = Intent(this, ARCameraActivity::class.java)
            intent.putExtra(ARCameraActivity.arResourceKey, "")
            intent.putExtra(ARCameraActivity.completeKey, data.done >= data.num)
            startActivity(intent)
        }
    }

    override fun couponTapped(data: StampRallyEntity?) {
        //val data = stampRallyAdapter.findById(id)
        if (data != null) {
            Log.d(logTag, "クーポンタップ:${data.name}")
        }

    }
}
