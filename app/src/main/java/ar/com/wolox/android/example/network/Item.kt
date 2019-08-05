package ar.com.wolox.android.example.network

data class Item(
    val name: String,
    var selected: Boolean
) {
    fun isSelected(): Boolean = selected
}