package org.sch.wrench.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import lombok.NonNull;
import org.sch.wrench.tree.entity.ObjInTree;

import java.util.List;

/**
 * <p>
 * 对象关系树 服务类
 * </p>
 */
public interface IObjInTreeService extends IService<ObjInTree> {
    /**
     * 保存两个实体的上下级关系，如果 child 在树中已存在，则会保存失败
     *
     * 如果 child 在树中不存在，则将子节点添加到树中，如果同时存在 parent，则会将子节点添加到 parent 下面
     *
     * 如果 child 在树中存在，且不存在 parent，则将 child 挪出为根节点
     *
     * 如果 child 在树中存在，且存在 parent，则将 child 挪到 parent 下面，但请注意不能把父节点挪到子节点下面
     *
     * @param child 下级的实体 ID
     * @param parent 上级的实体 ID，不存在可给 null 或空字符串(默认添加为根)
     * @param type 树类型
     * @return 保存结果，body 为 child 的 ObjInTree 对象
     */
    boolean save(@NonNull String child, String parent, @NonNull Enum<?> type);

    /**
     * 变更父级关系
     * @param child 下级的实体 ID
     * @param parent 上级的实体 ID
     * @param type 树类型
     */
    void moveParent(@NonNull String child, @NonNull String parent, @NonNull Enum<?> type);

    /**
     * 查询实体的所有级别的后代
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体的后代列表
     */
    List<String> children(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 查询实体某一级别的后代
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @param layer 要查询的级别
     * @return 指定级别的后代列表
     */
    List<String> children(@NonNull String oid, @NonNull Enum<?> type, int layer);

    /**
     * 查询实体的所有级别的后代ID - 包含自己
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体的后代列表
     */
    List<String> childrenSelf(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 查询实体的所有级别的后代 - 包含自己
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体的后代列表
     */
    List<ObjInTree> childrenSelfObj(@NonNull String oid, @NonNull Enum<?> type);



    /**
     * 查询实体的所有级别的后代数量
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体的后代数量
     */
    int childrenCount(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 查询实体某一级别的后代数量
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @param layer 要查询的级别
     * @return 指定级别的后代数量
     */
    int childrenCount(@NonNull String oid, @NonNull Enum<?> type, int layer);


    /**
     * 查询指定实体的所有祖先.
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体所有的祖先
     */
    List<String> parentIds(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 查询指定实体的所有祖先包含自己.
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 实体所有的祖先包含自己
     */
    List<String> parentIdsAndSelf(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 查询指定实体的某一级别的祖先.
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @param layer 要查询的级别
     * @return 指定级别的祖先
     */
    ObjInTree parent(@NonNull String oid, @NonNull Enum<?> type, int layer);

    /**
     * 根据 oid 查询 Tree 对象
     *
     * @param oid 实体的 ID
     * @param type 树类型
     * @return 查到的 Tree 对象
     */
    ObjInTree getByOid(@NonNull String oid, @NonNull Enum<?> type);

    /**
     * 判断 child 是否是 parent 的下级
     *
     * @param child 下级的实体 ID
     * @param parent 上级的实体 ID
     * @param type 树类型
     * @return 是返回 true，不是返回 false
     */
    boolean isChildren(@NonNull String child, @NonNull String parent, @NonNull Enum<?> type);

    /**
     * 将一个实体从树中移除，且会同时移除其所有子节点
     *
     * @param oid 被删除的实体的 ID
     * @param type 树类型
     */
    void removeByOid(@NonNull String oid, @NonNull Enum<?> type);

}
