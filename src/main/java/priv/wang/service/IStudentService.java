package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.Student;
import priv.wang.entity.Subject;
import priv.wang.entity.User;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:38
 * @comment: Subject表的业务层接口
 */
public interface IStudentService {

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    int findLoginVerify(@Param("uName") String username, @Param("pwd") String password);

    /**
     * 查询全部方法
     * @param start 分页开始的位置
     * @param end   分页结束的位置
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    List<Student> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("name") String name, @Param("clazzId") Integer clazzId);

    /**
     * 查询总记录数
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    int findCount(@Param("name") String name, @Param("clazzId") Integer clazzId);

    /**
     * 插入一条信息
     * @return
     */
    boolean insertInfo(Student student);

    /**
     * 更新一条信息
     * @param student
     * @return
     */
    boolean updateInfo(Student student);

    /**
     * 根据ID删除一条或多条数据
     * @param id
     * @return
     */
    boolean deleteInfo(@Param("delValues") String id);

}
