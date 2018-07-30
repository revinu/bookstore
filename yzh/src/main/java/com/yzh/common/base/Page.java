package com.yzh.common.base;

import java.io.Serializable;

/**
 * 分页类
 *
 * @author yzh
 * @date 2018/1/31 3:35
 */
public class Page implements Serializable {

    private int totalCount;//总数据条数
    private int pageSize = 5;//每页条数   0:不分页
    private int curPage = 1;//页码
    private int pageCount;//总页数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        if (curPage <= 0) {
            curPage = 1;
        }
        this.curPage = curPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", pageSize=" + pageSize +
                ", curPage=" + curPage +
                ", pageCount=" + pageCount +
                '}';
    }

    public void forbiddenPagination() {
        this.pageSize = 0;
    }

}
