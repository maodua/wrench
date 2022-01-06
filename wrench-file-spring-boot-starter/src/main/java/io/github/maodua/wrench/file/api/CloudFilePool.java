package io.github.maodua.wrench.file.api;

import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.file.bean.FilePool;
import io.github.maodua.wrench.file.bean.FileType;
import io.github.maodua.wrench.file.service.ICloudFilePoolService;
import io.github.maodua.wrench.file.vo.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * 云文件池
 */
@RestController
@RequestMapping("cloud")
public class CloudFilePool {
    @Autowired
    private ICloudFilePoolService cloudFilePoolService;

    @PostMapping(path = "/upload")
    public Result<Boolean> upload(@Valid @RequestBody File file){
        FileType type = FileType.FILE;
        try {
            type = FileType.valueOf(file.getFileType());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        var filePool = new FilePool()
            .setOid("0")
            .setFilename(file.getFilename())
            .setFilepath(file.getFilepath())
            .setFiletype(type)
            .setLocal(false)
            .setAddtime(LocalDateTime.now())
            .setExtend(file.getExtend())
            .setSize(file.getSize());

        boolean upload = cloudFilePoolService.upload(filePool);
        return Result.auto(upload);
    }

    @GetMapping("/{id}")
    public Result<FilePool> show(@PathVariable String id) {
        var filePool = cloudFilePoolService.getById(id);
        return Result.success(filePool);
    }
}
