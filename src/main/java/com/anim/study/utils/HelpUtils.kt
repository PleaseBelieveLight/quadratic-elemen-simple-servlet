package com.anim.study.utils

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import jakarta.servlet.http.HttpServletResponse

fun HttpServletResponse.print(msg : String){
    this.writer?.println(msg)
}

fun HttpServletResponse.printUsuallyState(state : ErrorRequestException){
    print(JsonUtils.serialize(BaseRespBean.getErrorRespBean(state)))
}

fun Any?.hasData() : String {
    return if (this is Collection<*> && isEmpty()) {
        "没有查找到数据"
    } else {
        "成功"
    }
}