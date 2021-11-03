package org.pear.wrench.pager.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页返回值的 data 部分
 * @param <T> 实际数据的数据类型
 */
@Data
@Accessors(chain = true)
public final class PageBody<T> {
    /**
     * 实际的数据列表
     */
    private List<T> listData;
    /**
     * 当前是第几页
     */
    private long page;
    /**
     * 总记录数
     */
    private long totalRow;
    /**
     * 总页数
     */
    private long totalPage;
}
