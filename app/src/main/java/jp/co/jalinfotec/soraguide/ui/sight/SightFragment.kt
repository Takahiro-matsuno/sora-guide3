package jp.co.jalinfotec.soraguide.ui.sight

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.R

class SightFragment: Fragment() {

    fun newInstance(): SightFragment {
        return SightFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sight, container, false)
    }
}