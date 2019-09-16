package priv.wang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.wang.entity.Subject;
import priv.wang.entity.SubjectSeries;

import java.util.List;

/**
 * @Subject: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:41
 * @comment: SubjectSeries表的持久层操作
 */
@Repository
public interface ISubjectSeriesDao {

    /**
     * 查询全部信息
     * @return
     */
    List<SubjectSeries> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name, @Param("typeId") Integer typeId);

    /**
     * 查询总记录数
     * @return
     */
    int findCount(@Param("name") String name, @Param("typeId") Integer typeId);

    /**
     * 添加方法
     * @param subjectSeries
     * @return
     */
    int insertInfo(SubjectSeries subjectSeries);

    /**
     * 修改方法
     * @param subjectSeries
     * @return
     */
    int updateInfo(SubjectSeries subjectSeries);

    /**
     * 删除方法
     * @return
     */
    int deleteInfo(String id);

}
