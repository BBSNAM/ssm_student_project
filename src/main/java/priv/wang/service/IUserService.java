package priv.wang.service;

import org.apache.ibatis.annotations.Param;
import priv.wang.entity.User;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:37
 * @comment: null
 */
public interface IUserService {

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    User findLoginVerify(@Param("uName")String username, @Param("pwd")String password);

    /**
     * 查询全部信息
     * @return
     */
    List<User> findAll(@Param("start")Integer start, @Param("end")Integer end, @Param("username")String username);

    /**
     * 查询总记录数
     * @return
     */
    int findCount(String username);

    /**
     * 添加方法
     * @param user
     * @return
     */
    boolean insertInfo(User user);

    /**
     * 修改方法
     * @param user
     * @return
     */
    boolean updateInfo(User user);

    /**
     * 删除方法
     * @return
     */
    boolean deleteInfo(String id);

}
