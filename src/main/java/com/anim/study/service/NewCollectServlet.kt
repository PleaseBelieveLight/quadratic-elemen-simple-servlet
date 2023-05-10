package com.anim.study.service

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import com.anim.study.dao.CollectMapper
import com.anim.study.data.DataProvider
import com.anim.study.domain.Collect
import com.anim.study.service.comment.CommentFrameServlet
import com.anim.study.state.RespState
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.VisitSafeCheckUtil
import com.anim.study.utils.print
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.*

/**
 * 优化字段过多的情况 ， 函数参数字段务必要对应
 */
class NewCollectServlet : CommentFrameServlet() {

    init {
        //将响应函数加入
        addFunction(this::deleteCollects, DataProvider.CollectDeleteUrl)
        addFunction(this::saveCollects, DataProvider.CollectSaveUrl)
        addFunction(this::findCollects, DataProvider.CollectFindUrl)
    }

    override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {
        VisitSafeCheckUtil.doSafePagerResponse<Collect>(req, resp)?.let { data ->
            val servletPath = req.servletPath
            startServletPathTask(servletPath, resp, data)
        }
    }

    /**
     * 查找收藏角色列表
     */
    private fun findCollects(resp: HttpServletResponse, data: Collect) {
        getMapper(CollectMapper::class.java)?.selectCollectRoles(data.id)?.let {
            resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
        } ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    /**
     * 插入收藏记录
     */
    private fun saveCollects(resp: HttpServletResponse, data: Collect) {
        //参数判断
        if (data.roleId == null || data.userId == null){
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return
        }
        data.collectTime = Date()
        getMapper(CollectMapper::class.java)?.insert(data)?.takeIf {
            it > 0
        }?.let {
            mSqlSession.commit()
            resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
        } ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    /**
     * 删除收藏
     */
    private fun deleteCollects(resp: HttpServletResponse, data: Collect) {
        getMapper(CollectMapper::class.java)?.deleteByPrimaryKey(data.id)?.takeIf {
            it > 0
        }?.let {
            mSqlSession.commit()
            resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
        } ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    override val respState: RespState
        get() = RespState.POST
}