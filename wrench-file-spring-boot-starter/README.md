# 🔧 文件存储可以更简单

## 安装
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-file-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-file-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 配置
application.yaml
```yaml
wrench:
  file:
    # 上传文件的根目录,默认当前目录
    root-path: /data/file-store
    # 是否替换URL前缀,默认false
    url-replace: false
    # 第三方网站的指定前缀,默认空字符串
    url-prefix: http://localhost:8080/
```

## 上传
项目中提供了简单的上传模板，实例代码如下
```java

/**
 * 本地文件存储 <br/>
 * <p>项目中提供了简单常用的本地文件上传模板，可直接继承 BaseLocalFileStoreApi 类使用</p>
 */
@RestController
@RequestMapping("local")
public class LocalFileStoreController extends BaseLocalFileStoreApi{}
/**
 * 第三方文件上传 <br/>
 * <p>项目中提供了简单常用的三方文件上传模板，可直接继承 BaseCloudFileStoreApi 类使用</p>
 */
@RestController
@RequestMapping("cloud")
public class CloudFileStoreController extends BaseCloudFileStoreApi{}
```

## 自定义上传
如果需要自定义上传逻辑,比如添加扩展数据等等...可以从新实现上传方法
```java
@RestController
@RequestMapping("local")
public class LocalFileStoreController extends BaseLocalFileStoreApi{
    /**
     * 自定义的下载方法 <br/>
     * <p>注意：如果需要替换什么方法就重新实现相同的方法名和参数</p>
     */
    @GetMapping("download/{id}")
    public T download(@PathVariable String id) {
        // 自定义代码...
    }
}
```
