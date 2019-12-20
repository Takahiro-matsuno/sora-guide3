package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.model.stamprally.TutorialEntity
import kotlinx.android.synthetic.main.fragment_tutorial_page.*

class TutorialPageFragment: Fragment(){

    private val BODY_KEY = "BODY"
    private val POS_KEY = "POSITION"
    private val IMAGE_KEY = "IMAGE"

    private lateinit var body: String
    private lateinit var imagePos: String
    private lateinit var imageUri: String

    //ViewPagerに乗せるページフラグメントのインスタンスを生成する。
    fun newInstance(
        //listener: TutorialDialog.CallbackListener,
        body: String,
        imagePosition: String,
        image: String
    ): TutorialPageFragment {

            val page = TutorialPageFragment()
            val args = page.arguments ?: Bundle()
            args.putString(BODY_KEY, body)
            args.putString(POS_KEY, imagePosition)
            args.putString(IMAGE_KEY, image)
            return page

    }

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            //受け取った本文と画像の配置を行う処理を書きたい
            var bundle = arguments
            if(savedInstanceState !== null) {
                body = arguments!!.getString(BODY_KEY).toString()
                imagePos = arguments!!.getString(POS_KEY).toString()
                imageUri = arguments!!.getString(IMAGE_KEY).toString()
            }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //文字、画像リソースの差し替え処理
        //今は仮の処理
        body_str.text = body + imagePos + imageUri
    }


}