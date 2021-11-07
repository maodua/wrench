package io.github.maodua.wrench.pager.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomizePageDataHandler extends Page<Object> implements IPageDataHandler {
    @Override
    public void setWrenchPage(long page) {
        this.setCurrent(page);
    }
    @Override
    public void setWrenchPageSize(long pageSize) {
        this.setSize(pageSize);
    }
    @Override
    public void setWrenchTotalRow(long totalRow) {
        this.setTotal(totalRow);
    }
    @Override
    public void setWrenchTotalPage(long totalPage) {
        this.setPages(totalPage);
    }
    @Override
    public void setWrenchData(Collection<Object> records) {
        this.setRecords(Collections.singletonList(records));
    }
}
