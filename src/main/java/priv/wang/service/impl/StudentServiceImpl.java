package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.IStudentDao;
import priv.wang.entity.Student;
import priv.wang.entity.Subject;
import priv.wang.entity.User;
import priv.wang.service.IStudentService;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: null
 */
@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private IStudentDao studentDao;
    
    /**
     * 登录验证
     * @param username
     * @param password
     * @return
     */
    @Override
    public int findLoginVerify(String username, String password) {
        return 0;
    }

    /**
     * 查询全部方法
     * @param start   分页开始的位置
     * @param end     分页结束的位置
     * @param name    根据传入参数判断是否模糊查询
     * @param clazzId
     * @return
     */
    @Override
    public List<Student> findAll(Integer start, Integer end, String name, Integer clazzId) {
        //name不等于空时拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return studentDao.findAll(start, end, name, clazzId);
    }

    /**
     * 查询总记录数
     * @param name    根据传入参数判断是否模糊查询
     * @param clazzId
     * @return
     */
    @Override
    public int findCount(String name, Integer clazzId) {
        //name不等于空时拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return studentDao.findCount(name, clazzId);
    }

    /**
     * 插入一条信息
     * @param student
     * @return
     */
    @Override
    public boolean insertInfo(Student student) {
        if(student.getRemark() == null || student.getRemark() == ""){
            student.setRemark("暂无备注");
        }
        return studentDao.insertInfo(student) > 0;
    }

    /**
     * 更新一条信息
     * @param student
     * @return
     */
    @Override
    public boolean updateInfo(Student student) {
        if(student.getRemark() == null || student.getRemark() == ""){
            student.setRemark("暂无备注");
        }
        return studentDao.updateInfo(student) > 0;
    }

    /**
     * 根据ID删除一条或多条数据
     * @param id
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return studentDao.deleteInfo(id) > 0;
    }
}
