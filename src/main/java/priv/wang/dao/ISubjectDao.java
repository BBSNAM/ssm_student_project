package priv.wang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.wang.entity.Subject;

import java.util.List;

/**
 * @Subject: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:41
 * @comment: Subject表的持久层操作
 */
@Repository
public interface ISubjectDao {

    /**
     * 查询全部信息
     * @return
     */
    List<Subject> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name);

    /**
     * 查询总记录数
     * @return
     */
    int findCount(String name);

    /**
     * 添加方法
     * @param subject
     * @return
     */
    int insertInfo(Subject subject);

    /**
     * 修改方法
     * @param subject
     * @return
     */
    int updateInfo(Subject subject);

    /**
     * 删除方法
     * @return
     */
    int deleteInfo(String id);

}
