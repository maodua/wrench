package org.pear.wrench.starter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.pear.wrench.starter.entity.TestTable;
import org.pear.wrench.starter.mapper.TestMapper;
import org.pear.wrench.starter.service.ITestService;
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
