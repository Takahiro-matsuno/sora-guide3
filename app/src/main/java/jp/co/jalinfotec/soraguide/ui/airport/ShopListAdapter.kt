package jp.co.jalinfotec.soraguide.ui.airport

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.model.airport.Shop

class ShopListAdapter(private val context: Context): RecyclerView.Adapter<ShopViewHolder>() {

    private val dataList = arrayListOf<Shop>()

    fun appendMember(list: List<Shop>) {
        val sta = dataList.size
        val range = list.size
        list.forEach { dataList.add(it) }
        this.notifyItemRangeInserted(sta, range)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(parent).newInstance(context, parent)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }
}
