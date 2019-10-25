package jp.co.jalinfotec.soraguide.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

abstract class BaseCallbackDialog<Interface>: DialogFragment() {

    enum class ListenerType { ACTIVITY, FRAGMENT, OTHER }
    private val listenerKey = "LISTENER"
    private var cListener: Interface? = null // CallbackListener
    private var listenerType: ListenerType? = null

    // コールバックリスナーを返却
    fun getCallbackListener(): Interface? {
        return this.cListener
    }

    // コールバックリスナーを設定
    fun setCallbackListener(listener: Interface?) {
        this.cListener = listener

        // ライフサイクル復帰時に親リソース判別のためTypeを保存する
        when (cListener) {
            null -> {
                listenerType = null
                setTargetFragment(null, 0)
            }
            is Activity -> {
                listenerType = ListenerType.ACTIVITY
                setTargetFragment(null, 0)
            }
            is Fragment -> {
                listenerType = ListenerType.FRAGMENT
                setTargetFragment(listener as Fragment, 0)
            }
            else -> {
                listenerType = ListenerType.OTHER
                setTargetFragment(null, 0)
            }
        }
        val args = this.arguments ?: Bundle()
        args.putSerializable(listenerKey, listenerType)
        this.arguments = args
    }

    // Activity, Fragment再起動時にリスナーを再設定する
    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listenerType = arguments?.getSerializable(listenerKey) as ListenerType
        when (listenerType) {
            ListenerType.ACTIVITY -> cListener = activity as Interface
            ListenerType.FRAGMENT -> cListener = targetFragment as Interface
            else -> {}
        }
    }

}