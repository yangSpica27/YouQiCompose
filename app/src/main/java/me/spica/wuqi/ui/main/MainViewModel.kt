package me.spica.wuqi.ui.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import me.spica.wuqi.model.network.NetworkState
import me.spica.wuqi.repository.YRepostory
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject
constructor(
    yRepostory: YRepostory,
) : ViewModel() {
    // 日期集的加载状态
    private val _dateLoadingState: MutableState<NetworkState> =
        mutableStateOf(NetworkState.IDLE)

    // 对外隔离设置方法 只能get()获取
    val dateLoadingState: State<NetworkState>
        get() = _dateLoadingState
    val dateInfo = yRepostory.dateList(
        onStart =
        {
            // 请求开始
            _dateLoadingState.value = NetworkState.LOADING
        },
        onComplete =
        {
            // 请求流程结束 流程空闲
            _dateLoadingState.value = NetworkState.IDLE
        },
        onError = {
            // 请求过程中发生错误
            _dateLoadingState.value = NetworkState.ERROR
        },
        onSuccess = {
            // 请求成功获取到数据
            _dateLoadingState.value = NetworkState.SUCCESS
        }
    ).shareIn(
        viewModelScope, // 作用域
        SharingStarted.WhileSubscribed(5000),// 等待时间
        replay = 5 // 重试
    )

}