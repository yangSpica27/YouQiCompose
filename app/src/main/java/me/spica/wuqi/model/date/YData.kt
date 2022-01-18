package me.spica.wuqi.model.date


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

const val NORMAL = 0
const val ARTICLE = 1
const val AUDIO = 2
const val PIC = 3
@JsonClass(generateAdapter = true)
data class YData(
    @Json(name = "content")
    val content: YContent,
    @Json(name = "date")
    val date: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lunar")
    val lunar: String,
    @Json(name = "wuhou")
    val wuhou: String,
    @Json(name = "wuhou_picture")
    val wuhouPicture: String,
    @Json(name = "wuhou_picture_tra")
    val wuhouPictureTra: String,
    @Json(name = "wuhou_tra")
    val wuhouTra: String,
)
