package com.anim.study.utils

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

fun HttpServletResponse.print(msg : String){
    this.writer?.println(msg)
}

fun HttpServletResponse.printUsuallyState(state : ErrorRequestException){
    print(JsonUtils.serialize(BaseRespBean.getErrorRespBean(state)))
}

/**
 * 验证是否为空列表
 */
fun Any?.hasData() : String {
    return if (this is Collection<*> && isEmpty()) {
        "没有查找到数据"
    } else {
        "成功"
    }
}

/**
 * 读取打印所有请求头，用于测试
 */
fun HttpServletRequest.readAllHead(){
    LogUtils.printTitle("请检查请求头参数")
    val headerNames = this.headerNames
    while (headerNames.hasMoreElements()) {
        val name = headerNames.nextElement()
        val headers = this.getHeaders(name)
        while (headers.hasMoreElements()) {
            val value = headers.nextElement()
            LogUtils.printContent("$name: $value")
        }
    }
    LogUtils.printTitle("打印结束")
}