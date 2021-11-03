package com.github.maodua.wrench.common.util;

import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void isBlank(){
        var s = "  ";
        var s2 = "";
        var s3 = """
            """;
        System.out.println(s.isEmpty());
        System.out.println(s2.isEmpty());
        System.out.println(s3.isEmpty());
//        System.out.println(Strings.isBlank(s));

    }
}
