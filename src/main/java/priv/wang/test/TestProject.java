package priv.wang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.wang.entity.User;
import priv.wang.service.IUserService;
import priv.wang.utils.AutoCreateDataUtil;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 15:43
 * @comment: null
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TestProject {

    @Autowired
    private IUserService userService;

    @Autowired
    private AutoCreateDataUtil autoCreateDataUtil;

    /**
     * 测试登录验证
     */
    @Test
    public void TestLoginVerify(){
        User user = userService.findLoginVerify("admin","admin");
        System.out.println(user);
    }

    @Test
    public void TestCreateStudentNo(){
        System.out.println(autoCreateDataUtil.getStudentNo(12));
    }

    @Test
    public void TestGetYear(){
        System.out.println(autoCreateDataUtil.getAutoClazzNo(17));
    }

}
