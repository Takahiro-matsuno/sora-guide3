package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import jp.co.jalinfotec.soraguide.ui.base.BaseCallbackDialog

class TutorialDialog : BaseCallbackDialog<TutorialDialog.CallbackListener>() {

    //コールバック用
    interface CallbackListener {
        //次から表示しないにチェックが入っていた場合のコールバックリスナー
        fun updateFlag(dialogId: String)
    }

    // インスタンスは必ず以下のクラスで生成
    fun newInstance(
        listener: CallbackListener,
        entity: TutorialDialog
    ): TutorialDialog {
        val dialog = TutorialDialog()
        dialog.setCallbackListener(listener)
        val args = dialog.arguments ?: Bundle()
        args.putSerializable("ENTITY", entity)
        return dialog
    }


    /*
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
        return  inflater.inflate(R.layout.dialog_tutorial, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


/*


        /*


        // クーポン名をViewに設定
        cpn_name.text = entity.stampRallyName

        // クーポンの有効期限をViewに設定
        cpn_expiry_date.text = resources.getString(
            R.string.limitStr, Constants.df.format(entity.startDate), Constants.df.format(entity.endDate))

        cpn_use_btn.setOnClickListener {
            if(!entity.isCouponUsed) {
                //QRコードを表示
                qr_image.setImageBitmap(BitmapFactory.decodeStream(resources.assets.open(entity.couponUri)))
                getCallbackListener()?.useCoupon(entity)
            }
        }
        cpn_close_btn.setOnClickListener { this.dismiss() }
    }
        */


}