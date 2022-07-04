package io.github.maodua.wrench.tree;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.maodua.wrench.tree.entity.ObjInTree;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.github.maodua.wrench.tree.service.impl.ObjInTreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TreeApplication.class)
public class TreeApplicationTest {
    @Autowired
    private ObjInTreeServiceImpl objInTreeService;
    private final ObjectMapper mapper = new ObjectMapper();

    private final TreeType type = TreeType.OTHER;
    private final String rootId = "100";

    @Test
    public void  saveTest() throws JsonProcessingException {
//         添加测试
//        100 -> 101 -> 1011
//            -> 102
//            -> 103 -> 1031
        Assert.isTrue(objInTreeService.save(rootId, null, type), "添加失败，淦！");

        Assert.isTrue(objInTreeService.save("101", rootId, type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("102", rootId, type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("103", rootId, type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("1011", "101", type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("1031", "103", type), "添加失败，淦！");
        formatPrint();
    }
    @Test
    void moveTest() throws JsonProcessingException {
//      测试修改
//        1031 -> 1011
//        1031 -> 103
        objInTreeService.moveParent("1031", "1011", type);
        formatPrint();
        objInTreeService.moveParent("1031", "103", type);
        formatPrint();
    }


    @Test
    void removeTest() throws JsonProcessingException {
        Assert.isTrue(objInTreeService.save("10311", "1031", type), "添加失败，淦！");
        objInTreeService.removeByOid("10311", type);
        formatPrint();
    }


    @Test
    void formatPrint() throws JsonProcessingException {
        List<ObjInTree> objInTrees = objInTreeService.childrenSelfObj(rootId, type);
        String s = Tree2Json(rootId, objInTrees);
        System.out.println(s);
    }


    @Data
    @Accessors(chain = true)
    public class TestTree{

        private String id;
        /**
         * 父级ID
         */
        private String parentId;
        /**
         * 层级
         */
        private Integer lvl;

        private List<TestTree> children;

    }
    public String Tree2Json(String rootId, List<ObjInTree> trees) throws JsonProcessingException {
        List<TestTree> merges = trees.stream().map(t -> new TestTree()
            .setId(t.getObjId())
            .setParentId(t.getParentId())
            .setLvl(t.getLvl())
            .setChildren(new ArrayList<>())
        ).collect(Collectors.toList());

        // 获取根节点
        TestTree root = merges.stream().filter(f -> f.getId().equals(rootId)).findFirst().get();

        // 数据集中删除根节点
        merges.remove(root);

        //开始添加子级
        for (TestTree merge : merges) {
            // 添加到根上
            if (root.getId().equals(merge.getParentId()) && !root.getChildren().contains(merge)) {
                root.getChildren().add(merge);
            }
            //开始添加子级
            merges.forEach(it -> {
                if (merge.getId().equals(it.getParentId()) && !merge.getChildren().contains(it)) {
                    //如果是空就添加子级
                    merge.getChildren().add(it);
                }
            });
        }
        ObjectWriter objectWriter = mapper.writerWithDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(root);
    }

}
