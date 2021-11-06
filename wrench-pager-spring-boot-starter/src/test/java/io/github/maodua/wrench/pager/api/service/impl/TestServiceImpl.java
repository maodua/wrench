package io.github.maodua.wrench.pager.api.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.maodua.wrench.pager.api.entity.TestTable;
import io.github.maodua.wrench.pager.api.mapper.TestMapper;
import io.github.maodua.wrench.pager.api.service.ITestService;
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
