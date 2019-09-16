package priv.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.wang.dao.IStudentDao;
import priv.wang.dao.ISubjectSeriesDao;
import priv.wang.entity.SubjectSeries;
import priv.wang.entity.SubjectType;
import priv.wang.service.IStudentService;
import priv.wang.service.ISubjectSeriesService;

import java.util.List;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:40
 * @comment: SubjectSeries表的业务层实现类
 */
@Service
public class SubjectSeriesServiceImpl implements ISubjectSeriesService {

    @Autowired
    private ISubjectSeriesDao seriesDao;

    /**
     * 查询全部信息
     * @param start
     * @param end
     * @param name
     * @return
     */
    @Override
    public List<SubjectSeries> findAll(Integer start, Integer end, String name, Integer typeId) {
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return seriesDao.findAll(start, end, name, typeId);
    }

    /**
     * 查询总记录数
     * @param name
     * @return
     */
    @Override
    public int findCount(String name, Integer typeId) {
        if(name != null && name != ""){
            name = "%"+name+"%";
        }
        return seriesDao.findCount(name, typeId);
    }

    /**
     * 添加方法
     * @param subjectSeries
     * @return
     */
    @Override
    public boolean insertInfo(SubjectSeries subjectSeries) {
        if(subjectSeries.getRemark() == null || subjectSeries.getRemark() == ""){
            subjectSeries.setRemark("暂无备注");
        }
        return seriesDao.insertInfo(subjectSeries) > 0;
    }

    /**
     * 修改方法
     * @param subjectSeries
     * @return
     */
    @Override
    public boolean updateInfo(SubjectSeries subjectSeries) {
        if(subjectSeries.getRemark() == null || subjectSeries.getRemark() == ""){
            subjectSeries.setRemark("暂无备注");
        }
        return seriesDao.updateInfo(subjectSeries) > 0;
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
    @Override
    public boolean deleteInfo(String id) {
        return seriesDao.deleteInfo(id) > 0;
    }

}
