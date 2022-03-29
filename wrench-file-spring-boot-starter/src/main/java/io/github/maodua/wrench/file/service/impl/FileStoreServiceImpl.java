package io.github.maodua.wrench.file.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.maodua.wrench.file.autoconfig.FileStoreConfigure;
import io.github.maodua.wrench.file.bean.FileStore;
import io.github.maodua.wrench.file.mapper.IFileStoreMapper;
import io.github.maodua.wrench.file.service.IFileStoreService;
import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

/**
 * 文件池 服务层
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED)
public class FileStoreServiceImpl extends ServiceImpl<IFileStoreMapper, FileStore> implements IFileStoreService {
    @Autowired
    private FileStoreConfigure fileConfigure;

    @Override
    public String saveFile(@NonNull MultipartFile multipartFile, String oid) throws IOException {
        // 文件名
        String fileName = multipartFile.getOriginalFilename();
        // 文件类型
        String fileType = multipartFile.getContentType();
        // 文件大小
        long fileSize = multipartFile.getSize();

        String path1 = RandomStringUtils.randomAlphanumeric(2).toUpperCase();
        String path2 = RandomStringUtils.randomAlphanumeric(2).toUpperCase();
        // 随机文件名
        String randomFileName = RandomStringUtils.randomAlphanumeric(16).toUpperCase();
        // 相对路径
        Path relativePath = Paths.get(path1, path2, randomFileName);
        // 文件夹绝对路径
        File absolutePathFolder = Paths.get(fileConfigure.getRootPath(), path1, path2).toFile();
        if (!absolutePathFolder.exists()){
            if (!absolutePathFolder.mkdirs())
                throw new IllegalStateException(String.format("上传目录 %s 创建失败", absolutePathFolder.getAbsolutePath()));
        }
        // 绝对路径
        File absolutePath = Paths.get(absolutePathFolder.getAbsolutePath(), randomFileName).toFile();

        multipartFile.transferTo(absolutePath);

        FileStore fileStore = new FileStore()
            .setOid(oid)
            .setLocal(true)
            .setFilename(fileName)
            .setFiletype(fileType)
            .setSize(fileSize)
            .setFilepath(relativePath.toString())
            .setAddtime(LocalDateTime.now());
        this.save(fileStore);
        return fileStore.getId();
    }

    @Override
    public void deleteFile(@NonNull String id) {
        // 获取文件
        FileStore fileStore = this.getById(id);

        // 如果文件存则删除文件
        if (fileStore != null) {
            // 删除文件在数据库中的记录
            this.removeById(id);

            // 删除文件
            File file = new File(fileConfigure.getRootPath(), fileStore.getFilepath());
            if (file.exists() && !file.delete()) {
                // 如果删除失败则设置虚拟机退出时再次尝试删除
                file.deleteOnExit();
            }
        }
    }

}
