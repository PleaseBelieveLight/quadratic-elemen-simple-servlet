package com.anim.study.bean

import com.anim.study.state.RespState

data class BaseRespBean<T : Any>(
    val code: Int,
    val message: String,
    val data: T?,
    val success: Boolean
) {

    companion object {

        fun getErrorRespBean(errorType: ErrorRequestException): BaseRespBean<String> {
            val data = when (errorType) {
                is ErrorRequestException.PostTypeException -> {
                    "请求类型不匹配，请使用:${errorType.respState}"
                }
                ErrorRequestException.FileIdLoseException -> {
                    "请求字段参数不全，请仔细检查"
                }
                ErrorRequestException.NoFoundDataException -> {
                    "参数类型不匹配"
                }
            }
            return BaseRespBean(2023, "服务器请求失败", data, false)
        }

        fun <T : Any> getSucceedRespBean(data: T?): BaseRespBean<T> {
            val message = if (data != null) {
                if (data is Collection<*> && data.isEmpty()) {
                    "没有查找到数据"
                } else {
                    "成功"
                }
            } else {
                "没有查找到数据"
            }
            return BaseRespBean(200, message, data, true)
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