package com.anim.study.service.comment

import com.anim.study.utils.LogUtils
import jakarta.servlet.http.HttpServletResponse
import kotlin.reflect.KFunction
import kotlin.reflect.jvm.isAccessible

/**
 * @Author: ljl
 * @Description: 通过反射减少子类IF判断
 * @Date: 2023/5/10
 */
abstract class CommentFrameServlet : BaseHttpServlet() {

    private val functionMap = hashMapOf<String, KFunction<Unit>>()

    internal fun addFunction(function: KFunction<Unit>, otherName: String = function.name) {
        functionMap[otherName] = function
    }

    /**
     * 如果这里处理了异常，上级异常处理就不会触发
     */
    @Throws(Exception::class)
    internal fun <T : Any> startServletPathTask(
        taskName: String,
        resp: HttpServletResponse? = null,
        data: T? = null
    ) {
        try {
            startServletTask(taskName, resp, data)
        } catch (e: Exception) {
            //这里打印一下日志，再将异常传递下去
            LogUtils.printError(e.message)
            throw Exception("方法调用异常")
        }
    }

    private fun <T : Any> startServletTask(taskName: String, resp: HttpServletResponse?, data: T?) {
        functionMap[taskName]?.let { function ->
            //设置private私有方法可以访问
            function.isAccessible = true
            if (resp != null && data != null) {
                function.call(resp, data)
                return
            }
            if (resp != null) {
                function.call(resp)
                return
            }
            function.call()
        } ?: kotlin.run {
            LogUtils.printError("没有找到可调用函数")
        }
    }
}
