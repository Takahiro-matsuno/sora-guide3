package jp.co.jalinfotec.soraguide.ui.base

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class RecyclerClickListener(context: Context,
                    private val mListener: OnItemClickListener?): androidx.recyclerview.widget.RecyclerView.OnItemTouchListener {

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private var mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }
    })

    override fun onInterceptTouchEvent(view: androidx.recyclerview.widget.RecyclerView, e: MotionEvent): Boolean {
// タッチした箇所のViewを取得
        val childView = view.findChildViewUnder(e.x, e.y)//たぶん画面の座標

        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            childView.isPressed = true//trueにしているのはアイテムのtouch feedbackがつく前にonItemClickが呼ばれてしまうから・・・らしい
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))//ここでタップした際に取得するもの(viewとposition)を定義
        }
        return false
    }
    override fun onTouchEvent(view: androidx.recyclerview.widget.RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}
