package com.anim.study.service

import com.anim.study.bean.BasePagerBean
import com.anim.study.bean.BaseRespBean
import com.anim.study.bean.ErrorRequestException
import com.anim.study.call.PagerDataSource
import com.anim.study.dao.RoleMapper
import com.anim.study.data.body.PagerDataBody
import com.anim.study.data.pager.PagerUtils
import com.anim.study.domain.Role
import com.anim.study.state.RespState
import com.anim.study.utils.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * @author ljl
 * @date 2023/4/30 18:17
 * @Description: 所需参数 categoryId
 */
class RolesServlet : BaseHttpServlet() {

    private val roleMapper by lazy {
        getMapper(RoleMapper::class.java)
    }

    override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {
        if (!checkAuthorization(req, resp)){
            return
        }
        val inputStream = req.inputStream ?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return
        }
        JsonUtils.parseInputSteam(inputStream, PagerDataBody<Int>().javaClass)?.run {
            if (!checkNotNull()) {
                resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
                return
            }
            LogUtils.printContent("request body = $this")
            findByUserPager(this)?.let {
                resp.print(JsonUtils.serialize(BaseRespBean.getSucceedRespBean(it)))
            } ?: kotlin.run {
                resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            }
        }?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
            return
        }
    }

    private fun findByUserPager(requestBody: PagerDataBody<Int>): BasePagerBean<Role>? {
        val roleMapper = this.roleMapper ?: return null
        return PagerUtils.coverRespPagerInfo(requestBody,object :PagerDataSource<Role,Int>{
            override fun findListTotalSize(filter : Int): Long {
                return roleMapper.findRolesCount(filter)
            }
            override fun findDataSourceByPage(pagerDataBody: PagerDataBody<Int>): List<Role> {
                return roleMapper.findRolesByPage(pagerDataBody)
            }
        })
    }

    override val respState: RespState
        get() = RespState.GET
}