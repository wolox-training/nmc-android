package ar.com.wolox.android.example.network

import android.annotation.TargetApi
import android.os.Build
import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

@TargetApi(Build.VERSION_CODES.O)
class News(
    externalTitle: String,
    externalUrlPicture: String,
    externalText: String
) {
    private var id = ""
    private var userId = 0

    @SerializedName("createdAt")
    private var createdAt = ""

    private var title = ""
    private var picture = ""
    private var text = ""
    private var likes: List<Int>? = null

    private var timeToNow = ""

    init {
        title = externalTitle
        picture = externalUrlPicture
        text = externalText
    }

    fun getUserId(): Int = userId

    fun getCreatedAt(): LocalDateTime = LocalDateTime.parse(createdAt.replace("Z", ""))

    fun setTimeToNow(time: String) {
        timeToNow = time
    }

    fun getTimeToNow(): String = timeToNow

    fun getTitle(): String = title

    fun getPictureUrl(): String = picture

    fun getText(): String = text

    fun getFirstLike(): Int? = likes?.get(0)
}