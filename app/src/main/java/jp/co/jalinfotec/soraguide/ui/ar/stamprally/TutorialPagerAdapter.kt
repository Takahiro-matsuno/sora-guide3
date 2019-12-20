package jp.co.jalinfotec.soraguide.ui.ar.stamprally

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import jp.co.jalinfotec.soraguide.model.stamprally.TutorialEntity

class TutorialPagerAdapter(fm: FragmentManager, pages: List<TutorialEntity.Page>) : FragmentPagerAdapter(fm){

    private val pageList: List<TutorialEntity.Page> = pages

    override fun getCount(): Int {
       return pageList.size
    }

    override fun getItem(position: Int): Fragment {
        return TutorialPageFragment().newInstance(pageList.get(position).tutorialText,
            pageList.get(position).imagePosition, pageList.get(position).imageURI
        )
    }

}