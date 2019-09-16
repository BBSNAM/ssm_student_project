package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.SubjectSeries;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:38
 * @comment: SubjectSeries表的业务层接口
 */
public interface ISubjectSeriesService {

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
    boolean insertInfo(SubjectSeries subjectSeries);

    /**
     * 修改方法
     * @param subjectSeries
     * @return
     */
    boolean updateInfo(SubjectSeries subjectSeries);

    /**
     * 删除方法
     * @return
     */
    boolean deleteInfo(String id);


}
