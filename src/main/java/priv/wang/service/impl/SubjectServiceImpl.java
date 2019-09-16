package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.ISubjectDao;
import priv.wang.entity.Subject;
import priv.wang.service.ISubjectService;

import java.util.List;

/**
 * @Subject: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: null
 */
@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private ISubjectDao subjectDao;

    /**
     * 查询全部信息
     * @param start
     * @param end
     * @param name
     * @return
     */
    @Override
    public List<Subject> findAll(Integer start, Integer end, String name) {
        //判断姓名是否为空从而拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return subjectDao.findAll(start, end, name);
    }

    /**
     * 查询总记录数
     * @param name
     * @return
     */
    @Override
    public int findCount(String name) {
        //判断姓名是否为空从而拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return subjectDao.findCount(name);
    }

    /**
     * 添加方法
     * @param subject
     * @return
     */
    @Override
    public boolean insertInfo(Subject subject) {
        if(subject.getRemark() == null || subject.getRemark() == ""){
            subject.setRemark("暂无备注");
        }
        return subjectDao.insertInfo(subject) > 0;
    }

    /**
     * 修改方法
     * @param subject
     * @return
     */
    @Override
    public boolean updateInfo(Subject subject) {
        return subjectDao.updateInfo(subject) > 0;
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return subjectDao.deleteInfo(id) > 0;
    }
}
