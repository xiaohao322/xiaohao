package kgc.controller;

import com.alibaba.fastjson.JSONArray;
import kgc.pojo.*;
import kgc.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "SuperuserServlet",urlPatterns = "/doSup")
public class SuperuserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //编码方式统一操作
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    //获取输入流对象
    PrintWriter out = response.getWriter();
    //获取path路径
    String path = request.getContextPath();
    //获取session对象
    HttpSession session = request.getSession();
    //获取参数action的值
    String action = request.getParameter("action");
    //获取服务对象
    SuperuserServiceImpl supService = SuperuserServiceImpl.getInstance();
    /**
     * 管理登录
     */
    if(action!=null && action.equals("admlogin")){
        //接收参数
        String userLoginName = request.getParameter("userLoginName").trim();
        String userPassword = request.getParameter("userPassword");
        //执行并接收结果
        Superuser superuser = supService.AdmLogin(userLoginName,userPassword);

        //判断是否登录成功并传递参数到页面
        if (superuser != null) {
            //存储结果到session
            session.setAttribute("admlogin", superuser);
            response.sendRedirect(path+"page/index.jsp");
        } else {
            request.setAttribute("error","输入的账号或密码有误");
            request.getRequestDispatcher(path+"/GMLogin.jsp").forward(request,response);

        }
    }
    /**
     * 修改管理
     */
    if(action!=null && action.equals("Updateadm")){
        //获取参数并创建对象
        int id=Integer.parseInt(request.getParameter("id"));
        String admName = request.getParameter("username");
        String admPwd = request.getParameter("userpwd");
        String admid = request.getParameter("usecode");
        String admLoginName = request.getParameter("userloginname");
        Superuser admin = new Superuser(id, admName, admPwd, null, 0, admid, admLoginName);
        System.out.println(admin.getUserName()+"wwww"+admin.getUserPassword());
        //执行并返回结果
        if (supService.UpdataAdmin(admin)) {
            System.out.println("修改成功");
            response.sendRedirect(path+"page/index.jsp");
        } else {
            System.out.println("修改失败");
        }
    }
    //获取所有用户数据并返回
    if (action != null && action.equals("unionQuery")) {
        //定义每页显示的页数
        int pagesize=6;
        //页码
        int pagenum=Integer.parseInt(request.getParameter("pageNumber"));
        //获取查询信息
        String userName = request.getParameter("username").trim();
        String userid= request.getParameter("userid").trim();
        String usersex=request.getParameter("usersex");
        Page<Customer> page=supService.showUser(pagesize,pagenum,userid,userName,usersex);
        session.setAttribute("page",page);
        request.setAttribute("userId",userid);
        request.setAttribute("userName",userName);
        request.setAttribute("userSex",usersex);
        request.getRequestDispatcher("/page/showCustomer.jsp").forward(request,response);
    }
    //获取用户
    if (action!=null&&action.equals("getUser")){
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=supService.getUser(id);
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/page/UpdataCustomer.jsp").forward(request,response);
    }
    //获取管理员
    if (action!=null&&action.equals("getAdmin")){
        int id=Integer.parseInt(request.getParameter("id"));
        Superuser superuser=supService.getAdmin(id);
        request.setAttribute("superuser",superuser);
        request.getRequestDispatcher("/page/UpdataAdmin.jsp").forward(request,response);
    }
    //删除用户
    if (action!=null&&action.equals("delUser")){
        Long id=Long.parseLong(request.getParameter("id"));
        if (supService.DelUser(id)){
            System.out.println("删除成功");
            response.sendRedirect("doSup?action=unionQuery&pageNumber=1&username=&userid=&usersex=");
        }else {
            System.out.println("删除失败");
            response.sendRedirect("doSup?action=unionQuery&pageNumber=1&username=&userid=&usersex=");
        }
    }
    //修改用户
    if (action!=null&&action.equals("updataUser")) {
        Long id=Long.parseLong(request.getParameter("id"));
        String name= request.getParameter("name");
        String logiNname= request.getParameter("loginName");
        String pwd=request.getParameter("pwd");
        String email= request.getParameter("email");
        String sex= request.getParameter("sex");
        String hobby= request.getParameter("hobby");
        String code= request.getParameter("code");
        String birth= request.getParameter("birth");
        Customer cus=new Customer(id,name,logiNname,pwd,email,sex,null,hobby,code, Date.valueOf(birth));
        if (supService.UpdataUser(cus)){
            System.out.println("修改成功");
            response.sendRedirect("doSup?action=unionQuery&pageNumber=1&username=&userid=&usersex=");
        }else {
            System.out.println("修改失败");
            response.sendRedirect("doSup?action=unionQuery&pageNumber=1&username=&userid=&usersex=");
        }
    }
    //添加用户
    if (action != null && action.equals("adduser")) {
        String userName= request.getParameter("username").trim();
        String userLoginName = request.getParameter("loginname").trim();
        String userPwd= request.getParameter("userpwd").trim();
        String userEmail= request.getParameter("useremail").trim();
        String usersex = request.getParameter("usersex");

    }
    BigclassServiceImpl bigClassService=BigclassServiceImpl.getInstance();
    //查询大分类
    if (action!=null&&action.equals("queryAllBigClass")){
        int pageSize=6;
        int pagenum=Integer.parseInt(request.getParameter("pageNumber"));
        Page<Bigclass> page=bigClassService.queryAllBigClass(pageSize,pagenum);
        session.setAttribute("page",page);
        request.getRequestDispatcher("/page/BigQuery.jsp").forward(request,response);
    }
    //删除大分类
    if (action!=null&&action.equals("delBClass")){
        int id=Integer.parseInt(request.getParameter("id"));
        if (bigClassService.delByIdBigClass(id)){
            response.sendRedirect("doSup?action=queryAllBigClass&pageNumber=1");
        }else {
            System.out.println("删除失败。");
        }
    }
    SmallServiceImpl smallService=SmallServiceImpl.getInstance();
    //查询小分类
    if (action!=null&&action.equals("unionSmallClass")){
        int pageSize=6;
        int pagenum=Integer.parseInt(request.getParameter("pageNumber"));
        String whereValue=request.getParameter("likeval").trim();
        String bigId=request.getParameter("b_id").trim();
        Page<EXSmallClass> page=smallService.queryUnionSmallClass(pageSize,pagenum,whereValue,bigId);
        System.out.println(page.getPageData().size());
        session.setAttribute("page",page);
        request.setAttribute("likeval",whereValue);
        request.setAttribute("b_id",bigId);
        request.getRequestDispatcher("/page/showSmallClass.jsp").forward(request,response);
    }
    //获取大分类列表
    //ajax 展示折扣
    if (action!=null&&action.equals("showBigClass")){
        List<Bigclass> list =smallService.showBIgClass();
        out.print(JSONArray.toJSONString(list));

    }
    //删除小分类
    if (action!=null&&action.equals("delSClass")){
        int id=Integer.parseInt(request.getParameter("id"));
        if (smallService.delByIdSmallClass(id)){
            response.sendRedirect("doSup?action=unionSmallClass&pageNumber=1&likeval=&b_id=");
        }else {
            System.out.println("删除失败。");
        }
    }
    GoodsServiceImpl goodService=GoodsServiceImpl.getInstance();
    //查询商品
    if (action!=null&&action.equals("unionQgoods")){
        int pageSize=6;
        int pagenum=Integer.parseInt(request.getParameter("pageNumber"));
        String whereValue=request.getParameter("likeval").trim();
        String smallClassid=request.getParameter("sm_id").trim();
        String d_id=request.getParameter("d_id").trim();

        Page<EXGoods> page=goodService.queryUnionGoods(pageSize,pagenum,whereValue,smallClassid,d_id);
        session.setAttribute("page",page);
        request.setAttribute("likeval",whereValue);
        request.setAttribute("sm_id",smallClassid);
        request.setAttribute("d_id",d_id);
        request.getRequestDispatcher("/page/showGoods.jsp").forward(request,response);

    }

    //ajax 展示小分类
    if (action!=null&&action.equals("showSmallClass")){
        List<Smallclass> smallclasses=smallService.showSClass();
        out.print(JSONArray.toJSONString(smallclasses));

    }
    //展示折扣
    //ajax 展示折扣
    if (action!=null&&action.equals("showDesc")){
        List<Discount> discounts=smallService.showDisc();
        out.print(JSONArray.toJSONString(discounts));

    }
    //删除商品
    if (action!=null&&action.equals("delGood")){
        int id=Integer.parseInt(request.getParameter("id"));
        if (goodService.delByIdGoods(id)){
            response.sendRedirect("doSup?action=unionQgoods&pageNumber=1&likeval=&sm_id=&d_id=");
        }else {
            System.out.println("商品删除 失败");
        }

    }
    //打开修改商品页面
    if (action!=null&&action.equals("getGood")){
        String id=request.getParameter("id");
        Goods goods=goodService.getgoodById(id);
        session.setAttribute("Goods",goods);
        response.sendRedirect(path+"/page/updateGoodsInfo.jsp");
    }
    AnnounementServiceImpl annService=AnnounementServiceImpl.getInstance();
    //公告页面
    if (action!=null&&action.equals("queraunn")){
        int pageSize=6;
        int pagenum=Integer.parseInt(request.getParameter("pageNumber"));
        Page<Announcement> page=annService.queryAllAnnoun(pageSize,pagenum);
        session.setAttribute("page",page);
        request.getRequestDispatcher("/page/showAnnouncement.jsp").forward(request,response);

    }

    }
}
