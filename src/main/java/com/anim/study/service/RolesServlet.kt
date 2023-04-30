package com.anim.study.service

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import com.anim.study.dao.RoleMapper
import com.anim.study.state.RespState
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.print
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * @author ljl
 * @date 2023/4/30 18:17
 * @Description: 所需参数 categoryId
 */
class RolesServlet : BaseHttpServlet() {

    override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {
        val categoryId = req.getParameter("categoryId")?.toIntOrNull() ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return
        }
        getMapper(RoleMapper::class.java)?.selectByCategoryKey(categoryId)?.let {
            resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
        }?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    override val respState: RespState
        get() = RespState.GET
}