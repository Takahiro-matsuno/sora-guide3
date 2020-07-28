package jp.co.jalinfotec.soraguide.ui.airport

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.airport.Shop
import kotlinx.android.synthetic.main.viewholder_shop.view.*

class ShopViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private lateinit var mContext: Context

    fun newInstance(context: Context?, parent: ViewGroup): ShopViewHolder {
        val holder = ShopViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.viewholder_shop,
                parent,
                false)
        )
        holder.mContext = context!!
        return holder
    }

    fun bindData(data: Shop) {
        /* Viewのデータ設定 */
        itemView.shopName.text   = data.name
        itemView.openHour.text = data.openHour
        itemView.phoneNumber.text = data.phoneNumber
        Glide.with(mContext)
            .asBitmap()
            .load(data.imageUrl)
            .apply(RequestOptions().centerCrop())
            .into(itemView.shopImage)
    }
}
