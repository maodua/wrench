package org.pear.wrench.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.pear.wrench.starter.entity.TestTable;

/**
 * <p>
 * 对象关系树 Mapper 接口
 * </p>
 */
@Mapper
public interface TestMapper extends BaseMapper<TestTable> {

}
