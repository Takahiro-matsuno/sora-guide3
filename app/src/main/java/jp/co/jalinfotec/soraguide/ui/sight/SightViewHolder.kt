package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.sight.Sight
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.viewholder_sight.view.*

class SightViewHolder(itemview:View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemview) {
    val row = itemView.findViewById<View>(R.id.row)
    private lateinit var mContext: Context
    private val logTag = this::class.java.simpleName

    fun newInstance(context: Context, parent: ViewGroup): SightViewHolder {
        val holder = SightViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.viewholder_sight,
            parent,
            false)
        )
        holder.mContext = context
        return holder
    }

    fun bindData(data: Sight) {
        /* Viewのデータ設定 */
        itemView.sightTitle.text   = data.Title
        itemView.sightAddress.text = data.Address
        itemView.sightTime.text    = data.Time
        Glide.with(mContext)
            .asBitmap()
            .load("${Constants.RURUBU_URL}${Constants.RURUBU_STOAGE_URL}${data.PhotoList?.get(0)?.URL}")
            .apply(RequestOptions().centerCrop())
            .into(itemView.sightImage)
    }
}
