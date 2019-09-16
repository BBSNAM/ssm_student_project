package priv.wang.page;

import org.springframework.stereotype.Component;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 22:02
 * @comment: 实现分页的工具类
 */
@Component
public class Page {

    private int page;//当前页面

    private int rows;//每页显示数量

    private int offset;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int row) {
        this.rows = row;
    }

    public int getOffset() {
        this.offset = (page -1)*rows;
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = (page -1)*rows;
    }


}
