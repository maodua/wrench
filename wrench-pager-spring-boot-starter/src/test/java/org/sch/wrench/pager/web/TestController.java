package org.sch.wrench.pager.web;

import org.sch.wrench.common.vo.result.Result;
import org.sch.wrench.pager.annotation.Pager;
import org.sch.wrench.pager.service.ITestService;
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
}
