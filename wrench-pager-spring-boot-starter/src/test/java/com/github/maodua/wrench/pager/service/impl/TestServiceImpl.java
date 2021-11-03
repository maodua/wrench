package com.github.maodua.wrench.pager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.maodua.wrench.pager.entity.TestTable;
import com.github.maodua.wrench.pager.mapper.TestMapper;
import com.github.maodua.wrench.pager.service.ITestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 对象关系树 服务实现类
 * </p>
 */
@Service
@Transactional
public class TestServiceImpl extends ServiceImpl<TestMapper, TestTable> implements ITestService {

}
