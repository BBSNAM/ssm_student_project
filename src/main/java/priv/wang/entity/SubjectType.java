package priv.wang.entity;

import java.io.Serializable;

/**
 * @user: Mr.Wang
 * @date: 2019/9/5
 * @time: 19:10
 * @comment: SubjectType表的实体类
 */
public class SubjectType implements Serializable {

    private Integer id;
    private Integer subId;
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

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
