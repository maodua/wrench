package io.github.maodua.wrench.pager.api.web;


import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.pager.annotation.Pager;
import io.github.maodua.wrench.pager.api.entity.TestTable;
import io.github.maodua.wrench.pager.api.service.ITestService;
import io.github.maodua.wrench.pager.handler.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @Pager
    @GetMapping("list")
    public R<?> list(){
        List list = testService.list();
        return R.success(list);
    }


    @Pager
    @GetMapping("list2")
    public Result<?> list2(){
        List list = testService.list();
        return Result.success(list);
    }

    @Pager
    @GetMapping("list3")
    public Result<?> list3(){
        List<TestTable> list = testService.list();
        return Result.success(list.get(0));
    }
}
