package com.anim.study.call

import com.anim.study.data.body.PagerDataBody

/**
 * @author ljl
 * @date 2023/4/30 18:17
 * @Description: 分页工具类
 */
interface PagerDataSource<Data : Any, Page> {

    fun findListTotalSize(filter : Page): Long

    fun findDataSourceByPage(pagerDataBody: PagerDataBody<Page>): List<Data>
}