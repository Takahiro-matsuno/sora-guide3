package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

import jp.co.jalinfotec.soraguide.model.sight.SightPage

class SightListAdapter(private val context:Context?): RecyclerView.Adapter<SightViewHolder>() {
    private val dataList = ArrayList<SightPage.Sight>()

    fun appendMember(list: List<SightPage.Sight>) {
        val sta = dataList.size
        list.forEach { dataList.add(it) }
        this.notifyItemRangeInserted(sta, list.size)
    }

    fun removeAllMember() {
        if (itemCount != 0) {
            val range = dataList.size
            dataList.removeAll(dataList)
            this.notifyItemRangeRemoved(0, range)
        }
    }

    fun getItem(pos: Int): SightPage.Sight {
        return dataList[pos]
    }

    //1行のレイアウトをセット
    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): SightViewHolder {
//        return SightViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.viewholder_sight,parent,false))
        return SightViewHolder(parent).newInstance(context, parent)
    }

    //リストに表示する行数を返す
    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: SightViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }
}
