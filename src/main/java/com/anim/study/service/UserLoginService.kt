package com.anim.study.service

import com.anim.study.bean.ErrorRequestException
import com.anim.study.dao.UserMapper
import com.anim.study.state.RespState
import com.anim.study.utils.JsonUtils
import com.anim.study.utils.print
import com.anim.study.utils.printUsuallyState
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

/**
 * @author ljl
 * @date 2022/12/11 19:31
 * @Description: 简单登录 ，实际使用需要加上 token验证，密码加密
 */
class UserLoginService : BaseHttpServlet() {

    override fun doResponse(req: HttpServletRequest, resp: HttpServletResponse) {

        val username : String? = req.getParameter("username")
        val password : String? = req.getParameter("password")

        if (username == null || password == null){
            resp.printUsuallyState(ErrorRequestException.FileIdLoseException)
            return
        }

        printTitle("UserLoginService username = $username , password = $password")

        getMapper(UserMapper::class.java)?.selectByPrimaryKey(username,password)?.let {
            resp.print(JsonUtils.serialize(it))
        }?: kotlin.run {
            resp.printUsuallyState(ErrorRequestException.NoFoundDataException)
        }
    }

    override val respState: RespState
        get() = RespState.POST
}