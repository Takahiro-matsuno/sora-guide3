package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.model.stamprally.TutorialEntity

class TutorialPageFragment: Fragment(){

    private val dataKey = "BODY"
    private val dataKey2 = "IMAGE"

    private lateinit var body: String
    private lateinit var imageUri: String

    //ViewPagerに乗せるページフラグメントのインスタンスを生成する。
    fun newInstance(){
        fun newInstance(
            //listener: TutorialDialog.CallbackListener,
            body: String,
            image: String
        ): TutorialPageFragment {
            val page = TutorialPageFragment()
            val args = page.arguments ?: Bundle()
            args.putString("BODY", body)
            args.putString("IMAGE", image)
            return page
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //受け取った本文と画像の配置を行う処理を書きたい

        /*
        try {
            entity = if (savedInstanceState !== null) {
                // savedInstanceStateでバックアップしたEntityを取得
                savedInstanceState.getSerializable(dataKey) as? TutorialEntity ?: throw Exception()
            } else {
                // newInstanceで設定したEntityを取得
                arguments?.getSerializable(dataKey) as? TutorialEntity ?: throw Exception()
            }
        } catch (ex: Exception) {
            //クーポンデータがnullならダイアログを閉じる
            Toast.makeText(this.context, "ページの読み込みに失敗しました", Toast.LENGTH_LONG).show()
            this.dismiss()
        }
    }

    */


}