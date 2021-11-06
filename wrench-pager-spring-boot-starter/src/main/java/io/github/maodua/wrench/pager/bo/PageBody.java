package io.github.maodua.wrench.pager.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * 分页返回值的 data 部分
 * @param <T> 实际数据的数据类型
 */
@Data
@Accessors(chain = true)
public class PageBody<T> {
    /**
     * 实际的数据列表
     */
    private Collection<T> records;
    /**
     * 当前是第几页
     */
    private long page;
    /**
     * 页大小
     */
    private long pageSize;
    /**
     * 总记录数
     */
    private long totalRow;
    /**
     * 总页数
     */
    private long totalPage;
}
