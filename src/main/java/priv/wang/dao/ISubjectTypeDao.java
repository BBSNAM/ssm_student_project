package priv.wang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.wang.entity.SubjectType;
import priv.wang.entity.User;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:41
 * @comment: User表的持久层操作
 */
@Repository
public interface ISubjectTypeDao {

    /**
     * 查询全部信息
     * @return
     */
    List<SubjectType> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name, @Param("subId") Integer subId);

    /**
     * 查询总记录数
     * @return
     */
    int findCount(@Param("name") String name, @Param("subId") Integer subId);

    /**
     * 添加方法
     * @param subjectType
     * @return
     */
    int insertInfo(SubjectType subjectType);

    /**
     * 修改方法
     * @param subjectType
     * @return
     */
    int updateInfo(SubjectType subjectType);

    /**
     * 删除方法
     * @return
     */
    int deleteInfo(String id);

}
