package kgc.service;

import kgc.pojo.Bigclass;
import kgc.pojo.Customer;
import kgc.pojo.Page;
import kgc.pojo.Superuser;

import java.util.List;

public interface SuperuserServic {
    //管理员登录
    Superuser AdmLogin (String admName, String admPwd);
    //更新管理员数据
    boolean UpdataAdmin(Superuser admin);
    //查询用户
    Page<Customer> showUser(int pagesize, int pageNum, String userid, String name, String usersex);
    //删除
    boolean DelUser(long id);
    //添加
    boolean AddUser(Customer cust);
    //更新用户数据
    boolean UpdataUser(Customer cust);

    Customer getUser(int id);
    Superuser getAdmin(int id);
}
