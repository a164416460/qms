package com.jrs_qms_llh.commons.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用分页类
 * 泛型可以实现任何项目通用的类
 *
 * @param <T>
 * @author 黄浩文
 */
public class Page<T> {
    private int currentPage; //当前页
    private int totalPage; //总页数
    private int count; //总数据条数
    private int pageSize;   //每页显示的条数
    private List<T> list = new ArrayList<>(); //每页显示的记录详细信息

    public Page() {
    }

    public Page(int currentPage, int count, int pageSize, List<T> list) {
        this.currentPage = currentPage;
        this.count = count;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        // 总页数 = 总条数 ÷ 每页显示数
        totalPage = count / pageSize;
        totalPage += count % pageSize == 0 ? 0 : 1;
        return totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", count=" + count +
                ", pageSize=" + pageSize +
                ", list=" + list +
                '}';
    }
}
