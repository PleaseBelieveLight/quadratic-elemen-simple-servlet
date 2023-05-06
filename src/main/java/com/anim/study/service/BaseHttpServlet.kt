package com.anim.study.service

import com.anim.study.bean.ErrorRequestException
import com.anim.study.data.DataProvider
import com.anim.study.state.RespState
import com.anim.study.utils.SqlUtils
import com.anim.study.utils.printUsuallyState
import com.anim.study.utils.readAllHead
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.ibatis.session.SqlSession

abstract class BaseHttpServlet : HttpServlet() {

    final override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        initHttpServlet(req, resp, RespState.GET)
    }

    final override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        initHttpServlet(req, resp, RespState.POST)
    }

    /**
     * 响应方法
     */
    abstract fun doResponse(req: HttpServletRequest, resp: HttpServletResponse)

    /**
     * 请求类型，必须实现
     */
    abstract val respState: RespState

    private fun initHttpServlet(req: HttpServletRequest, resp: HttpServletResponse, nowRespState: RespState) {
        if (respState != nowRespState) {
            resp.printUsuallyState(ErrorRequestException.PostTypeException(respState))
            return
        }
        initRespBody(req, resp)
        doResponse(req, resp)
    }

    fun checkAuthorization(req: HttpServletRequest, resp: HttpServletResponse): Boolean {
        val authorization: String? = req.getHeader("Authorization")
        if (authorization != DataProvider.AuthorizationToken) {
            req.readAllHead()
            resp.printUsuallyState(ErrorRequestException.IdCheckErrorException)
            return false
        }
        return true
    }

    private fun initRespBody(req: HttpServletRequest,response: HttpServletResponse) {
        //响应类型
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        //请求编码类型
        req.characterEncoding = "UTF-8"
    }

    /**
     * 管理 session 类，需要释放资源
     */
    private var sqlSession: SqlSession ?= null

    fun <T> getMapper(clazz: Class<T>): T? {
        if (sqlSession == null){
            sqlSession = SqlUtils.getSqlSession()
        }
        return sqlSession?.getMapper(clazz)
    }

    override fun destroy() {
        super.destroy()
        //清理资源
        sqlSession?.close()
    }
}