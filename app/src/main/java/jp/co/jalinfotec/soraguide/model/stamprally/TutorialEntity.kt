package jp.co.jalinfotec.soraguide.model.stamprally

import java.io.Serializable
import java.util.*

//チュートリアルの情報
data class TutorialEntity(
    val tutorialId: String,
    var pages: List<TutorialData>
) :Serializable {
    //ページを構成する情報
    data class TutorialData(
        val tutorialText: String,
        val imagePosition: String,
        var imageURI: String // 画像リソースの指定
    ) : Serializable

}