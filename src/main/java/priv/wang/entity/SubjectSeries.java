package priv.wang.entity;

import java.io.Serializable;

/**
 * @user: Mr.Wang
 * @date: 2019/9/5
 * @time: 19:11
 * @comment: SubjectSeries表的实体类
 */
public class SubjectSeries implements Serializable {

    private Integer id;
    private Integer typeId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
