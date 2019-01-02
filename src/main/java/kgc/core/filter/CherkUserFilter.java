package kgc.core.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @ClassName kgc.core.filter
 * @Author xiaohao
 * @Date 2018/12/20 13:57
 * @Version 1.0
 **/
public class CherkUserFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     /**
      * 拦截资源，进行拦截处理
      * 1.要验证当前请求有木有登录操作
      *   1.1如果标识是否登录
      */
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        Object studentObj=session.getAttribute("Student");
        Object teaObj=session.getAttribute("Teacher");

        String path=request.getContextPath();
        if (studentObj!=null||teaObj!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            response.sendRedirect(path+"/Login.jsp?msg=1");
        }

    }

    @Override
    public void destroy() {

    }
}
