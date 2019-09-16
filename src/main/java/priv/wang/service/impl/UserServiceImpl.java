package priv.wang.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.IUserDao;
import priv.wang.entity.User;
import priv.wang.service.IUserService;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: User表的业务层操作
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    /**
     * 登录验证
     * @return
     */
    @Override
    public User findLoginVerify(String username, String password) {
        return userDao.findLoginVerify(username, password);
    }

    /**
     * 查询全部信息
     * @return
     */
    @Override
    public List<User> findAll(@Param("start") Integer start, @Param("end") Integer end, @Param("username") String username) {
        return userDao.findAll(start, end, username);
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public int findCount(String username) {
        return userDao.findCount(username);
    }

    /**
     * 添加方法
     * @param user
     * @return
     */
    @Override
    public boolean insertInfo(User user) {
        return userDao.insertInfo(user) > 0;
    }

    /**
     * 修改方法
     * @param user
     * @return
     */
    @Override
    public boolean updateInfo(User user) {
        return userDao.updateInfo(user) > 0;
    }

    /**
     * 删除方法
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return userDao.deleteInfo(id) > 0;
    }

}
