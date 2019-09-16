package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.SubjectType;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:38
 * @comment: null
 */
public interface ISubjectTypeService {

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
    boolean insertInfo(SubjectType subjectType);

    /**
     * 修改方法
     * @param subjectType
     * @return
     */
    boolean updateInfo(SubjectType subjectType);

    /**
     * 删除方法
     * @return
     */
    boolean deleteInfo(String id);

}
