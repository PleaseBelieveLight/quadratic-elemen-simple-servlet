package com.anim.study.utils
import org.apache.log4j.Logger

/**
 * 常用日志格式
 */
object LogUtils {

    @JvmStatic
    private val logger = Logger.getLogger(LogUtils::class.java)

    @JvmStatic
    fun printTitle(msg:String){
        logger.info("===>------------$msg---------------<===")
    }

    @JvmStatic
    fun printContent(msg: String){
        logger.info("content===>：$msg")
    }

    @JvmStatic
    fun printError(msg: String){
        logger.info("******************** error：$msg ********************")
    }
}