package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.stamprally.StampRally
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.viewholder_stamp_rally.view.*

class StampRallyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var mContext: Context
    private lateinit var cListener: CallbackListener

    interface CallbackListener {
        fun itemTapped(data: StampRally?)
        fun couponTapped(data: StampRally?)
    }

    fun newInstance(context: Context, parent: ViewGroup, listener: CallbackListener): StampRallyViewHolder {
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
    fun bindData(data: StampRally) {
        /* Viewのデータ設定 */
        itemView.stampRallyImage.setImageBitmap(BitmapFactory.decodeStream(mContext.resources.assets.open(data.stampRallyImageUri)))
        itemView.stampRallyNameText.text = data.stampRallyName
        itemView.stampRallyPeriodText.text =
            mContext.resources.getString(R.string.periodStr, Constants.df.format(data.startDate), Constants.df.format(data.endDate))

        // コンプリート済みか、使用済みかで表記を変更する
        val num = data.markers.size
        if (data.isCompleted) {
            itemView.couponBtn.isEnabled = true
            itemView.stampRallyArchiveText.text = mContext.getString(R.string.stamp_rally_achieve, num, num)
            //クーポンが使用済みかで表記を変更する
            if (data.isCouponUsed) {
                //使用済み
                itemView.couponBtn.isEnabled = false
                itemView.couponBtn.text = mContext.resources.getString(R.string.coupon_btn_used)
                itemView.couponBtn.setTextColor(Color.GRAY)
            } else {
                //未使用
                itemView.couponBtn.text = mContext.resources.getString(R.string.coupon_btn_not_used)
            }
        } else {
            //未達成
            itemView.couponBtn.isEnabled = false
            itemView.stampRallyArchiveText.text = mContext.getString(R.string.stamp_rally_achieve, data.getAcquiredNum(), num)
            itemView.couponBtn.text = mContext.resources.getString(R.string.coupon_btn_not_achieved)
            itemView.couponBtn.setTextColor(Color.GRAY)
        }



        // クーポン使用済みかで表記を変更する

        /*
        if (data.isCouponUsed) {
            itemView.couponBtn.isEnabled = false
            itemView.couponBtn.text = mContext.resources.getString(R.string.coupon_btn_used)
        } else {
            itemView.couponBtn.text = mContext.resources.getString(R.string.coupon_btn_not_used)
        }

        */

        /* Viewのクリックイベント */
        //itemView.stampRallyLayout.setOnClickListener { cListener.itemTapped(data) }
        itemView.couponBtn.setOnClickListener { cListener.couponTapped(data) }
        itemView.cardView.setOnClickListener { if (!data.isCompleted) { cListener.itemTapped(data) } }

    }
}