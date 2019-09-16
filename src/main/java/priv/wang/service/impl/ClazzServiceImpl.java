package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.IClazzDao;
import priv.wang.entity.Clazz;
import priv.wang.service.IClazzService;
import priv.wang.utils.AutoCreateDataUtil;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:39
 * @comment: Clazz表的业务层实现类
 */
@Service
public class ClazzServiceImpl implements IClazzService {

    @Autowired
    private IClazzDao dao;

    /**
     * 查询全部方法
     * @param start 分页开始的位置
     * @param end   分页结束的位置
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    @Override
    public List<Clazz> findAll(Integer start, Integer end, String name, Integer series) {
        //name不等于空时拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return dao.findAll(start, end, name, series);
    }

    /**
     * 查询总记录数
     * @param name 根据传入参数判断是否模糊查询
     * @return
     */
    @Override
    public int findCount(String name, Integer series) {
        //name不等于空时拼接条件
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return dao.findCount(name, series);
    }

    /**
     * 插入一条信息
     * @param clazz
     * @return
     */
    @Override
    public boolean insertInfo(Clazz clazz) {
        if(clazz.getRemark() == null || clazz.getRemark() == ""){
            clazz.setRemark("暂无备注");
        }
        return dao.insertInfo(clazz) > 0;
    }

    /**
     * 更新一条信息
     * @param clazz
     * @return
     */
    @Override
    public boolean updateInfo(Clazz clazz) {
        if(clazz.getRemark() == null || clazz.getRemark() == ""){
            clazz.setRemark("暂无备注");
        }
        return dao.updateInfo(clazz) > 0;
    }

    /**
     * 根据ID删除一条或多条数据
     * @param id
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return dao.deleteInfo(id) > 0;
    }

    /**
     * 根据clazzId查询该班级的编号
     * @param clazzId
     * @return
     */
    @Override
    public String findClazzNoByClazzId(Integer clazzId) {
        return dao.findClazzNoByClazzId(clazzId);
    }

}
