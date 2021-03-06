package me.spica.wuqi.repository

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import me.spica.wuqi.model.comment.CommentItem
import me.spica.wuqi.model.date.YDateList
import me.spica.wuqi.network.AppClient
import javax.inject.Inject

@Suppress("unused")
class YRepostory
@Inject constructor(
    private val appClient: AppClient
) {


    @WorkerThread
    fun dateList(
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
        onSuccess: () -> Unit
    ) = flow {

        var yDateList: YDateList

        val response = appClient.getDateList()

        response.suspendOnSuccess {
            yDateList = this.data
            emit(yDateList)
            onSuccess()
        }.onError {
            onError(message())
        }.onException {
            onError(message)
        }
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)


    fun getCommentList(
        contentId: Int,
        page: Int,
        type: Int? = null,
        onStart:
            () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit,
    ) = flow {
        var comments: List<CommentItem>
        val response = appClient.getCommentList(contentId, page, type)
        response.suspendOnSuccess {
            comments = this.data
            emit(comments)
        }.onError {
            onError(message())
        }.onException {
            onError(message)
        }
    }.onStart {
        onStart()
    }.onCompletion {
        onComplete()
    }.flowOn(Dispatchers.IO)


}