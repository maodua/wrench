package io.github.maodua.wrench.pager.handler;

import java.util.Collection;

public interface IPageDataHandler {

    /**
     * 设置当前页
     * @param page 当前页
     */
    void setWrenchPage(long page);

    /**
     * 设置页大小
     * @param pageSize 当前页
     */
    void setWrenchPageSize(long pageSize);

    /**
     * 设置总行数
     * @param totalRow 总行数
     */
    void setWrenchTotalRow(long totalRow);

    /**
     * 设置总页数
     * @param totalPage 总页数
     */
    void setWrenchTotalPage(long totalPage);
    /**
     * 设置分页数据
     * @param records 分页数据
     */
    void setWrenchData(Collection<Object> records);
}
