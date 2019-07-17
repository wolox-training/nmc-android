package ar.com.wolox.android.example.network

import com.google.gson.annotations.SerializedName

data class News(
    val id: String,
    val userId: Int,
    @SerializedName("createdAt")
    val createdAt: String,
    val title: String,
    val picture: String,
    val text: String,
    val likes: List<Int>
) {

    var readableCreationTime = ""
}