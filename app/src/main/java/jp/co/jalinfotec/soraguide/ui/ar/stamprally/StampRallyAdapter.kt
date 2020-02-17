package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.model.stamprally.StampRally

class StampRallyAdapter(
    private val context: Context,
    private val listener: StampRallyViewHolder.CallbackListener
): RecyclerView.Adapter<StampRallyViewHolder>()
{
    private var dataList = ArrayList<StampRally>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampRallyViewHolder {
        return StampRallyViewHolder(parent).newInstance(context, parent, listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: StampRallyViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    /**
     * リストの取得
     */
    fun getList(): ArrayList<StampRally> {
        return dataList
    }

    /**
     * リストの追加
     */
    // 複数追加
    fun appendMembers(newList: List<StampRally>) {
        val pos = dataList.size
        val range = newList.size
        for (d in newList) {
            dataList.add(d)
        }
        this@StampRallyAdapter.notifyItemRangeInserted(pos, range)
    }

    // クーポンを使用済みにする
    fun usedCoupon(entity : StampRally){
        val position = dataList.indexOf(entity)
        //dataListに対象のentityが含まれている場合
        if (position != -1 ){
            entity.isCouponUsed = true
            this.notifyItemChanged(position)
        }
    }
}