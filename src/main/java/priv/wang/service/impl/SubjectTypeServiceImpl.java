package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.ISubjectTypeDao;
import priv.wang.entity.SubjectType;
import priv.wang.service.ISubjectTypeService;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: null
 */
@Service
public class SubjectTypeServiceImpl implements ISubjectTypeService {

    @Autowired
    private ISubjectTypeDao subjectTypeDao;

    /**
     * 查询全部信息
     * @param start
     * @param end
     * @param name
     * @return
     */
    @Override
    public List<SubjectType> findAll(Integer start, Integer end, String name, Integer subId) {
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return subjectTypeDao.findAll(start, end, name, subId);
    }

    /**
     * 查询总记录数
     * @param name
     * @return
     */
    @Override
    public int findCount(String name, Integer subId) {
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return subjectTypeDao.findCount(name, subId);
    }

    /**
     * 添加方法
     * @param subjectType
     * @return
     */
    @Override
    public boolean insertInfo(SubjectType subjectType) {
        if(subjectType.getRemark() == null || subjectType.getRemark() == ""){
            subjectType.setRemark("暂无备注");
        }
        return subjectTypeDao.insertInfo(subjectType) > 0;
    }

    /**
     * 修改方法
     * @param subjectType
     * @return
     */
    @Override
    public boolean updateInfo(SubjectType subjectType) {
        return subjectTypeDao.updateInfo(subjectType) > 0;
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return subjectTypeDao.deleteInfo(id) > 0;
    }
}
