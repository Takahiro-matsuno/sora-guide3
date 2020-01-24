package jp.co.jalinfotec.soraguide.ui.airport


import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.viewholder_shop.view.*
import kotlinx.android.synthetic.main.viewholder_shop.view.image
import kotlinx.android.synthetic.main.viewholder_shop.view.location
import kotlinx.android.synthetic.main.viewholder_shop.view.time
import kotlinx.android.synthetic.main.viewholder_shop.view.title

class ShopViewHolder(itemview: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemview) {

    var shopImage: ImageView = itemview.image
    var shopTitle: TextView = itemview.title
    var shopLocation: TextView = itemview.location
    var shopTime: TextView = itemview.time
    var shopCategory: TextView = itemview.category
    var shopText: TextView = itemview.text
    var shopTel: TextView = itemview.tel
}