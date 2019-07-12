package ar.com.wolox.android.example.network

class News(
    externalTitle: String,
    externalUrlPicture: String,
    externalText: String
) {
    private var id = ""
    private var userId = ""
    private var createdAt = ""
    private var title = ""
    private var picture = ""
    private var text = ""
    private var likes: List<Int>? = null

    init {
        title = externalTitle
        picture = externalUrlPicture
        text = externalText
    }

    fun getUserId(): String = userId

    fun getCreatedAt(): String = createdAt

    fun getTitle(): String = title

    fun getPictureUrl(): String = picture

    fun getText(): String = text

    fun getFirstLike(): Int? = likes?.get(0)
}