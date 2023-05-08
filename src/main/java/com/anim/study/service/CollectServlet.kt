package com.anim.study.service

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import com.anim.study.dao.CollectMapper
import com.anim.study.data.DataProvider
import com.anim.study.domain.Collect
import com.anim.study.service.comment.StrictCheckServlet
import com.anim.study.state.RespState
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.print
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.util.*

class CollectServlet : StrictCheckServlet<Collect>(Collect::class.java) {

    override fun doSafeResponse(data: Collect, req: HttpServletRequest, resp: HttpServletResponse) {
        val servletPath = req.servletPath
        if (data.id != null && servletPath == DataProvider.CollectFindUrl) {
            //查找收藏
            getMapper(CollectMapper::class.java)?.selectCollectRoles(data.id)?.let {
                resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
            } ?: kotlin.run {
                resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            }
        } else {
            val result = if (data.roleId != null && data.userId != null && servletPath == DataProvider.CollectSaveUrl) {
                //插入收藏记录
                data.collectTime = Date()
                getMapper(CollectMapper::class.java)?.insert(data)
            } else if (data.id != null && servletPath == DataProvider.CollectDeleteUrl) {
                //删除收藏
                getMapper(CollectMapper::class.java)?.deleteByPrimaryKey(data.id)
            } else {
                null
            }
            //输出结果
            if (result == null) {
                resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            } else {
                resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(result)))
            }
        }
    }

    override val respState: RespState
        get() = RespState.POST
}