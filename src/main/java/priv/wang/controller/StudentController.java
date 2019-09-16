package priv.wang.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import priv.wang.entity.Clazz;
import priv.wang.entity.Student;
import priv.wang.service.IClazzService;
import priv.wang.service.IStudentService;
import priv.wang.utils.AutoCreateDataUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 16:57
 * @comment: Student表的控制器
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IClazzService clazzService;
    @Autowired
    private AutoCreateDataUtil autoCreateDataUtil;

    /**
     * 学生列表的页面跳转
     * @param modelAndView
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("student/student_list");
        //查询出所有学科的信息方便添加到增改查下拉框中
        int count = clazzService.findCount(null, null);     //先查询出学科的总条数
        //查询（从0到count代表，该条件代表查询全部）
        List<Clazz> lists = clazzService.findAll(0,count,null, null);
        //添加到ModelAndView（使用fastjson将数据转换为要用的格式）
        modelAndView.addObject("clazzListJson", JSON.toJSONString(lists));
        modelAndView.addObject("clazzList", lists);
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
    public @ResponseBody
    Map findAll(Integer page, Integer rows, String name, Integer clazzId){
        Map<String, Object> returnMap = new HashMap<>();
        if(rows == 10){
            rows = 20;      //改为每页显示20条，easyui默认每页10条
        }
        //开始位置：当前页-1*每页显示数量  结束位置：当前页
        List<Student> gradeList = studentService.findAll((page - 1) * rows, rows, name, clazzId);
        //查询总记录数
        int count = studentService.findCount(name, clazzId);
        returnMap.put("rows",gradeList);
        returnMap.put("total",count);
        return returnMap;
    }

    /**
     * 插入一条信息
     * @param student
     * @return
     */
    @RequestMapping("/insert")
    public @ResponseBody Map insertInfo(Student student){
        Map<String, String> returnMap = new HashMap<>();
        //设置自动生成的学号
        student.setSn(autoCreateDataUtil.getStudentNo(student.getClazzId()));
        //调用插入方法
        boolean result = studentService.insertInfo(student);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","添加成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","添加学生信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 更新一条信息
     * @param student
     * @return
     */
    @RequestMapping("/update")
    public @ResponseBody Map updateInfo(Student student){
        Map<String, String> returnMap = new HashMap<>();
        //调用更新方法
        boolean result = studentService.updateInfo(student);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","更新成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","更新学生信息出现错误！请联系相关人士解决！");
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
        boolean result = studentService.deleteInfo(resultIds);
        //判断是否成功
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","删除成功！");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","删除学生信息出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

    /**
     * 上传图片
     * @param request
     * @param photo
     * @return
     */
    @RequestMapping("/upload_photo")
    public @ResponseBody Map uploadPhoto(HttpServletRequest request, MultipartFile photo) throws IOException {
        Map<String, String> returnMap = new HashMap<>();
        //设置上传文件的目标路径
        String path = request.getServletContext().getRealPath("/")+"upload/";
        String showPath = request.getServletContext().getContextPath()+"/upload/";
        // 1. 判断路径是否存在
        File file = new File(path);
        if(!file.exists()){
            returnMap.put("type","error");
            returnMap.put("msg","上传路径不存在");
            return returnMap;
        }
        // 2. 执行上传
        String fileName = photo.getOriginalFilename();  //获取上传文件名
        //判断格式是否正确
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
            returnMap.put("type","error");
            returnMap.put("msg","上传文件格式有误！请上传jpg,png,gif,jpeg格式的图片！");
            return returnMap;
        }
        photo.transferTo(new File(path, fileName));
        returnMap.put("type","success");
        returnMap.put("src",showPath+fileName);
        returnMap.put("msg","删除学生信息出现错误！请联系相关人士解决！");
        return returnMap;
    }

}
