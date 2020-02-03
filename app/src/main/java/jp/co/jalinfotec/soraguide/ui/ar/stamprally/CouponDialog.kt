package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.os.Bundle
import jp.co.jalinfotec.soraguide.R
import android.view.*
import android.graphics.BitmapFactory
import android.widget.Toast
import jp.co.jalinfotec.soraguide.model.stamprally.StampRallyEntity
import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog
import jp.co.jalinfotec.soraguide.utils.Constants
import kotlinx.android.synthetic.main.dialog_coupon.*
import java.lang.Exception

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
        entity: StampRallyEntity
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
                // savedInstanceStateでバックアップしたEntityを取得
                savedInstanceState.getSerializable(dataKey) as? StampRallyEntity ?: throw Exception()
            } else {
                // newInstanceで設定したEntityを取得
                arguments?.getSerializable(dataKey) as? StampRallyEntity ?: throw Exception()
            }
        } catch (ex: Exception) {
            //クーポンデータがnullならダイアログを閉じる
            Toast.makeText(this.context, "クーポンの読み込みに失敗しました", Toast.LENGTH_LONG).show()
            this.dismiss()
        }
    }

    // Activity終了前にデータを保存
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(dataKey, entity)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.dialog_coupon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var qrOnDisplay: Boolean = false

        // クーポン名をViewに設定
        cpn_name.text = entity.stampRallyName
        //qr_image.setImageBitmap(BitmapFactory.decodeStream(resources.assets.open(entity.stampRallyImageUri)))
        qr_image.setImageResource(R.drawable.coupon)
        // クーポンの有効期限をViewに設定
        cpn_expiry_date.text = resources.getString(
            R.string.limitStr, Constants.df.format(entity.startDate), Constants.df.format(entity.endDate))

        cpn_use_btn.setOnClickListener {
            if(qrOnDisplay){
                this.dismiss()
            } else {
                if(!entity.isCouponUsed) {
                    //QRコードを表示
                    qr_image.setImageBitmap(BitmapFactory.decodeStream(resources.assets.open(entity.couponUri)))
                    getCallbackListener()?.useCoupon(entity)
                    qrOnDisplay = true
                    cpn_use_btn.text = "閉じる"
                }
            }
        }
        cpn_close_btn.setOnClickListener { this.dismiss() }
    }
}