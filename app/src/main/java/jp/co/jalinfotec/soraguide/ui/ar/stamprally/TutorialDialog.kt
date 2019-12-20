package jp.co.jalinfotec.soraguide.ui.ar.stamprally
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.*
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import jp.co.jalinfotec.soraguide.model.stamprally.TutorialEntity
import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog
import jp.co.jalinfotec.soraguide.R
import kotlinx.android.synthetic.main.dialog_tutorial.*

class TutorialDialog : BaseCallbackDialog<TutorialDialog.CallbackListener>() {

    private val dataKey = "ENTITY"
    private lateinit var entity: TutorialEntity

    //コールバック用
    interface CallbackListener {
        //次から表示しないにチェックが入っていた場合のコールバックリスナー
        fun updateFlag(dialogId: String)
        {
            Log.d("check", "hogehgoe")
        }
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        entity: TutorialEntity
    ): TutorialDialog {
        val dialog = TutorialDialog()
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
                savedInstanceState.getSerializable(dataKey) as? TutorialEntity ?: throw Exception()
            } else {
                // newInstanceで設定したEntityを取得
                arguments?.getSerializable(dataKey) as? TutorialEntity ?: throw Exception()
            }
        } catch (ex: Exception) {
            //チュートリアルデータがnullならダイアログを閉じる
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
        return  inflater.inflate(R.layout.dialog_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var currentPage = 0
        titleText.text = entity.tutorialId
        //ViewPager処理
        val pager:ViewPager = view.findViewById<ViewPager>(R.id.TutorialPager)
        pager.adapter = TutorialPagerAdapter(childFragmentManager, entity.pages)

        //beforeボタン
        beforeBtn.setOnClickListener(){
            currentPage++
            pager.setCurrentItem(currentPage)
        }

        //nextボタン
        nextBtn.setOnClickListener(){
            currentPage--
            pager.setCurrentItem(currentPage)
        }

    }







}