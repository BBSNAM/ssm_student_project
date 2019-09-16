package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.IGradeDao;
import priv.wang.entity.Grade;
import priv.wang.service.IGradeService;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: null
 */
@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private IGradeDao dao;

    /**
     * 查询全部方法
     * @param start 分页开始的位置
     * @param end   分页结束的位置
     * @param name  根据传入参数判断是否模糊查询
     * @return
     */
    @Override
    public List<Grade> findAll(Integer start, Integer end, String name, Integer series) {
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
     * 根据ID查询单条数据
     * @param id
     * @return
     */
    @Override
    public Grade findInfoById(Integer id) {
        return dao.findInfoById(id);
    }

    /**
     * 插入一条信息
     * @param grade
     * @return
     */
    @Override
    public boolean insertInfo(Grade grade) {
        if(grade.getRemark() == null || grade.getRemark() == ""){
            grade.setRemark("暂无备注");
        }
        return dao.insertInfo(grade) > 0;
    }

    /**
     * 更新一条信息
     * @param grade
     * @return
     */
    @Override
    public boolean updateInfo(Grade grade) {
        if(grade.getRemark() == null || grade.getRemark() == ""){
            grade.setRemark("暂无备注");
        }
        return dao.updateInfo(grade) > 0;
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
     * 根据clazzId查询出该届年级的开设时间
     * @param clazzId
     * @return
     */
    @Override
    public String findYearByClazzId(Integer clazzId) {
        return dao.findYearByClazzId(clazzId);
    }

    /**
     * 根据clazzId查询出该届年级的专业编号
     *
     * @param clazzId
     * @return
     */
    @Override
    public int findSeriesByClazzId(Integer clazzId) {
        return dao.findSeriesByClazzId(clazzId);
    }

}
