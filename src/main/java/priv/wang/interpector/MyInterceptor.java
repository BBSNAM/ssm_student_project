package priv.wang.interpector;

import org.springframework.web.servlet.HandlerInterceptor;
import priv.wang.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @user: Mr.Wang
 * @date: 2019/9/4
 * @time: 16:31
 * @comment: null
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 跳转前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每次跳转前判断当前是否已经是登录状态，如果不是则跳到登录页面，防止直接输入网址越过登录访问
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            response.sendRedirect("login");
            return false;
        }
        return true;
    }
}
