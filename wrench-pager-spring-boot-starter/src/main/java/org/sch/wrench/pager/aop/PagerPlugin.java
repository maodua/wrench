package org.sch.wrench.pager.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.sch.wrench.common.util.Servlets;
import org.sch.wrench.common.util.Springs;
import org.sch.wrench.pager.annotation.Pager;
import org.sch.wrench.pager.bo.PageBody;
import org.sch.wrench.pager.handler.IResultHandler;
import org.sch.wrench.pager.handler.DefaultResultHandler;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 分页插件
 */
@Component
@Aspect
public class PagerPlugin {


    @Around("@annotation(pager)")
    public Object aroundHandler(ProceedingJoinPoint point, Pager pager) throws Throwable{
        // 获取参数
        // 1. 设置分页属性
        this.setPager(pager);

        // 2. 执行原有方法
        var localPage = PageHelper.getLocalPage();
        var returnObject =  point.proceed();
        // 防止没有清理当前线程page
        PageHelper.clearPage();


        // 3. 修改返回值并返回

        // 空值不处理
        if (returnObject == null) return null;
        // 获取返回值处理器
        var resultHandler = Springs.getBeansList(IResultHandler.class).stream().findFirst().orElse(new DefaultResultHandler());
        // 获取数据
        var data = resultHandler.getData(returnObject);
        // 返回值 data 为空，返回空list
        if (data == null) {
            resultHandler.setData(returnObject, Collections.emptyList());
        }
        // 返回值是集合添加分页属性
        if (data instanceof Collection){
            @SuppressWarnings("unchecked")
            var pageBody = new PageBody()
                .setPage(localPage.getPageNum())
                .setTotalRow(localPage.getTotal())
                .setTotalPage(localPage.getPages())
                .setListData(List.copyOf((Collection) data));
            resultHandler.setData(returnObject, pageBody);
        }

        return returnObject;
    }

    void setPager(Pager pager){
        // 获取Request请求中的参数
        var pageStr = Servlets.getRequest().getParameter("page");
        var pageSizeStr = Servlets.getRequest().getParameter("pageSize");

        // 页数 和 页大小
        int page = Optional.ofNullable(pageStr).map(Integer::getInteger).orElse(pager.page());
        int pageSize = Optional.ofNullable(pageSizeStr).map(Integer::getInteger).orElse(pager.pageSize());
        pageSize = Math.min(Math.max(pageSize, pager.minSize()),pager.maxSize());

        // 设置分页属性
        PageHelper.clearPage();
        PageHelper.startPage(page, pageSize);
    }

}
