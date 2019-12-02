package jp.co.jalinfotec.soraguide.ui.sight

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import jp.co.jalinfotec.soraguide.R

class SightViewHolder(itemview:View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemview) {
    val imageView           = itemView.findViewById<ImageView>(R.id.image_row)//画像
    val descriptionText     = itemView.findViewById<TextView>(R.id.content_row)//観光地名
    val publishText         = itemView.findViewById<TextView>(R.id.publish_row)//住所
    val timetext            = itemview.findViewById<TextView>(R.id.time_row)//営業時間
    val row = itemView.findViewById<View>(R.id.row)
}
