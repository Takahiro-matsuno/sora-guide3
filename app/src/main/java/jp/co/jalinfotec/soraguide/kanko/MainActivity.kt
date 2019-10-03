package jp.co.jalinfotec.soraguide

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.DataBindingUtil

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
/* ---------------------------------------------------------------------- */
/* 事前定義(プルダウンの部品など                                            */
/* ---------------------------------------------------------------------- */
    private val spinnerKen = arrayOf(
     " ","徳島県","香川県","愛媛県","高知県"
    )
    private val spinnerTachiyori = arrayOf(
        "指定なし", "所要30分程度", "所要30～60分くらい", "所要60～90分くらい", "所要90分以上"
    )
/* ---------------------------------------------------------------------- */
/* Lifecycle                                                              */
/* ---------------------------------------------------------------------- */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        } ?: IllegalAccessException("Toolbar cannot be null")

        //県選択用プルダウン部分の部品
        val spiner_adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            spinnerKen)

        spiner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner_area?.adapter = spiner_adapter
        var ken = ""

        spinner_area?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                ken = item
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //立ち寄り時間選択用プルダウン部分の部品
        val tachiyori_adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_item,
            spinnerTachiyori
        )

        tachiyori_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tachiyori.adapter = tachiyori_adapter
        var tachiyori = ""

        spinner_tachiyori?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val tachiyoriParent = parent as Spinner
                val tachiyoritime = tachiyoriParent.selectedItem as String
                tachiyori = tachiyoritime
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                //TODO:プルダウンから選択されなかった処理・・・って必要？
            }
        }
        //キーボードでenterキー押されたら閉じる処理
        edit_text.setOnKeyListener(OnkeyListener())

        search_button.setOnClickListener {
            if(ken == "------"){
                //TODO:検索処理を行わないようにする。
            }

            //検索キーワードに入力された値のset
            val keyword = edit_text.text.toString()
            Log.d("検索キーワード","入力されたキーワードは$keyword")

            //spinnerで選択された値をJISコードへ変換
            when(ken){
                "------" -> {ken = ""}
                "徳島県" -> {ken = "36"}
                "香川県" -> {ken = "37"}
                "愛媛県" -> {ken = "38"}
                "高知県" -> {ken = "39"}
            }

            when(tachiyori){
                "指定なし"           -> {tachiyori = "0,1,2,3"}
                "所要30分程度"       -> {tachiyori = "0"}
                "所要30～60分くらい" -> {tachiyori = "1"}
                "所要60～90分くらい" -> {tachiyori = "2"}
                "所要90分以上"       -> {tachiyori = "3"}
            }

            val intent = Intent(this@MainActivity,ResultActivity::class.java)
            intent.putExtra("keyword",keyword)
            intent.putExtra("ken",ken)
            intent.putExtra("tachiyori",tachiyori)
            startActivity(intent)
        }

    }

    //キーボード消すためのクラス。
    private inner class OnkeyListener : View.OnKeyListener{
        override fun onKey(view: View,keycode:Int,keyEvent: KeyEvent):Boolean{
            if(keyEvent.action != KeyEvent.ACTION_UP || keycode != KeyEvent.KEYCODE_ENTER){
                return false
            }
            val editText = view as EditText
            // キーボードを閉じる
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
            var keyword = editText.text.toString()
            return true
        }
    }
}
