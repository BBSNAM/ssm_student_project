package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.Subject;

import java.util.List;

/**
 * @Subject: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:38
 * @comment: null
 */
public interface ISubjectService {

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
    boolean insertInfo(Subject subject);

    /**
     * 修改方法
     * @param subject
     * @return
     */
    boolean updateInfo(Subject subject);

    /**
     * 删除方法
     * @return
     */
    boolean deleteInfo(String id);

}
