package priv.wang.entity;

import java.io.Serializable;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:42
 * @comment: 对应数据库grade表
 */
public class Grade implements Serializable {

    private Integer id;
    private String name;
    private Integer series;
    private String year;
    private String remark;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
