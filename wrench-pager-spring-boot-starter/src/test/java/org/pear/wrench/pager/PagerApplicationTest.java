package org.pear.wrench.pager;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = PagerApplication.class)
public class PagerApplicationTest {
    @Test
    public void api_user_list(){
        MockMvcRequestBuilders.get("/api/user/list");
    }
}
