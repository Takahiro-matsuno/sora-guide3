package jp.co.jalinfotec.soraguide.model.stamprally
import java.io.Serializable

data class TutorialEntity(
    val tutorialId: String,
    var pages: List<Page>
) :Serializable {
    //ページを構成する情報
    data class Page(
        val tutorialText: String,
        val imagePosition: String,
        var imageURI: String // 画像リソースの指定
    ) : Serializable

}