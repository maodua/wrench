package io.github.maodua.wrench.tree.entity;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 对象关系树 <br/>
 * 参考 Closure Table 数据结构
 */
@Data
@Accessors(chain = true)
public class ObjInTree {


    private String id;

    /**
     * 父级或祖父级ID
     */
    private String ancestorId;

    /**
     * 实体ID
     */
    private String objId;

    /**
     * oid对于ancestor_id中的级别
     */
    private Integer lvl;
    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 树类型
     */
    private String type;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;
}
