package jp.co.jalinfotec.soraguide.model.topics

class TopicsRepository {

    fun getTopics(): List<Topics> {
        return listOf(
            Topics(1, "ほげ", "https://image.shutterstock.com/image-photo/abstract-design-background-vegetables-on-260nw-82612867.jpg", "https://www.jal.co.jp/"),
            Topics(2, "ぴよ", "https://image.shutterstock.com/image-photo/italy-countryside-landscape-tuscany-rolling-260nw-1104764066.jpg", "https://www.jal.co.jp/"),
            Topics(3, "ほげ", "https://image.shutterstock.com/image-photo/abstract-design-background-vegetables-on-260nw-82612867.jpg", "https://www.jal.co.jp/"),
            Topics(4, "ぴよ", "https://image.shutterstock.com/image-photo/italy-countryside-landscape-tuscany-rolling-260nw-1104764066.jpg", "https://www.jal.co.jp/"),
            Topics(5, "ほげ", "https://image.shutterstock.com/image-photo/abstract-design-background-vegetables-on-260nw-82612867.jpg", "https://www.jal.co.jp/"),
            Topics(6, "ぴよ", "https://image.shutterstock.com/image-photo/italy-countryside-landscape-tuscany-rolling-260nw-1104764066.jpg", "https://www.jal.co.jp/")
        )
    }
}