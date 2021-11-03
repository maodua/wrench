package io.github.maodua.wrench.starter.web;

import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.pager.annotation.Pager;
import io.github.maodua.wrench.pager.util.Pagers;
import io.github.maodua.wrench.starter.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @Pager
    @GetMapping("list")
    public Result<?> list(){
        var list = testService.list();

        return Result.success(list);
    }

    @Pager
    @GetMapping("list2")
    public Result<?> list2(){
        var list = Pagers.skipPager(() -> testService.list());

        return Result.success(list);
    }
}
