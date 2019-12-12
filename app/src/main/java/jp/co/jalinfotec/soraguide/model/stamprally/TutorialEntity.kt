package jp.co.jalinfotec.soraguide.model.stamprally
import java.io.Serializable
import java.util.*

data class TutorialEntity(
    val tutorialId: String,
    var pages: List<page>
) :Serializable {
    //ページを構成する情報
    data class page(
        val tutorialText: String,
        val imagePosition: String,
        var imageURI: String // 画像リソースの指定
    ) : Serializable

}