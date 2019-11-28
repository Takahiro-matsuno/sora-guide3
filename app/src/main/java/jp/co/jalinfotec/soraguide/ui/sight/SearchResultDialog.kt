package jp.co.jalinfotec.soraguide.ui.sight

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog

class SearchResultDialog:BaseCallbackDialog<SearchResultDialog.CallbackListener>() {
    private val dataKey = "DATA"
    private val default: String = "DEF"
    private lateinit var data: String

    /**
     * 呼び出し元(Activity, Fragment)で実装するCallbackMethodを定義
     */
    interface CallbackListener {
        fun ok()
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        data: String // activityからダイアログに渡したいデータがあればこれ使う
    ): SearchResultDialog {
        val dialog = SearchResultDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putString("DATA", data)
        dialog.setCancelable(false)//dialogのmodal化
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ダイアログ呼び出し元からの引数を取得
        data = if (savedInstanceState == null) {
            // ダイアログ生成時
            arguments?.getString(dataKey) ?: default
        } else {
            // 再起動時
            savedInstanceState.getString(dataKey) ?: default
        }
    }
//     Activity終了前にデータを保存
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(dataKey, data)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // R.layoutを使わない場合
        val builder = AlertDialog.Builder(context)
            .setTitle("検索結果エラー")
            .setMessage(data)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                Toast.makeText(context,"検索画面に戻ります", Toast.LENGTH_SHORT).show()
                getCallbackListener()?.ok()
                finishActivity()
            }
        return builder.create()
    }
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // TODO R.layoutを使用したViewを使う場合は以下に追加
//        return super.onCreateView(inflater, container, savedInstanceState)
//
//    }
    fun finishActivity(){
        activity?.finish()
    }
}
