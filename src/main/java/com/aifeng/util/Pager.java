package com.aifeng.util;

import java.util.List;

/**
 * 分页组件
 *
 * @ClassName: Pagination
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月31日 下午11:48:36
 */
public class Pager {

    int page = 1;
    int pageSize = 10;
    long total;
    int start = 0;
    int pageTotal = 1;
    int end = 0;
    private List<?> results;

    public Pager() {
    }

    public Pager(int page) {
        this.page = page;
    }

    public Pager(int page, int pageSize) {
        this.page = page;
        if (pageSize != 0) {
            this.pageSize = pageSize;
        }
    }

    public int getStart() {
        return (page - 1) * pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize != 0) {
            this.pageSize = pageSize;
        }
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
        this.pageTotal = (int) (total + pageSize - 1) / pageSize;//TODO 怎么感觉这里 少了一页
        if (this.pageTotal == 0)
            this.pageTotal = 1;
    }

    public void setStart(int start) {
        this.start = start;
        this.page = (start + pageSize - 1) / pageSize;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public int getEnd() {
        end = page * pageSize;
        if (end > total) {
            end = (int) total;
        }
        return end;
    }

    public List<?> getResults() {
        return results;
    }

    /**
     * 设置记录
     *
     * @param results
     */
    public void setResults(List<?> results) {
        this.results = results;
    }

}
