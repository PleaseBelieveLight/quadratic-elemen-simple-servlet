package com.anim.study.service

import com.anim.study.bean.ErrorRequestException
import com.anim.study.state.RespState
import com.anim.study.utils.SqlUtils
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.apache.ibatis.session.SqlSession
import org.apache.log4j.Logger

abstract class BaseHttpServlet : HttpServlet() {

    private val logger = Logger.getLogger(BaseHttpServlet::class.java)

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
        initRespBody(resp)
        doResponse(req, resp)
    }

    private fun initRespBody(response: HttpServletResponse) {
        response.contentType = "text/html" //解决中文乱码
        response.characterEncoding = "UTF-8"
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

    /**
     * 常用日志格式
     */
    fun printTitle(msg:String){
        logger.info("-==>------------$msg---------------<==")
    }

    fun printContent(msg: String){
        logger.info("content-==>：$msg")
    }

    fun printError(msg: String){
        logger.info("******************** error：$msg ********************")
    }

    override fun destroy() {
        super.destroy()
        //清理资源
        sqlSession?.close()
    }
}