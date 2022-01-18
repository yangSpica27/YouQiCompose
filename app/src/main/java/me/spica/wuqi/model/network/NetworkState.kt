package me.spica.wuqi.model.network

import androidx.compose.runtime.Composable

enum class NetworkState {
    IDLE,// 空闲
    LOADING,// 加载中
    ERROR,// 错误
    SUCCESS// 成功
}

@Composable
fun NetworkState.onSuccess(block: @Composable () -> Unit): NetworkState {
    if (this == NetworkState.SUCCESS) {
        block()
    }
    return this
}

@Composable
fun NetworkState.onLoading(block: @Composable () -> Unit): NetworkState {
    if (this == NetworkState.LOADING) {
        block()
    }
    return this
}