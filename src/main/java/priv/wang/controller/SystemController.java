package priv.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import priv.wang.entity.User;
import priv.wang.service.IStudentService;
import priv.wang.service.IUserService;
import priv.wang.utils.CpachaUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 13:54
 * @comment: null
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentService studentService;

    /**
     * 初始化跳转至login页面
     * @return
     */
    @RequestMapping("/login")
    public String loginPage(){
        return "system/login";
    }

    /**
     * 获取验证码图片
     * @param request
     * @param response
     */
    @RequestMapping("/get_cpacha")
    public void getCpacha(HttpServletRequest request, HttpServletResponse response) {

        //创建获取验证码工具类
        CpachaUtil cpachaUtil = new CpachaUtil();
        //获取验证码
        String generatorVCode = cpachaUtil.generatorVCode();
        //存入会话方便验证
        request.getSession().setAttribute("loginCpacha",generatorVCode);
        //获取验证码图片
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            //将生成的验证码图片写入(第一个参数为要写入的图片，第二个为图片格式，第三个为一个输出流)
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录验证
     * @param username 用户输入的用户名
     * @param password 用户输入的密码
     * @param vcode 用户输入的验证码
     * @param type  登录类型：1管理员 2学生
     */
    @RequestMapping("/loginVerify")
    public @ResponseBody Map loginVerify(String username, String password, String vcode, Integer type, HttpServletRequest request){
        Map<String,String> lists = new HashMap<>();
        //验证是否为空
        if(username != null && username != "" && password != null && password != "" && vcode != null && vcode != "" && type != null) {
            //取出session中的验证码
            String sessionVcode = (String) request.getSession().getAttribute("loginCpacha");
            //验证验证码是否正确
            if(!sessionVcode.equalsIgnoreCase(vcode)){
                lists.put("type","error");
                lists.put("msg","验证码输入有误！");
                return lists;
            }
            //标记结果
            User user = null;
            //判断是管理员还是学生
            if(type == 1){
                //管理员
                user = userService.findLoginVerify(username, password);
            }
//            else{
//                //学生
//                result = studentService.loginVerify(username, password);
//            }
            //判断是否登陆成功
            if(user != null) {
                //将User对象存入会话
                request.getSession().setAttribute("user",user);
                //保存当前登录状态 1：管理员 2：学生
                request.getSession().setAttribute("userType",1);
                lists.put("type", "success");
                lists.put("msg", "登陆成功！");
            }else{
                lists.put("type", "lose");
                lists.put("msg", "用户或密码有误！");
            }
            return lists;
        }else{
            lists.put("type","error");
            lists.put("msg","请输入完整的登录信息");
            return lists;
        }
    }

    /**
     * 登陆成功后的跳转
     * @param mav
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView mav, HttpServletRequest request){
        //登陆后的主页跳转
        mav.setViewName("system/index");
        return mav;
    }

    @RequestMapping("loginOut")
    public ModelAndView loginOut(ModelAndView modelAndView, HttpServletRequest request){
        //消灭该会话
        request.getSession().invalidate();
        modelAndView.setViewName("system/login");
        return modelAndView;
    }

}
