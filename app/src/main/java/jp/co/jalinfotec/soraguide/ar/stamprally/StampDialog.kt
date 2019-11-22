package jp.co.jalinfotec.soraguide.ar.stamprally

import android.annotation.SuppressLint
import android.app.Dialog

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import jp.co.jalinfotec.soraguide.base.BaseCallbackDialog
import android.graphics.drawable.ColorDrawable
import jp.co.jalinfotec.soraguide.R
import android.view.*
import android.view.Window.FEATURE_NO_TITLE
import kotlinx.android.synthetic.main.coupon_layout.*
import android.graphics.BitmapFactory
import java.io.InputStream
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import jp.co.jalinfotec.*
import java.io.Serializable


class StampDialog : BaseCallbackDialog<StampDialog.CallbackListener>() {

    private val dataKey = "ENTITY"
    private val default: String = "DEF"
    private var entity: StampRallyEntity?=null

    /**
     * 呼び出し元(Activity, Fragment)で実装するCallbackMethodを定義
     */

    interface CallbackListener {
        fun useCoupon(entity :StampRallyEntity)
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        entity: StampRallyEntity // ダイアログに渡したいデータ
    ): StampDialog {
        val dialog = StampDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putSerializable("ENTITY", entity)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        entity = if (savedInstanceState !== null) {
            // 呼び出し元からの引数を取得
            savedInstanceState.getSerializable(dataKey) as StampRallyEntity
        } else {
            //新規インスタンスから
            arguments?.getSerializable(dataKey) as StampRallyEntity
        }
        //クーポンデータがnullならダイアログを閉じる
        if (entity ==  null){
            Toast.makeText(this.context, "クーポンは無効です。", Toast.LENGTH_LONG).show()
            this.dismiss()
        }

    }

    // Activity終了前にデータを保存
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(dataKey, entity)
    }

    /*
    @SuppressLint("ResourceType")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // R.layoutを使わない場合
        /*
        val builder = AlertDialog.Builder(context!!)
            .setTitle("タイトル")
            .setMessage(data)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                getCallbackListener()?.ok()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
                getCallbackListener()?.cancel()
            }

        return builder.create()
        */

        return Dialog(context!!, R.layout.coupon_layout)
    }


     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // TODO R.layoutを使用したViewを使う場合は以下に追加
         return  inflater.inflate(R.layout.coupon_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //クーポン名　差し替え
        couponText1.text = entity?.stampRallyName
        //有効期限　差し替え
        QRLimit.text = "有効期限: 2019/10/32~ 2022/12/43"

        var isCouponUsed:Boolean = false //使用フラグ

        if(isCouponUsed === false){
            //"クーポンを使用する" ボタン
            button_use.setOnClickListener(){
                // "クーポンを使用する"ボタンをタップ時の処理
                val ips = resources.assets.open("wikitude/stamp-rally/assets/qr.png") //リソース取得
                val bm = BitmapFactory.decodeStream(ips)
                isCouponUsed = true
                //画像差し替え
                QRView.setImageBitmap(bm)
                //使用済みにする処理
                //StampRallyActivity.
                Log.d("cp_used" ,"this coupon has been deleted")
            }
        }

        //右上の"×"ボタン
        close_coupon.setOnClickListener(){
            this.dismiss()
        }


    }


}


