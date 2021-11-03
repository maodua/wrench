package io.github.maodua.wrench.tree;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import io.github.maodua.wrench.tree.service.impl.ObjInTreeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TreeApplication.class)
public class TreeApplicationTest {
    @Autowired
    private ObjInTreeServiceImpl objInTreeService;
    private TreeType type = TreeType.OTHER;
    @Test
    public void  saveTest(){
//         添加测试
//        100 -> 101 -> 1011
//            -> 102
//            -> 103 -> 1031
        Assert.isTrue(objInTreeService.save("100", "", type), "添加失败，淦！");

        Assert.isTrue(objInTreeService.save("101", "100", type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("102", "100", type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("103", "100", type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("1011", "101", type), "添加失败，淦！");
        Assert.isTrue(objInTreeService.save("1031", "103", type), "添加失败，淦！");

    }
    @Test
    void moveTest(){
//      测试修改
//        1031 -> 1011
//        1031 -> 103
        objInTreeService.moveParent("1031", "1011", type);
        objInTreeService.moveParent("1031", "103", type);
    }

    @Test
    void findTest(){

    }

    @Test
    void removeTest(){
        Assert.isTrue(objInTreeService.save("10311", "1031", type), "添加失败，淦！");
        objInTreeService.removeByOid("10311", type);
    }

}
