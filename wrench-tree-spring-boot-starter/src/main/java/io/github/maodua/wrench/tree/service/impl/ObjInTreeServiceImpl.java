package io.github.maodua.wrench.tree.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.maodua.wrench.tree.entity.Constant;
import io.github.maodua.wrench.tree.entity.ObjInTree;
import io.github.maodua.wrench.tree.exception.TreeException;
import io.github.maodua.wrench.tree.mapper.ObjInTreeMapper;
import io.github.maodua.wrench.tree.service.IObjInTreeService;
import lombok.NonNull;
import io.github.maodua.wrench.common.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 对象关系树 服务实现类
 * </p>
 */
@Service
@Transactional
public class ObjInTreeServiceImpl extends ServiceImpl<ObjInTreeMapper, ObjInTree> implements IObjInTreeService {


    @Override
    public boolean save(@NonNull String child, String parent, @NonNull Enum<?> type) {
        // 父级为空默认添加此节点为根节点
        if (Strings.isBlank(parent)){
            var typeCount = this.lambdaQuery().eq(ObjInTree::getType, type).count();
            if (typeCount > 0){
                throw new TreeException(MessageFormat.format("已有 {0} 类型节点,不能当做根节点", type));
            }

            var objInTree = new ObjInTree()
                .setAncestorId(child)
                .setObjId(child)
                .setLvl(0)
                .setParentId(Constant.defaultAncestorId)
                .setType(type.toString())
                .setAddTime(LocalDateTime.now());
            return this.save(objInTree);
        }

        // 检查子集是否存在
        var childCount = this.lambdaQuery().eq(ObjInTree::getObjId,child).eq(ObjInTree::getType,type).count();
        if (childCount > 0){
            throw new TreeException("子级已经存在,不能保存");
        }
        // 检查父级是否存在
        var parentCount = this.lambdaQuery().eq(ObjInTree::getAncestorId, parent).eq(ObjInTree::getObjId, parent).eq(ObjInTree::getType, type).count();
        if (parentCount <= 0){
            throw new TreeException("父级不存在,不能保存");
        }

        // 检查当前关系是否存在
        var count = this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, parent)
                .eq(ObjInTree::getObjId, child)
                .eq(ObjInTree::getType, type)
                .count();
        if (count > 0){
            throw new TreeException("关系已经存在,不能保存");
        }

        // 祖先添加关系
        var ancestor = this.lambdaQuery().eq(ObjInTree::getObjId,parent).eq(ObjInTree::getType, type).list();
        var addList = new ArrayList<ObjInTree>();
        ancestor.forEach(a -> {
            var ojbInTree = new ObjInTree()
                    .setAncestorId(a.getAncestorId())
                    .setObjId(child)
                    .setLvl(a.getLvl() + 1)
                    .setParentId(parent)
                    .setType(a.getType())
                    .setAddTime(LocalDateTime.now());
            addList.add(ojbInTree);
        });
        this.saveBatch(addList);

        // 添加本级关系
        var now = new ObjInTree()
                .setAncestorId(child)
                .setObjId(child)
                .setLvl(0)
                .setParentId(parent)
                .setType(type.toString())
                .setAddTime(LocalDateTime.now());
        return this.save(now);
    }

    /**
     * 子级存在的情况调树结构
     */
    @Override
    public void moveParent(@NonNull String child, @NonNull String parent, @NonNull Enum<?> type) {
    // 1. 数据校验

        // 检查子集是否存在
        var childCount = this.lambdaQuery().eq(ObjInTree::getObjId,child).eq(ObjInTree::getType,type).count();
        if (childCount <= 0){
            throw new TreeException("子级不存在,移动失败。");
        }
        // 检查是否有父级
        var parentCount = this.lambdaQuery().eq(ObjInTree::getAncestorId, parent).eq(ObjInTree::getObjId, parent).eq(ObjInTree::getType, type).count();
        if (parentCount <= 0){
            throw new TreeException("父级不存在,移动失败。");
        }

        // 检查当前关系是否存在
        var count = this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, parent)
                .eq(ObjInTree::getObjId, child)
                .eq(ObjInTree::getType, type)
                .eq(ObjInTree::getLvl,1)
                .count();
        if (count > 0){
            throw new TreeException("子父关系已经存在,移动失败");
        }

        // 父级是否是自己的子级
        var isChildren = this.isChildren(parent, child, type);
        if (isChildren){
            throw new TreeException("父级不可以移动到子级，移动失败");
        }

    // 2. 修改当前节点 parentId && 不更改层级关系为方便后续层级修改
        this.lambdaUpdate().set(ObjInTree::getParentId, parent)
                .eq(ObjInTree::getAncestorId, child)
                .eq(ObjInTree::getObjId, child)
                .eq(ObjInTree::getType, type)
                .eq(ObjInTree::getLvl, 0)
                .update();

       // 当前节点的所有下限关系(包括自己)
        var childRelation = this.lambdaQuery().eq(ObjInTree::getAncestorId, child).eq(ObjInTree::getType, type).list();
    // 2. 删除已有上限关系

        // 旧的上级关系(不包括自己)
        var oldAncestorRelation = this.lambdaQuery().eq(ObjInTree::getObjId,child).eq(ObjInTree::getType,type).gt(ObjInTree::getLvl,0).list();
        if (oldAncestorRelation.size() > 0){

            // 旧的上级id
            var oldAncestorId = oldAncestorRelation.stream().map(ObjInTree::getAncestorId).collect(Collectors.toList());

            // 当前节点的所有关系子级
            var relationChild = childRelation.stream().map(ObjInTree::getObjId).collect(Collectors.toList());

            // 删除所有  祖先ID = 旧祖先 && 实体ID = 当前节点下节点(包括自己)
            var removeWrapper = Wrappers.<ObjInTree>lambdaQuery()
                    .in(ObjInTree::getAncestorId, oldAncestorId)
                    .in(ObjInTree::getObjId, relationChild);
            this.remove(removeWrapper);
        }


    // 3.重新添加上限关系

        // 父级的祖先关系
        var ancestor = this.lambdaQuery().eq(ObjInTree::getObjId,parent).list();
        var addList = new ArrayList<ObjInTree>();
        ancestor.forEach(a ->
            childRelation.forEach(c -> {

                var ojbInTree = new ObjInTree()
                        .setAncestorId(a.getAncestorId())
                        .setObjId(c.getObjId())
                        .setLvl(a.getLvl() + c.getLvl() + 1)
                        .setParentId(c.getParentId())
                        .setType(a.getType())
                        .setAddTime(LocalDateTime.now());
                addList.add(ojbInTree);

            })
        );
        this.saveBatch(addList);
    }

    @Override
    public List<String> children(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, oid)
                .eq(ObjInTree::getType, type)
                .ne(ObjInTree::getLvl,0)
                .list().stream().map(ObjInTree::getObjId).collect(Collectors.toList());
    }

    @Override
    public List<String> children(@NonNull String oid, @NonNull Enum<?> type, int layer) {
        return this.lambdaQuery()
            .eq(ObjInTree::getAncestorId, oid)
            .eq(ObjInTree::getType, type)
            .eq(ObjInTree::getLvl,layer)
            .list().stream().map(ObjInTree::getObjId).collect(Collectors.toList());
    }

    @Override
    public List<String> childrenSelf(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, oid)
                .eq(ObjInTree::getType, type)
                .list().stream().map(ObjInTree::getObjId).collect(Collectors.toList());
    }

    @Override
    public List<ObjInTree> childrenSelfObj(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, oid)
                .eq(ObjInTree::getType, type)
                .list();
    }

    @Override
    public int childrenCount(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, oid)
                .eq(ObjInTree::getType, type)
                .ne(ObjInTree::getLvl,0)
                .count();
    }

    @Override
    public int childrenCount(@NonNull String oid, @NonNull Enum<?> type, int layer) {
        return this.lambdaQuery()
                .eq(ObjInTree::getAncestorId, oid)
                .eq(ObjInTree::getType, type)
                .eq(ObjInTree::getLvl,layer)
                .count();
    }

    @Override
    public List<String> parentIds(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getObjId,oid)
                .eq(ObjInTree::getType,type)
                .ne(ObjInTree::getLvl, 0)
                .list().stream().map(ObjInTree::getAncestorId).collect(Collectors.toList());
    }

    @Override
    public List<String> parentIdsAndSelf(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getObjId,oid)
                .eq(ObjInTree::getType,type)
                .list().stream().map(ObjInTree::getAncestorId).collect(Collectors.toList());
    }

    @Override
    public ObjInTree parent(@NonNull String oid, @NonNull Enum<?> type, int layer) {
        return this.lambdaQuery()
                .eq(ObjInTree::getObjId,oid)
                .eq(ObjInTree::getType,type)
                .eq(ObjInTree::getLvl,layer).one();
    }

    @Override
    public ObjInTree getByOid(@NonNull String oid, @NonNull Enum<?> type) {
        return this.lambdaQuery()
                .eq(ObjInTree::getObjId,oid)
                .eq(ObjInTree::getType,type)
                .ne(ObjInTree::getLvl,0).one();
    }

    @Override
    public boolean isChildren(@NonNull String child, @NonNull String parent, @NonNull Enum<?> type) {
        var count = this.lambdaQuery()
                .eq(ObjInTree::getObjId,child)
                .eq(ObjInTree::getAncestorId,parent)
                .eq(ObjInTree::getType,type)
                .ne(ObjInTree::getLvl,0)
                .count();
        return count > 0;
    }

    @Override
    public void removeByOid(@NonNull String oid, @NonNull Enum<?> type) {

        // 获取所有的下级(包括本身)
        var childrenIds = this.childrenSelf(oid, type);

        if (childrenIds.size() > 0){
            // 删除祖先 和 本身(及其下级)的关系
            var parentWrapper = Wrappers.<ObjInTree>lambdaQuery().in(ObjInTree::getObjId, childrenIds).gt(ObjInTree::getLvl, 0);
            this.remove(parentWrapper);

            // 删除本身的树
            var currentWrapper = Wrappers.<ObjInTree>lambdaQuery().in(ObjInTree::getAncestorId, childrenIds);
            this.remove(currentWrapper);

        }

    }
}
