package me.spica.wuqi.network



import com.skydoves.sandwich.ApiResponse
import me.spica.wuqi.model.comment.CommentItem
import me.spica.wuqi.model.date.YDateList
import me.spica.wuqi.network.service.ApiService
import javax.inject.Inject


/**
 * 网络请求的Client
 */
class AppClient
@Inject constructor(
    private val apiService: ApiService
) {


    suspend fun getDateList(): ApiResponse<YDateList> =
        apiService.getDateList()


    suspend fun getCommentList(
        contentId: Int,
        page: Int,
        type: Int? = null
    ): ApiResponse<List<CommentItem>> =
        apiService.getCommentList(contentId, page, type)


    /**
     * QQ 登陆
     */
    suspend fun login(openid: String, access_token: String): ApiResponse<Any> =
        apiService.login(account = openid, accessToken = access_token)

}