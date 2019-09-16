package priv.wang.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.wang.service.IClazzService;
import priv.wang.service.IGradeService;
import priv.wang.service.IStudentService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @user: Mr.Wang
 * @date: 2019/9/7
 * @time: 10:59
 * @comment: 自动生成一些特定数据的工具类
 */
@Component
public class AutoCreateDataUtil {

    @Autowired
    private IClazzService clazzService;     //Clazz业务层实现类
    @Autowired
    private IStudentService studentService;     //Student业务层实现类
    @Autowired
    private IGradeService gradeService;         //Grade业务层实现类


    /**
     * 创建学会说呢过学号方法
     * 学号 = 年份 + 专业编号 + 班级号 + 学生报到编号
     * 年份：grade表的year列（根据clazzId 查询grade表的year列）
     * 专业编号：grade表的series列（根据clazzId 查询grade表的series列）
     * 班级号：clazz表的clazzNo列（指定clazz ID的clazzNo列）
     * 学生报到编号：当前学生所属班级的学生总数+1
     * @return
     */
    public String getStudentNo(Integer clazzId){
        // 根据ClazzId先查出grade表的创建年份
        String year = gradeService.findYearByClazzId(clazzId);
        // 根据ClazzId先查出grade表的专业编号
        int seriesId = gradeService.findSeriesByClazzId(clazzId);
        String resultSeries = String.valueOf(seriesId);
        if(seriesId > 0 && seriesId < 10){
            resultSeries = "0"+seriesId;
        }
        // 根据clazzId查出该班级的班级号
        String clazzNo = clazzService.findClazzNoByClazzId(clazzId);
        // 计算出改学生的报道编号  根据班级编号
        int studentNo = studentService.findCount(null, clazzId) + 1;
        String resultStudentNo = studentNo+"";
        if(studentNo > 0 && studentNo < 10){
            resultStudentNo = "0"+studentNo;
        }
        //返回最终学号
        return year + resultSeries + clazzNo + resultStudentNo;
    }

    /**
     * 插入班级时生成班级编号
     * 根据班级的gradeId查询同gradeId下数据总计数数（记录数+1就是新的班级编号）
     * @return
     */
    public String getAutoClazzNo(Integer gradeId){
        int count = clazzService.findCount(null, gradeId)+1;
        return count < 10 && count >= 0 ? "0"+count : count+"";
    }

}
