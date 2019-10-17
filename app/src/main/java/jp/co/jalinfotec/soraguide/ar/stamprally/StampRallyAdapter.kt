package jp.co.jalinfotec.soraguide.ar.stamprally

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class StampRallyAdapter(
    private val listener: StampRallyViewHolder.CallbackListener
): RecyclerView.Adapter<StampRallyViewHolder>()
{
    private var dataList = ArrayList<StampRallyEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampRallyViewHolder {
        return StampRallyViewHolder(parent).newInstance(parent, listener)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: StampRallyViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    /**
     * リストの追加
     */
    // 複数追加
    fun appendMembers(newList: ArrayList<StampRallyEntity>) {
        val pos = dataList.size
        val range = newList.size
        for (d in newList) {
            dataList.add(d)
        }
        this@StampRallyAdapter.notifyItemRangeInserted(pos, range)
    }

    /**
     * リストの検索

    fun findById(id: Long): StampRallyEntity? {
        for (d in dataList) {
            if (d.id == id) return d
        }
        return null
    }
    */
}