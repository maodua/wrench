# 🔧 分页可以更简单

## 安装
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-pager-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-pager-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 示例代码
```java
@RestController
@RequestMapping("api/test")
public class TestController {
    @Autowired
    private ITestService testService;

    @Pager
    @GetMapping("list")
    public Result<?> list(){
        var list = testService.list();
        return Result.success(list);
    }
}
```
ok现在你的接口就已经支持分页了

返回值
```json
GET http://ip:port/api/testlist?page=2&pageSize=12
{
    "success": true,
    "code": 0,
    "message": "",
    "data": {
        "records": [
            {
                "id": "1452532627610537985",
                "objId": "100",
                "addTime": "2021-10-25T15:08:59"
            }
        ],
        "page": 2,
        "totalRow": 13,
        "totalPage": 2
    }
}
```
## 灵活配置
### 1. 自定义请求参数
```yaml
# application.yml中添加以下配置
wrench:
    pager:
    	# 当前页参数 KEY
        page: "pageCustomize"
        # 页大小参数 KEY
        pageSize: "pageSizeCustomize"
```
访问URL ： GET http://ip:port/xxx/xxx?pageCustomize=2&pageSizeCustomize=12

### 2. 自定义返回对象

```java
// 自定义结果处理器
public class CustomizeResultHandler implements IResultHandler<WrenchResult<Object>> {
    @Override
    public Object getData(WrenchResult<Object> result) throws Exception {
        return result.getData();
    }
    @Override
    public void setData(WrenchResult<Object> result, Object data) {
        result.setData(data);
    }
}


@Configuration
public class PagerConfig {
    // 注册到 spring 容器中
    @Bean
    public IResultHandler resultHandler(){
        return new CustomizeResultHandler();
    }
}

```
现在你的接口方法就可以使用自定义的返回类了
```java
@Pager
@GetMapping("list")
public WrenchResult<?> list(){
    var list = testService.list();
    return WrenchResult.success(list);
}
```

### 3. 自定义返回数据
```java
// 自定义分页数据处理器
public class CustomizePageDataHandler extends Page<Object> implements IPageDataHandler {
    @Override
    public void setWrenchPage(long page) {this.setCurrent(page);}
    @Override
    public void setWrenchPageSize(long pageSize) {this.setSize(pageSize);}
    @Override
    public void setWrenchTotalRow(long totalRow) {this.setTotal(totalRow);}
    @Override
    public void setWrenchTotalPage(long totalPage) {this.setPages(totalPage);}
    @Override
    public void setWrenchData(Collection<Object> records) {this.setRecords(Collections.singletonList(records));}
}

@Configuration
public class PagerConfig {
    // 注册到 spring 容器中
    @Bean
    public IPageDataHandler customizePageDataHandler(){
        return new CustomizePageDataHandler();
    }
}
```

