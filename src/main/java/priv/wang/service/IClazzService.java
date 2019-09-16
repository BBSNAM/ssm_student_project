package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.Clazz;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:39
 * @comment: Clazz表的业务层接口
 */
public interface IClazzService {

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
    boolean insertInfo(Clazz clazz);

    /**
     * 更新一条信息
     * @param clazz
     * @return
     */
    boolean updateInfo(Clazz clazz);

    /**
     * 根据ID删除一条或多条数据
     * @param id
     * @return
     */
    boolean deleteInfo(@Param("delValues") String id);

    /**
     * 根据clazzId查询该班级的编号
     * @param clazzId
     * @return
     */
    String findClazzNoByClazzId(Integer clazzId);

}
