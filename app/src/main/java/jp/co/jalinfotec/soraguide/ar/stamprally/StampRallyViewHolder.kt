package jp.co.jalinfotec.soraguide.ar.stamprally

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.viewholder_stamp_rally.view.*
import java.text.SimpleDateFormat

class StampRallyViewHolder(view: View):
    RecyclerView.ViewHolder(view) {

    private lateinit var cListener: CallbackListener

    interface CallbackListener {
        fun itemTapped(data: StampRallyEntity?)
        fun couponTapped(data: StampRallyEntity?)
    }

    @SuppressLint("SimpleDateFormat")
    private val sf = SimpleDateFormat("yyyy/MM/dd")

    fun newInstance(parent: ViewGroup, listener: StampRallyViewHolder.CallbackListener): StampRallyViewHolder {
        val viewHolder =  StampRallyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_stamp_rally,
                parent,
                false)
        )
        viewHolder.cListener = listener
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    fun bindData(data: StampRallyEntity) {
        itemView.name_txt.text = data.stampRallyName
        itemView.period_txt.text = "期間：${sf.format(data.startDate)}~${sf.format(data.endDate)}"
        itemView.achieve_txt.text = "達成度：${data.done} / ${data.num}"
        if (data.done < data.num) {
            itemView.coupon_btn.isEnabled = false
        }
        itemView.stamp_rally_layout.setOnClickListener {
            cListener.itemTapped(data)
        }
        itemView.coupon_btn.setOnClickListener {
            cListener.couponTapped(data)
        }
    }
}