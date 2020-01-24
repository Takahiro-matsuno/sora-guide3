package jp.co.jalinfotec.soraguide.ui.airport

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.model.airport.Shop


open class ShopListAdapter(private val mParentActivity : ShopFragment,
                           private val mValues: ArrayList<Shop>) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {

        //レイアウトの設定(inflate)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_shop,parent,false)
        //Holderの生成
        val holder = ShopViewHolder(view)

        //要素がTAPされたときの動作
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
        }

        return holder
    }

    //ShopViewHolderに各値をセットする。
    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {

        val item = mValues[position]
        holder.shopImage.setImageDrawable(item.shopImage)
        holder.shopTitle.text = item.shopTitle
        holder.shopLocation.text = item.shopLocation
        holder.shopTime.text = item.shopTime
        holder.shopCategory.text = item.shopCategory
        holder.shopTel.text = item.shopTel
        holder.shopText.text = item.shopText

    }

    //リスト表示の行数取得
    override fun getItemCount(): Int {
        return mValues.size
    }

    //選択された行番号のDATAを取得
    fun getItem(pos: Int): Shop {
        return mValues[pos]
    }


}
