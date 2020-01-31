package jp.co.jalinfotec.soraguide.ui.sight

import android.content.Context
import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.R
import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog
import kotlinx.android.synthetic.main.dialog_sight_search.*

class SightSearchDialog:BaseCallbackDialog<SightSearchDialog.CallbackListener>() {
    private val kenMap = mapOf(
        "香川県" to "37",
        "徳島県" to "36",
        "愛媛県" to "38",
        "高知県" to "39"
    )
    private val tachiyoriMap = mapOf(
        "指定なし" to "0,1,2,3",
        "所要時間30分程度" to "0",
        "所要時間30～60分くらい" to "1",
        "所要時間60～90分くらい" to "2",
        "所要時間90分以上" to "3"
    )
    private val seasonspinner = arrayOf("", "春", "夏", "秋", "冬")

    private val RainMap = mapOf(
        "指定なし" to "0,1,2",
        "天気が雨でも楽しめる" to "0",
        "天気が雨ならいまいち・・・" to "2"
    )

    interface CallbackListener{//呼び出し元で動作するメソッド
        fun search(ken: String, keyword: String, tachiyori: String,season:String,rain:String)
    }

    fun newInstance(listener: CallbackListener):SightSearchDialog{
        val dialog = SightSearchDialog()
        dialog.setCallbackListener(listener)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_sight_search, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kenSpinner.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_spinner_item, kenMap.keys.toTypedArray())
        tachiyoriSpinner.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_spinner_item, tachiyoriMap.keys.toTypedArray())
        seasonSpinner.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_spinner_item, seasonspinner)
        rainSpinner.adapter = ArrayAdapter(this.context!!, android.R.layout.simple_spinner_item, RainMap.keys.toTypedArray())
        searchBtn.setOnClickListener {
            val kenData = kenMap[(kenSpinner.selectedItem as String)]
            val keywordData = keywordText.text.toString()
            val tachiyoriData = tachiyoriMap[(tachiyoriSpinner.selectedItem as String)]
            val recomendData = seasonSpinner.selectedItem as String
            val rainData = rainSpinner.selectedItem as String
            if (!kenData.isNullOrEmpty() && !tachiyoriData.isNullOrEmpty()) {
                this.dismiss()
                this.getCallbackListener()?.search(kenData, keywordData, tachiyoriData,recomendData,rainData)
            } else {
                Toast.makeText(this.context, "検索条件が不正です", Toast.LENGTH_SHORT).show()
            }
        }
        //キーボードでエンター押された時のイベント
        keywordText.setOnKeyListener { view, keyCode, keyEvent ->
            (keyEvent!!.action == KeyEvent.ACTION_DOWN && keyCode == KEYCODE_ENTER).apply{
                //TODO:外出ししたsetOnClickListenerを呼び出す
                dismiss()
            }
        }
        keywordText.isFocusableInTouchMode = true
        keywordText.requestFocus()
    }
}
