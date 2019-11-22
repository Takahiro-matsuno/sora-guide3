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





class StampDialog : BaseCallbackDialog<StampDialog.CallbackListener>() {

    private val dataKey = "DATA"
    private val default: String = "DEF"
    private lateinit var data: String
    private lateinit var URI: String

    /**
     * 呼び出し元(Activity, Fragment)で実装するCallbackMethodを定義
     */

    interface CallbackListener {
        fun ok()
        fun cancel()
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        data: String // ダイアログに渡したいデータ
    ): StampDialog {
        val dialog = StampDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putString("DATA", data)
        URI = data
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 呼び出し元からの引数を取得
        data = if (savedInstanceState == null) {
            // ダイアログ生成時
            arguments?.getString(dataKey) ?: default
        } else {
            // 再起動時
            savedInstanceState.getString(dataKey) ?: default

        }
    }

    // Activity終了前にデータを保存
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(dataKey, data)
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

        //リソース取得
        val ips = resources.assets.open("wikitude/stamp-rally/assets/qr.png")
        val bm = BitmapFactory.decodeStream(ips)

        //クーポン名　差し替え
        couponText1.text = "ほげほげクーポン"
        //有効期限　差し替え
        QRLimit.text = "有効期限: 2019/10/32~ 2022/12/43"
        //画像差し替え
        QRView.setImageBitmap(bm)





        /*
        リスナ
        dia_ok.setOnClickListener{
            getCallbackListener()?.ok()
        }

         */


    }



}


