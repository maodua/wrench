package io.github.maodua.wrench.common.util;

import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void isBlank(){
        String s = "  ";
        String s2 = "";
        System.out.println(s.isEmpty());
        System.out.println(s2.isEmpty());

//        System.out.println(Strings.isBlank(s));

    }
}
