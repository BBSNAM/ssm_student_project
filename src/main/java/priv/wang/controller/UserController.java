package priv.wang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import priv.wang.entity.User;
import priv.wang.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 16:56
 * @comment: null
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 用户列表页面跳转
     * @param modelAndView
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list(ModelAndView modelAndView){
        modelAndView.setViewName("user/user_list");
        return modelAndView;
    }

    /**
     * 查询全部（利用easyui实现分页）
     * @param page  easyui自动传来的page代表当前页
     * @param rows  easyui自动传来的rows代表每页显示的数量
     * @return
     */
    @RequestMapping("getList")
    public @ResponseBody Map findAll(Integer page, Integer rows, String username){
        Map<String, Object> map = new HashMap<>();
        if(rows == 10){
            rows = 20;      //改为每页显示20条，easyui默认每页10条
        }
        if(username != null && username != ""){
            username = "%"+username+"%";
        }
        //调用查询方法传入分页所需参数
        List<User> userList = userService.findAll((page-1)*rows, rows, username);
        //查询总记录数
        int count = userService.findCount(username);
        //存入Map
        map.put("rows",userList);
        map.put("total",count);
        return map;
    }

    /**
     * 插入一条信息
     */
    @RequestMapping("/insert")
    public @ResponseBody Map insertInfo(String username, String password){
        Map<String, String> map = new HashMap<>();
        //创建User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        //调用插入方法
        boolean result = userService.insertInfo(user);
        //判断结果
        if(result){
            map.put("type","success");
            map.put("msg","插入成功");
        }else{
            map.put("type","error");
            map.put("msg","插入数据时出现错误！请联系相关人士解决！");
        }
        return map;
    }

    /**
     * 更新一条信息
     */
    @RequestMapping("/update")
    public @ResponseBody Map updateInfo(String username, String password, Integer id){
        Map<String, String> map = new HashMap<>();
        //创建User对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setId(id);
        //调用更新方法
        boolean result = userService.updateInfo(user);
        //判断结果
        if(result){
            map.put("type","success");
            map.put("msg","更新成功");
        }else{
            map.put("type","error");
            map.put("msg","更新数据时出现错误！请联系相关人士解决！");
        }
        return map;
    }

    /**
     * 删除方法
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public @ResponseBody Map deleteInfo(int[] ids, HttpServletRequest request) {
        Map<String, String> returnMap = new HashMap<>();
        //取出当前登录对象，禁止删除当前登录对象
        User user = (User) request.getSession().getAttribute("user");
        //取出要删除的ID
        String delValue = "";
        for (int i = 0; i < ids.length; i++) {
            //判断删除的ID中是否包含当前登录对象
            if(ids[i] == user.getId()){
                returnMap.put("type","error");
                returnMap.put("msg","无法删除当前登录对象！");
                return returnMap;
            }
            //处理数组拼接删除条件
            if(i == ids.length-1){
                delValue += ids[i];
            }else{
                delValue += ids[i]+",";
            }
        }
        //调用删除方法
        boolean result = userService.deleteInfo(delValue);
        //判断结果
        if(result){
            returnMap.put("type","success");
            returnMap.put("msg","删除成功");
        }else{
            returnMap.put("type","error");
            returnMap.put("msg","删除数据时出现错误！请联系相关人士解决！");
        }
        return returnMap;
    }

}
