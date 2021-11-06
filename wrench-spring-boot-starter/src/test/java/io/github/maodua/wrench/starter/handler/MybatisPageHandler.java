package io.github.maodua.wrench.starter.handler;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.maodua.wrench.pager.handler.IPageDataHandler;

import java.util.Collection;
import java.util.List;

public class MybatisPageHandler extends Page<Object> implements IPageDataHandler {
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
        this.setRecords(List.copyOf(records));
    }
}
