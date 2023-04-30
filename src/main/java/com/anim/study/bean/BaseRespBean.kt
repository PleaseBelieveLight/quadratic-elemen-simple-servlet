package com.anim.study.bean

import com.anim.study.state.RespState

data class BaseRespBean<T : Any>(
    val code: Int,
    val message: String,
    val data: T,
    val success: Boolean
) {

    companion object {

        fun getErrorRespBean(errorType: ErrorRequestException): BaseRespBean<String> {
            val data = when (errorType) {
                is ErrorRequestException.PostTypeException -> {
                    "请求类型不匹配，请使用:${errorType.respState}"
                }
                ErrorRequestException.FileIdLoseException -> {
                    "请求字段丢失"
                }
                ErrorRequestException.NoFoundDataException -> {
                    "没有找到相应数据"
                }
            }
            return BaseRespBean(2023, "服务器请求失败", data, false)
        }
    }
}

sealed class ErrorRequestException {

    data class PostTypeException(
        val respState: RespState? = null
    ) : ErrorRequestException()

    object FileIdLoseException : ErrorRequestException()

    object NoFoundDataException :  ErrorRequestException()
}