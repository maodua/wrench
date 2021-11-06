package io.github.maodua.wrench.pager.handler;


import io.github.maodua.wrench.pager.bo.PageBody;

import java.util.Collection;

/**
 * 默认的分页数据处理器
 */
public class DefaultPageDataHandler extends PageBody<Object> implements IPageDataHandler {


    @Override
    public void setWrenchPage(long page) {
        this.setPage(page);
    }

    @Override
    public void setWrenchPageSize(long pageSize) {
        this.setPageSize(pageSize);
    }

    @Override
    public void setWrenchTotalRow(long totalRow) {
        this.setTotalRow(totalRow);
    }

    @Override
    public void setWrenchTotalPage(long totalPage) {
        this.setTotalPage(totalPage);
    }

    @Override
    public void setWrenchData(Collection<Object> records) {
        this.setRecords(records);
    }
}
