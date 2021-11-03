# 🔧 分页可以更简单

### 添加依赖

---
gradle
```groovy
dependencies {
    compile "org.xxx:wrench-pager-spring-boot-starter:版本号"
}
```
maven
```xml
<dependency>
    <groupId>org.xxx</groupId>
    <artifactId>wrench-pager-spring-boot-starter</artifactId>
    <version>版本号</version>
</dependency>

```
### 示例代码

---
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
{
    "success": true,
    "code": 0,
    "message": "",
    "data": {
    "listData": [
        {
            "id": "1452532627610537985",
            "objId": "100",
            "addTime": "2021-10-25T15:08:59"
        },
        {
            "id": "1452532628701057025",
            "objId": "101",
            "addTime": "2021-10-25T15:09:00"
        }
    ],
    "page": 1,
    "totalRow": 13,
    "totalPage": 2
    }
}
```

### 自定义返回值

---
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
public class PagerTestConfig {
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
