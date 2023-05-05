package com.anim.study.data.pager

import com.anim.study.bean.BasePagerBean
import com.anim.study.call.PagerDataSource
import com.anim.study.data.body.PagerDataBody

/**
 * @author ljl
 * @date 2023/4/30 18:17
 * @Description: 分页工具类
 */
object PagerUtils{

    fun <Data : Any , Page> coverRespPagerInfo(
        requestBody: PagerDataBody<Page>,
        pagerDataSource: PagerDataSource<Data,Page>
    ) :BasePagerBean<Data> {
        val pagerDataBody = PagerDataBody<Page>()
        val size = requestBody.size
        val page = requestBody.page
        //请求数据
        pagerDataBody.page = (page - 1) * size
        pagerDataBody.size = size
        pagerDataBody.filter = requestBody.filter
        //返回结果
        val list : List<Data> = pagerDataSource.findDataSourceByPage(pagerDataBody)
        val totalSize = pagerDataSource.findListTotalSize(requestBody.filter)
        val pager = BasePagerBean<Data>()
        pager.data = list
        pager.total = totalSize
        //后面一页
        pager.nextPage = if (list.size < size) {
            -1
        } else {
            if (size * page < totalSize) {
                page + 1
            } else {
                -1
            }
        }
        pager.size = list.size
        return pager
    }
}