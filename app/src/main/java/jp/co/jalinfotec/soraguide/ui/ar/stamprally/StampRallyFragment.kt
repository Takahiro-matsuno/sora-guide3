package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import jp.co.jalinfotec.soraguide.R

class StampRallyFragment: Fragment() {

    fun newInstance(): StampRallyFragment {
        return StampRallyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_stamp_rally, container, false)
    }
}