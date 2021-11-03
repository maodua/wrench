package io.github.maodua.wrench.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.maodua.wrench.starter.entity.TestTable;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 对象关系树 Mapper 接口
 * </p>
 */
@Mapper
public interface TestMapper extends BaseMapper<TestTable> {

}
