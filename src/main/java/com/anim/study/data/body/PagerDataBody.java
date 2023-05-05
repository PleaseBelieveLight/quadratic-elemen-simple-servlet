package com.anim.study.data.body;

public class PagerDataBody<T> {

    private int page;

    private int size;

    private T filter;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public T getFilter() {
        return filter;
    }

    public void setFilter(T filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        return "PagerDataBody{" +
                "page=" + page +
                ", size=" + size +
                ", filter=" + filter +
                '}';
    }

    public boolean checkNotNull(){

        return filter != null;
    }
}
