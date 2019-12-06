package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyEntity
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.viewholder_stamp_rally.view.*

class StampRallyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var mContext: Context
    private lateinit var cListener: CallbackListener

    interface CallbackListener {
        fun itemTapped(data: StampRallyEntity?)
        fun couponTapped(data: StampRallyEntity?)
    }

    fun newInstance(context: Context, parent: ViewGroup, listener: StampRallyViewHolder.CallbackListener): StampRallyViewHolder {
        val viewHolder =  StampRallyViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_stamp_rally,
                parent,
                false)
        )
        viewHolder.mContext = context
        viewHolder.cListener = listener
        return viewHolder
    }

    @SuppressLint("SetTextI18n")
    fun bindData(data: StampRallyEntity) {
        /* Viewのデータ設定 */
        itemView.name_txt.text = data.stampRallyName
        itemView.period_txt.text =
            mContext.resources.getString(R.string.limitStr, Constants.df.format(data.startDate), Constants.df.format(data.endDate))

        // コンプリート済みかで表記を変更する
        val num = data.markers.size
        if (data.isCompleted) {
            itemView.coupon_btn.isEnabled = true
            itemView.achieve_txt.text = mContext.getString(R.string.stamp_rally_achieve, num, num)
        } else {
            itemView.coupon_btn.isEnabled = false
            itemView.achieve_txt.text = mContext.getString(R.string.stamp_rally_achieve, data.getAcquiredNum(), num)

        }
        // クーポン使用済みかで表記を変更する
        if (data.isCouponUsed) {
            itemView.coupon_btn.isEnabled = false
            itemView.coupon_btn.text = mContext.resources.getString(R.string.coupon_btn_used)
        } else {
            itemView.coupon_btn.text = mContext.resources.getString(R.string.coupon_btn_not_used)
        }

        /* Viewのクリックイベント */
        itemView.stamp_rally_layout.setOnClickListener { cListener.itemTapped(data) }
        itemView.coupon_btn.setOnClickListener { cListener.couponTapped(data) }
    }
}