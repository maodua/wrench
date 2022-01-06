package io.github.maodua.wrench.file.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.maodua.wrench.file.bean.FilePool;
import io.github.maodua.wrench.file.mapper.IFilePoolMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CloudFilePoolServiceImpl extends ServiceImpl<IFilePoolMapper, FilePool> implements ICloudFilePoolService {

    @Override
    public boolean upload(FilePool filePool) {
        return this.save(filePool);
    }

    @Override
    public FilePool get(String id) {
        return this.baseMapper.selectById(id);
    }

    @Override
    public List<FilePool> listById(Collection<String> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return this.listByIds(ids);
    }

    @Override
    public List<FilePool> listByOid(String oids) {
        return this.lambdaQuery().eq(FilePool::getOid, oids).list();
    }
}
