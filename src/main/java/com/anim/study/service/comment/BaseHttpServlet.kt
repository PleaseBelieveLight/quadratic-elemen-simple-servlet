package com.anim.study.service.comment

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