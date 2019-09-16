package priv.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import priv.wang.entity.Subject;
import priv.wang.service.ISubjectService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @user: Mr.Wang
 * @date: 2019/9/5
 * @time: 22:17
 * @comment: Subject表的控制器
 */
@Controller
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    /**
     * 页面跳转
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("subject/subject_list");
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
    public @ResponseBody Map findAll(Integer page, Integer rows, String name){
        Map<String, Object> returnMap = new HashMap<>();
        if(rows == 10){
            rows = 20;      //改为每页显示20条，easyui默认每页10条
        }
        //计算分页条件调用查询方法
        List<Subject> subjectList = subjectService.findAll((page - 1) * rows, rows, name);
        //查询总记录数
        int count = subjectService.findCount(name);
        returnMap.put("rows",subjectList);
        returnMap.put("total",count);
        return returnMap;
    }

    /**
     * 插入方法
     * @param subject
     * @return
     */
    @RequestMapping("/insert")
    public @ResponseBody Map insertInfo(Subject subject){
        Map<String, String> returnMap = new HashMap<>();
        //调用插入方法
        boolean result = subjectService.insertInfo(subject);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","添加成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","添加学科信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 更新方法
     * @param subject
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody Map updateInfo(Subject subject){
        Map<String, String> returnMap = new HashMap<>();
        //调用更新方法
        boolean result = subjectService.updateInfo(subject);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","更新成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","更新学科信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 删除方法
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody Map deleteInfo(int[] ids){
        Map<String, String> returnMap = new HashMap<>();
        //处理存放id的数组
        String delIds = "";
        for (int i = 0; i < ids.length; i++) {
            if(i == ids.length-1){
                delIds += ids[i];
                break;
            }else{
                delIds += ids[i]+",";
            }
        }
        //执行删除
        boolean result = subjectService.deleteInfo(delIds);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","删除成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","删除学科信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

}
