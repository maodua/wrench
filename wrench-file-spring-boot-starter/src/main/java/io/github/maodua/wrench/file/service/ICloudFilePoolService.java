package io.github.maodua.wrench.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.maodua.wrench.file.bean.FilePool;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ICloudFilePoolService extends IService<FilePool> {
    /**
     * 上传文件
     * @param filePool 文件
     * @return 存储是否成功
     */
    boolean upload(FilePool filePool);

    /**
     * 获取文件
     * @param id 文件id
     * @return 文件信息
     */
    FilePool get(String id);

    /**
     * 获取多个文件
     * @param ids 多个文件id
     * @return 多个文件
     */
    List<FilePool> listById(Collection<String> ids);

    /**
     * 字典类型文件数据
     * @param ids 多个文件id
     * @return 字典类型文件信息，key是文件id
     */
    default Map<String, FilePool> mapById(Collection<String> ids){
        List<FilePool> filePool = listById(ids);
        Map<String, FilePool> map = new HashMap<>();
        filePool.forEach(f -> map.put(f.getId(), f));
        return map;
    }

    /**
     * 根据其他属性多个文件
     * @param oid 多个其他属性
     * @return 文件信息
     */
    List<FilePool> listByOid(String oid);
}
