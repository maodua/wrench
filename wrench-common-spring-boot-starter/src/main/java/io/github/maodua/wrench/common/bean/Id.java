package io.github.maodua.wrench.common.bean;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * 公共 ID VO
 */
public class Id {
    /**
     * ID
     */
    @NotBlank(message = "id 不能为空")
    @Getter
    @Setter
    private String id;
}
