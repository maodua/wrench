package org.sch.wrench.starter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.sch.wrench.starter.entity.TestTable;
import org.sch.wrench.starter.mapper.TestMapper;
import org.sch.wrench.starter.service.ITestService;
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
