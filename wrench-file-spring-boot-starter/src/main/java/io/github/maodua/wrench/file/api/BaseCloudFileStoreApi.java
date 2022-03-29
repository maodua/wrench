package io.github.maodua.wrench.file.api;

import io.github.maodua.wrench.common.util.Assert;
import io.github.maodua.wrench.common.util.Strings;
import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.file.autoconfig.FileStoreConfigure;
import io.github.maodua.wrench.file.bean.FileStore;
import io.github.maodua.wrench.file.service.IFileStoreService;
import io.github.maodua.wrench.file.vo.CloudFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 云文件池
 */
//@RestController
//@RequestMapping("cloud")
public class BaseCloudFileStoreApi {
    @Autowired
    private IFileStoreService fileStoreService;
    @Autowired
    private FileStoreConfigure fileStoreConfigure;

    @PostMapping(path = "upload")
    public Result<String> upload(@Valid @RequestBody CloudFile file){
        // 访问路径
        String url = file.getUrlpath();
        if (fileStoreConfigure.getUilReplace() && url.startsWith(fileStoreConfigure.getUilPrefix())){
            // 替换指定前缀
            url = url.replaceFirst(url, fileStoreConfigure.getUilPrefix());
        }
        // 文件类型默认
        String fileType = "octet-stream";
        if(!Strings.isBlank(file.getFileType())){
            // 用户上传的文件类型
            fileType = file.getFileType();
        }else if (file.getFilename().contains(".")) {
            // 通过文件名获取的文件类型
            String[] split = file.getFilename().split("\\.");
            fileType = split[split.length - 1];
        }
        // 保存文件
        FileStore fileStore = new FileStore()
            .setOid(null)
            .setFilename(file.getFilename())
            .setFilepath(url)
            .setFiletype(fileType)
            .setLocal(false)
            .setAddtime(LocalDateTime.now())
            .setExtend(file.getExtend())
            .setSize(0L);
        fileStoreService.save(fileStore);

        return Result.success(fileStore.getId());
    }

    @GetMapping("info/{id}")
    public Result<CloudFile> info(@PathVariable String id) {
        // 数据库获取
        FileStore fileStore = fileStoreService.getById(id);
        Assert.notNull(fileStore, "没有找到文件");
        CloudFile file = new CloudFile();
        file.setFilename(fileStore.getFilename());
        file.setExtend(fileStore.getExtend());
        file.setFileType(fileStore.getFiletype());
        file.setUrlpath(fileStore.getFilepath());
        if (fileStoreConfigure.getUilReplace()){
            file.setUrlpath(fileStoreConfigure.getUilPrefix() + fileStore.getFilepath());
        }
        return Result.success(file);
    }
}
