# ๐ง ๅธธ็จๅ

## ๅฎ่ฃ
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-common-spring-boot-starter:ๆๆฐ็ๆฌๅท"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-common-spring-boot-starter</artifactId>
    <version>ๆๆฐ็ๆฌๅท</version>
</dependency>
```

## ็ฎๅฝ็ปๆ
```
โโโ generated
โโโ java
โ   โโโ io.github.maodua.wrench
โ       โโโ common
โ       โโโ advice
โ       โ   โโโ InternExceptionHandler.java ๅฌๅฑๅผๅธธๅค็ๅจ
โ       โโโ bean
โ       โ   โโโ Id.java ๅฌๅฑvo
โ       โโโ exception
โ       โ   โโโ MessageException.java ๅ็ซฏไผ ้ไฟกๆฏ็ๅผๅธธ
โ       โโโ util
โ       โ   โโโ Assert.java ๆญ่จๅฎ็จ็จๅบ็ฑป
โ       โ   โโโ Servlets.java Servlet ๅทฅๅท็ฑป
โ       โ   โโโ Springs.java Spring ๅทฅๅท็ฑป
โ       โ   โโโ Strings.java String ๅทฅๅท็ฑป
โ       โโโ vo
โ           โโโ result
โ               โโโ Result.java ็ปไธ่ฟๅ็ปๆ
โ               โโโ ResultCode.java ็ป่ฎก่ฟๅ็ปๆ็ถๆ็ 
โโโ resources
    โโโ META-INF
        โโโ spring.factories Spring่ชๅจๆณจๅฅ
```
