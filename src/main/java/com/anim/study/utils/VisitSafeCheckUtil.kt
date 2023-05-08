package com.anim.study.utils

import com.anim.study.bean.ErrorRequestException
import com.anim.study.data.DataProvider
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

object VisitSafeCheckUtil {

    /**
     * 安全获取 body
     */
    inline fun <reified T : Any> doSafePagerResponse(req: HttpServletRequest, resp: HttpServletResponse): T? {
        if (!checkAuthorization(req, resp)) {
            return null
        }
        val inputStream = req.inputStream ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return null
        }
        val reqBody = JsonUtils.parseInputSteam(inputStream, T::class.java) ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            return null
        }
        LogUtils.printContent("request body = $reqBody")
        return reqBody
    }

    @JvmStatic
    fun checkAuthorization(req: HttpServletRequest, resp: HttpServletResponse): Boolean {
        val authorization: String? = req.getHeader("Authorization")
        if (authorization != DataProvider.AuthorizationToken) {
            req.readAllHead()
            resp.printUsuallyState(ErrorRequestException.IdCheckErrorException)
            return false
        }
        return true
    }
}