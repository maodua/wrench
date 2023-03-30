# 🔧 权限可以更简单
其实这个模块只是很简单很简单的一个权限一些管理，没有 spring security 全面，但是轻量级（一共没几行代码）完全满足一些小项目的需求，如果后续有什么需求再扩展吧

## 1. 安装
[最新版本](https://mvnrepository.com/artifact/io.github.maodua/wrench-auth-spring-boot-starter)

Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-auth-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-auth-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 2. 教程
以三个接口作为例子：

| 接口         | 路径 | 描述 |
|------------|----|----|
| 登录接口       |  `POST:/api/login`  |   签发token并返回给前端 |
| 获取当前用户信息接口 |  `GET:/api/user`  |  获取当前用户信息  |
| 删除xx数据接口   |  `DELETE:/api/xx`  |  添加xx数据,接口有`DeleteXXAuth`权限限制  |

先后将登陆接口对外开放，application.yaml中配置如下
```yaml
wrench:
  auth:
    # 对外开放的api
    # KEY: 请求方法, 如 GET、POST、PUT...
    # VAL: 实际的URL, 可使用 AntPathMatcher 方式匹配验证
    open-url:
      POST:
        - /api/login
```
### 登录接口
```java
@PostMapping("/api/login")
public String login(@RequestBody LoginVo loginVo) {
    // 1. 验证用户...

    // 2. 用户权限
    String[] auth = {"DeleteXXAuth","权限A", "权限B"};

    // 3. 获取token
    String token = TokenUtil.create(false, "用户id", auth);
    return token;
}

```
### 获取当前用户信息接口
```java
@GetMapping("/api/user")
public String getUser(){
    // 获取用户id
    String userId = ContextHolder.getUserId();
    return userId;
}
```
### 添加xx数据接口
```java
@Auth("DeleteXXAuth") // 拥有DeleteXXAuth权限的用户才能访问
@DeleteMapping("/api/xx")
public Boolean delete(){
    //删除逻辑....
    return true;
}
```

