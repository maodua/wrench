package com.github.maodua.wrench.starter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("test")
public class TestTable {
    private String id;
    private String objId;
    private LocalDateTime addTime;
}
