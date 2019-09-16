package priv.wang.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import priv.wang.entity.Grade;
import priv.wang.entity.Subject;
import priv.wang.entity.SubjectSeries;
import priv.wang.service.IGradeService;
import priv.wang.service.ISubjectSeriesService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 17:04
 * @comment: Grade表的控制器
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;
    @Autowired
    private ISubjectSeriesService seriesService;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("grade/grade_list");
        //查询出所有学科的信息方便添加到增改查下拉框中
        int count = seriesService.findCount(null, null);     //先查询出学科的总条数
        //查询（从0到count代表，该条件代表查询全部）
        List<SubjectSeries> lists = seriesService.findAll(0,count,null, null);
        //添加到ModelAndView（使用fastjson将数据转换为要用的格式）
        modelAndView.addObject("seriesListJson", JSON.toJSONString(lists));
        modelAndView.addObject("seriesList", lists);
        return modelAndView;
    }

    /**
     * 查询全部
     * @param page  easyui实现分页，默认会传来page直接接收即可，page代表当前页
     * @param rows  easyui实现分页，默认会传来rows直接接收即可，rows代表每页显示的记录数
     *              注意返回时需要返回总记录数告诉easyui数据总记录数是多少
     *              规定返回的总记录数名为total，返回的数据结果为rows
     * @param name
     */
    @RequestMapping("/findAll")
    public @ResponseBody Map findAll(Integer page, Integer rows, String name, Integer series){
        Map<String, Object> returnMap = new HashMap<>();
        if(rows == 10){
            rows = 20;      //改为每页显示20条，easyui默认每页10条
        }
        //开始位置：当前页-1*每页显示数量  结束位置：当前页
        List<Grade> gradeList = gradeService.findAll((page - 1) * rows, rows, name, series);
        //查询总记录数
        int count = gradeService.findCount(name, series);
        returnMap.put("rows",gradeList);
        returnMap.put("total",count);
        return returnMap;
    }

    /**
     * 插入一条信息
     * @param grade
     * @return
     */
    @RequestMapping("/insert")
    public @ResponseBody Map insertInfo(Grade grade){
        Map<String, String> returnMap = new HashMap<>();
        //调用插入方法
        boolean result = gradeService.insertInfo(grade);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","添加成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","添加年级信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 更新一条信息
     * @param grade
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody Map updateInfo(Grade grade){
        Map<String, String> returnMap = new HashMap<>();
        //调用更新方法
        boolean result = gradeService.updateInfo(grade);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","更新成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","更新年级信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 删除一条信息
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody Map deleteInfo(int[] ids){
        Map<String, String> returnMap = new HashMap<>();
        //处理存放id的数组
        String resultIds = "";
        for (int i = 0; i < ids.length; i++) {
            if(i == ids.length-1){
                resultIds += ids[i];
                break;
            }else{
                resultIds += ids[i]+",";
            }
        }
        //执行删除
        boolean result = gradeService.deleteInfo(resultIds);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","删除成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","删除年级信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

}
