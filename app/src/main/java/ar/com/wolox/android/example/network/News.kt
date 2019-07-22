package ar.com.wolox.android.example.network

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime

@Parcelize
data class News(
    val id: String,
    val userId: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    val title: String,
    val picture: String,
    val text: String,
    val likes: List<Int>
) : Parcelable {

    val readableCreationTime get() = PrettyTime().format(DateTime(createdAt).toDate())!!
}