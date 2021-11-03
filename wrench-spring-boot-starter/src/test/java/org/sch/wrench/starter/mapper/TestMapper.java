package org.sch.wrench.starter.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sch.wrench.starter.entity.TestTable;
import org.sch.wrench.tree.entity.ObjInTree;

/**
 * <p>
 * 对象关系树 Mapper 接口
 * </p>
 */
@Mapper
public interface TestMapper extends BaseMapper<TestTable> {

}
