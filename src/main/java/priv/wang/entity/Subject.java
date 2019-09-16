package priv.wang.entity;

import java.io.Serializable;

/**
 * @user: Mr.Wang
 * @date: 2019/9/5
 * @time: 19:10
 * @comment: Subject表的实体类
 */
public class Subject implements Serializable {

    private Integer id;
    private String name;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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
