package io.github.maodua.wrench.file.api;

import io.github.maodua.wrench.common.vo.result.Result;
import io.github.maodua.wrench.file.autoconfig.FileStoreConfigure;
import io.github.maodua.wrench.file.bean.FileStore;
import io.github.maodua.wrench.file.service.IFileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 本地文件存储
 */
//@RestController
//@RequestMapping("local")
public class BaseLocalFileStoreApi {
    @Autowired
    private FileStoreConfigure fileStoreConfigure;
    @Autowired
    private IFileStoreService fileStoreService;

    @PostMapping("upload")
    @ResponseBody
    public Result<String> upload(@RequestPart MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return Result.fail("上传失败");
        }
        String id = this.fileStoreService.saveFile(file, null);
        return Result.success(id);

    }

    @GetMapping("{id}")
    public ResponseEntity<Resource> show(@PathVariable String id) throws UnsupportedEncodingException {
        FileStore fileStore = fileStoreService.getById(id);
        // 文件路径
        Path filePath = Paths.get(fileStoreConfigure.getRootPath(), fileStore.getFilepath());
        // 转码后的文件名
        String fileName = URLEncoder.encode(fileStore.getFilename(), String.valueOf(StandardCharsets.UTF_8));
        // header 中 content-disposition
        String contentDisposition = "inline; filename*=utf-8''" + fileName;

        // MediaType
        MediaType mediaType = MediaType.parseMediaType(fileStore.getFiletype());
        // 返回文件资源
        return ResponseEntity.ok()
            .contentType(mediaType)
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .body(new FileSystemResource(filePath));
    }

    @GetMapping("download/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) throws UnsupportedEncodingException {
        FileStore fileStore = fileStoreService.getById(id);
        // 文件路径
        Path filePath = Paths.get(fileStoreConfigure.getRootPath(), fileStore.getFilepath());
        // 转码后的文件名
        String fileName = URLEncoder.encode(fileStore.getFilename(), String.valueOf(StandardCharsets.UTF_8));
        // header 中 content-disposition
        String contentDisposition = "attachment; filename*=utf-8''" + fileName;

        return ResponseEntity.ok()
            // 设置 mime 为二进制流数据
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
            .body(new FileSystemResource(filePath));

    }

}
