package priv.wang.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import priv.wang.entity.User;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:41
 * @comment: User表的持久层操作
 */
@Repository
public interface IUserDao {

    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    User findLoginVerify(@Param("uName") String username, @Param("pwd") String password);

    /**
     * 查询全部信息
     * @return
     */
    List<User> findAll(@Param("start")Integer start, @Param("end")Integer end,@Param("username")String username);

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
    int insertInfo(User user);

    /**
     * 修改方法
     * @param user
     * @return
     */
    int updateInfo(User user);

    /**
     * 删除方法
     * @return
     */
    int deleteInfo(String id);

}
