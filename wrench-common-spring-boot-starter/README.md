# 🔧 常用包

## 安装
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-common-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-common-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 目录结构
```
├── generated
├── java
│   └── io.github.maodua.wrench
│       └── common
│       ├── advice
│       │   └── InternExceptionHandler.java 公共异常处理器
│       ├── bean
│       │   └── Id.java 公共vo
│       ├── exception
│       │   └── MessageException.java 前端传递信息的异常
│       ├── util
│       │   ├── Assert.java 断言实用程序类
│       │   ├── Servlets.java Servlet 工具类
│       │   ├── Springs.java Spring 工具类
│       │   └── Strings.java String 工具类
│       └── vo
│           └── result
│               ├── Result.java 统一返回结果
│               └── ResultCode.java 统计返回结果状态码
└── resources
    └── META-INF
        └── spring.factories Spring自动注入
```
