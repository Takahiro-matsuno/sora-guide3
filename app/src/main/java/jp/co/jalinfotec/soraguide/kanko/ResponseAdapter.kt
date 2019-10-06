package jp.co.jalinfotec.soraguide

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ResponseAdapter(private val context: Context,private val dataset:List<Sight>): RecyclerView.Adapter<ResponseViewHolder>(){
    //1行のレイアウトをセット
    override fun onCreateViewHolder(parent:ViewGroup,viewType: Int): ResponseViewHolder {
        return ResponseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.response_row,parent,false))
    }

    //リストに表示する行数を返す
    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
        //この行の情報を取得
        val response:Sight? = dataset[position]
        //GlideでImagetoUrlの画像をholder.imageviewにセット
        val options = RequestOptions().centerCrop()

        if (response?.PhotoList == null){//TODO:画像データない場合の実装
//             val preparatePicture = "http://imepic.jp/20190409/717130"
//            Glide.with(context).load(preparatePicture).apply(options).into(holder.imageView!!)
        }else{
            //withでimageViewがあるActivityかFragment指定。 loadで画像ダウンロード先指定 intoで表示させるimageviewを指定
            val picture = "https://www.j-jti.com/Storage/Image/Product/SightImage/S/${response.PhotoList[0].URL}"
            Glide.with(context).load(picture).apply(options).into(holder.imageView!!)//nullのPhotolistも含まれるためこんな書き方
        }
        //観光地名、住所をセット
        holder.descriptionText!!.text = response?.Title
        holder.publishText!!.text = response?.Address
        holder.timetext!!.text = response?.Time

        if (position == this.dataset.count() - 1){//positionはゼロスタート
            //リストの末尾にきたときの処理ここに記載
            Log.d("test","リストの末尾に到達！")
        }
    }
}
