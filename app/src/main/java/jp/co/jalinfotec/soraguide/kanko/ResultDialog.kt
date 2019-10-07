package jp.co.jalinfotec.soraguide.kanko

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import jp.co.jalinfotec.soraguide.base.BaseCallbackDialog

class ResultDialog:BaseCallbackDialog<ResultDialog.CallbackListener>() {
    private val dataKey = "DATA"
    private val default: String = "DEF"
    private lateinit var data: String

    /**
     * 呼び出し元(Activity, Fragment)で実装するCallbackMethodを定義
     */
    interface CallbackListener {
        fun ok()
//        fun cancel()
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        data: String // ダイアログに渡したいデータ
    ): ResultDialog {
        val dialog = ResultDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putString("DATA", data)
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
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // R.layoutを使わない場合
        val builder = AlertDialog.Builder(context)
            .setTitle("検索結果が0件でした")
            .setMessage(data)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(context,"検索画面に戻ります", Toast.LENGTH_SHORT).show()
                getCallbackListener()?.ok()
                finishActivity()
            }
//            .setNegativeButton("CANCEL") { dialog, _ ->
//                dialog.dismiss()
//                getCallbackListener()?.cancel()
//            }

        return builder.create()
    }
    fun finishActivity(){
        activity?.finish()
    }
}
