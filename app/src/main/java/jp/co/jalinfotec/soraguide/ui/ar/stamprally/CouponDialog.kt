package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.annotation.SuppressLint
import android.os.Bundle
import jp.co.jalinfotec.soraguide.R
import android.view.*
import android.graphics.BitmapFactory
import android.widget.Toast
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyEntity
import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog
import kotlinx.android.synthetic.main.dialog_coupon.*
import java.lang.Exception
import java.text.SimpleDateFormat

class CouponDialog : BaseCallbackDialog<CouponDialog.CallbackListener>() {

    private val dataKey = "ENTITY"
    private lateinit var entity: StampRallyEntity

    /**
     * 呼び出し元(Activity, Fragment)で実装するCallbackMethodを定義
     */
    interface CallbackListener {
        fun useCoupon(entity : StampRallyEntity)
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        entity: StampRallyEntity // ダイアログに渡したいデータ
    ): CouponDialog {
        val dialog = CouponDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putSerializable("ENTITY", entity)
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            entity = if (savedInstanceState !== null) {
                // 呼び出し元からの引数を取得
                savedInstanceState.getSerializable(dataKey) as? StampRallyEntity
                    ?: throw Exception()
            } else {
                //新規インスタンスから
                arguments?.getSerializable(dataKey) as? StampRallyEntity
                    ?: throw Exception()
            }
        } catch (ex: Exception) {
            //クーポンデータがnullならダイアログを閉じる
            Toast.makeText(this.context, "クーポンは無効です。", Toast.LENGTH_LONG).show()
            this.dismiss()
        }


    }

    // Activity終了前にデータを保存
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(dataKey, entity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // TODO R.layoutを使用したViewを使う場合は以下に追加
         return  inflater.inflate(R.layout.dialog_coupon, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btnTapped :Boolean = false //使用フラグ

        //クーポン名をViewに設定
        cpn_name.text = entity.stampRallyName
        //cpn_name.setEllipsize(null) // 文末を... にするのに必要そう

        //クーポンの有効期限をViewに設定
        val dateFormat = SimpleDateFormat("yyyy/MM/dd")
        cpn_expiry_date.text = resources.getString(R.string.limitStr, dateFormat.format(entity.startDate), dateFormat.format(entity.endDate))


        //"クーポンを使用する"ボタンタップ処理
        cpn_use_btn.setOnClickListener(){
            //リソース取得
            val ips = resources.assets.open("wikitude/stamp-rally/assets/qr.png")
            val bm = BitmapFactory.decodeStream(ips)
            //QRコードを表示
            qr_image.setImageBitmap(bm)

            //(クーポン使用ボタン初回押下時のみ)クーポンを使用済にする
            if(!btnTapped && !entity.isCouponUsed){
               getCallbackListener()?.useCoupon(entity)
            }
            btnTapped = true
        }

        //右上の"×"ボタンタップ処理
        cpn_close_btn.setOnClickListener(){
            this.dismiss()
        }


    }


}


