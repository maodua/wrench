package io.github.maodua.wrench.pager.aop;

import com.github.pagehelper.Page;
import io.github.maodua.wrench.pager.autoconfigure.WrenchPagerConfigure;
import io.github.maodua.wrench.pager.exception.PagerException;
import io.github.maodua.wrench.pager.handler.IPageDataHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import io.github.maodua.wrench.common.util.Servlets;
import io.github.maodua.wrench.common.util.Springs;
import io.github.maodua.wrench.pager.annotation.Pager;
import io.github.maodua.wrench.pager.handler.IResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.pagehelper.PageHelper;

import java.util.*;

/**
 * 分页插件
 */
@Component
@Aspect
public class PagerPlugin {

    @Autowired
    private WrenchPagerConfigure pagerConfigure;


    @Around("@annotation(pager)")
    public Object aroundHandler(ProceedingJoinPoint point, Pager pager) throws Throwable{
        // 获取参数
        // 1. 设置分页属性
        this.setPager(pager);

        // 2. 执行原有方法
        Page<Object> localPage = PageHelper.getLocalPage();
        Object returnObject = point.proceed();
        // 防止没有清理当前线程page
        PageHelper.clearPage();


        // 3. 修改返回值并返回
        // 空值不处理
        if (returnObject == null) return null;

        IResultHandler resultHandler = Springs.getBeansList(IResultHandler.class).stream().findFirst().orElseThrow(() -> new PagerException("没有找到返回值处理器。"));
        // 获取查询结果
        Object queryData = resultHandler.getData(returnObject);

        // 查询结果为空，返回空list
        if (queryData == null) resultHandler.setData(returnObject, Collections.emptyList());

        // 获取分页数据处理器
        IPageDataHandler pageDataHandler = Springs.getBeansList(IPageDataHandler.class).stream().findFirst().orElseThrow(() -> new PagerException("没有找到返回结果处理器"));
        Object pageDate = pageDateObject(pageDataHandler, localPage, queryData);
        resultHandler.setData(returnObject, pageDate);

        return returnObject;
    }

    /**
     * 设置分页参数
     * @param pager 分页相关的默认值
     */
    void setPager(Pager pager){
        // 获取Request请求中的参数
        String pageStr = Servlets.getRequest().getParameter(pagerConfigure.getPage());
        String pageSizeStr = Servlets.getRequest().getParameter(pagerConfigure.getPageSize());

        // 页数 和 页大小
        int page = Optional.ofNullable(pageStr).map(Integer::valueOf).orElse(pager.page());
        int pageSize = Optional.ofNullable(pageSizeStr).map(Integer::valueOf).orElse(pager.pageSize());
        pageSize = Math.min(Math.max(pageSize, pager.minSize()),pager.maxSize());

        // 设置分页属性
        PageHelper.clearPage();
        PageHelper.startPage(page, pageSize);
    }

    /**
     * 分页数据对象处理
     * @param handler 分页数据处理器
     * @param page 分页数据
     * @param queryData 查询结果
     * @return 分页数据
     */
    Object pageDateObject(IPageDataHandler handler, Page<Object> page, final Object queryData){
        handler.setWrenchPage(page.getPageNum());
        handler.setWrenchPageSize(page.getPageSize());
        handler.setWrenchTotalRow(page.getTotal());
        handler.setWrenchTotalPage(page.getPages());

        if (queryData instanceof Collection){
            Collection data = (Collection) queryData;
            // 查询结果是集合添加分页属性
            handler.setWrenchData(data);

        }else {
            // 查询结果不是集合自动装成集合
            handler.setWrenchData(Arrays.asList(queryData));
        }
        return handler;
    }

}
