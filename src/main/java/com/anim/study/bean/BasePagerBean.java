package com.anim.study.bean;

import java.util.List;

public class BasePagerBean<T> {
    private List<T> data;//返回的记录集合

    private int nextPage;//分页起始页

    private int size;//每页记录数

    private long total;//总记录条数

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "page=" + nextPage +
                ", size=" + size +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}


