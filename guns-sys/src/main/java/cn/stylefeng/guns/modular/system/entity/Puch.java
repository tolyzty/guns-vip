package cn.stylefeng.guns.modular.system.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: zhangty
 * @Date: 2019/9/4 16:36
 * @Description:
 * @Version:1.0.0
 */
@Data
public class Puch implements Serializable {

    private String name;

    private String jname;

    private String contains;

    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJname() {
        return jname;
    }

    public void setJname(String jname) {
        this.jname = jname;
    }
}
