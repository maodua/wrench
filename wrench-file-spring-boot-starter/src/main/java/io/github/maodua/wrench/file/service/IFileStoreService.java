package io.github.maodua.wrench.file.service;


import com.baomidou.mybatisplus.extension.service.IService;
import io.github.maodua.wrench.file.bean.FileStore;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * 文件池 服务层接口
 */
public interface IFileStoreService extends IService<FileStore> {
    /**
     * 保存一个文件到文件池中
     * @param multipartFile 请求中的文件
     * @param oid 对象ID可以为空
     * @return 文件的 ID
     */
    String saveFile(@NonNull MultipartFile multipartFile, String oid) throws IOException;

    /**
     * 根据 ID 删除一个文件
     * @param id 文件的 ID
     */
    void deleteFile(@NonNull String id);

}
