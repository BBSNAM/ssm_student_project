package priv.wang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.wang.entity.Clazz;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:51
 * @comment: Clazz表的持久层操作
 */
@Repository
public interface IClazzDao {

    /**
     * 查询全部方法
     * @param start 分页开始的位置
     * @param end   分页结束的位置
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    List<Clazz> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name, @Param("gradeId") Integer grade);

    /**
     * 查询总记录数
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    int findCount(@Param("name") String name, @Param("gradeId") Integer grade);

    /**
     * 插入一条信息
     * @return
     */
    int insertInfo(Clazz grade);

    /**
     * 更新一条信息
     * @param grade
     * @return
     */
    int updateInfo(Clazz grade);

    /**
     * 根据ID删除一条或多条数据
     * @param id
     * @return
     */
    int deleteInfo(@Param("delValues") String id);

    /**
     * 根据clazzId查询该班级的编号
     * @param clazzId
     * @return
     */
    String findClazzNoByClazzId(Integer clazzId);

}
