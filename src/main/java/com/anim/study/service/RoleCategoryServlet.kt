package com.anim.study.service

import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import com.anim.study.dao.CategoryMapper
import com.anim.study.service.comment.BaseHttpServlet
import com.anim.study.state.RespState
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.print
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * @author ljl
 * @date 2023/4/30 18:17
 * @Description: code
 */
class RoleCategoryServlet : BaseHttpServlet() {

    override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {
        getMapper(CategoryMapper::class.java)?.selectAllCategory()?.let {
            resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
        }?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    override val respState: RespState
        get() = RespState.GET
}