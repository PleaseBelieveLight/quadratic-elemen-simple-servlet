package com.anim.study.service.comment

import com.anim.study.bean.ErrorRequestException
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.LogUtils
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

abstract class StrictCheckServlet<in T : Any>(
    private val dataClass: Class<T>
) : BaseHttpServlet() {

    final override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {
        if (!checkAuthorization(req, resp)) {
            return
        }
        val inputStream = req.inputStream ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return
        }
        val reqBody = JsonUtils.parseInputSteam(inputStream, dataClass) ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            return
        }
        LogUtils.printContent("request body = $reqBody")
        doSafeResponse(reqBody, req, resp)
    }

    abstract fun doSafeResponse(data: T, req: HttpServletRequest, resp: HttpServletResponse)
}