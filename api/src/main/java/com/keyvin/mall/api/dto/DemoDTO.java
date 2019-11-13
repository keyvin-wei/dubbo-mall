package com.keyvin.mall.api.dto;

import java.io.Serializable;

/**
 * @author weiwh
 * @date 2019/10/25 20:18
 */
public class DemoDTO implements Serializable {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
