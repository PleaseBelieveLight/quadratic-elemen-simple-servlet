package com.anim.study.utils

import org.apache.ibatis.io.Resources
import org.apache.ibatis.session.SqlSession
import org.apache.ibatis.session.SqlSessionFactoryBuilder

object SqlUtils {

     fun getSqlSession() : SqlSession? {
        try {
            //读取Mybatis核心配置文件,放至输入流中(内存)
            val resources = Resources.getResourceAsStream("mybatis-config.xml")
            //创建SessionFactory对象，读取配置信息
            val factory = SqlSessionFactoryBuilder().build(resources)
            //创建一个session
            return factory.openSession()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}