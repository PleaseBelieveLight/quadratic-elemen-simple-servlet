package com.anim.study.bean

import com.anim.study.state.RespState
import com.anim.study.utils.hasData

data class BaseRespBean<T : Any>(
    val code: Int,
    val message: String,
    val data: T?,
    val success: Boolean
) {

    companion object {

        @JvmStatic
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
                ErrorRequestException.IdCheckErrorException -> {
                    "身份验证失败"
                }
            }
            return BaseRespBean(2023, "服务器请求失败", data, false)
        }

        @JvmStatic
        fun <T : Any> getSucceedRespBean(data: T?): BaseRespBean<T> {
            val message = if (data is BasePagerBean<*>){
                data.data.hasData()
            } else {
                data.hasData()
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

    object IdCheckErrorException :  ErrorRequestException()
}