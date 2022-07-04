package io.github.maodua.wrench.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.maodua.wrench.file.bean.FileStore;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IFileStoreMapper extends BaseMapper<FileStore> {
}
