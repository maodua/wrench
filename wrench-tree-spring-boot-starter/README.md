# 🔧 树存储可以更简单

## 1. 如何在关系型数据库中存储树形结构？
* 邻接表： 每一行添加一个 parent_id
* 左右值编码： 存储每个节点左右值，适合`增删改`
* 闭包表：空间换时间的方式，适合`查询`，此工具选择此方案

## 2. 安装
Gradle:
```groovy
dependencies {
    compile "io.github.maodua:wrench-tree-spring-boot-starter:最新版本号"
}
```
Maven:
```xml
<dependency>
    <groupId>io.github.maodua</groupId>
    <artifactId>wrench-tree-spring-boot-starter</artifactId>
    <version>最新版本号</version>
</dependency>
```

## 3. 如何在代码中使用此工具
```java
// 注入spring bean,后续调用 bean 中的接口
@Autowired
private IObjInTreeService objInTreeService;
```
### 3.1 增
```java
//保存两个实体的上下级关系，如果 child 在树中已存在，则会保存失败
objInTreeService.save()
```
### 3.2 删
```java
// 将一个实体从树中移除，且会同时移除其所有子节点
objInTreeService.removeByOid()
```

### 3.3 改
```java
// 变更父级关系
objInTreeService.moveParent()
```

### 3.4 查
```java
// ---查询后代---

    // 查询实体的所有级别的后代
        children(实体的ID, 树类型)
    // 查询实体某一级别的后代
        children(实体的ID, 树类型, 要查询的级别)
    // 查询实体的所有级别的后代包含自己
       childrenSelf(实体的ID, 树类型)
    // 查询实体的所有级别的后代包含自己（原始对象）
        childrenSelfObj(实体的ID, 树类型)
    // 查询实体的所有级别的后代数量
        childrenCount(实体的ID, 树类型)
    // 查询实体某一级别的后代数量
        childrenCount(实体的ID, 树类型, 要查询的级别)

 // ---查询祖先---
    // 查询指定实体的所有祖先
        parentIds()
    // 查询指定实体的所有祖先包含自己
        parentIdsAndSelf()
    // 查询指定实体的某一级别的祖先
        parent()

// 根据 oid 查询 Tree 对象
    getByOid()

// 判断 child 是否是 parent 的下级
    isChildren()

```
