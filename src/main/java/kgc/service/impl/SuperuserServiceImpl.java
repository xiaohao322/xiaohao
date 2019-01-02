package kgc.service.impl;

import kgc.dao.impl.SupuserImplDao;
import kgc.pojo.Bigclass;
import kgc.pojo.Customer;
import kgc.pojo.Page;
import kgc.pojo.Superuser;
import kgc.service.SuperuserServic;

import java.util.List;

public class SuperuserServiceImpl implements SuperuserServic {
    private static SuperuserServiceImpl ourInstance = new SuperuserServiceImpl();

    public static SuperuserServiceImpl getInstance() {
        return ourInstance;
    }

    private SuperuserServiceImpl() {
    }
    //获取SupuserImplDao对象
    private SupuserImplDao supDao=SupuserImplDao.getInstance();
    @Override
    public Superuser AdmLogin(String admName, String admPwd) {
        return supDao.AdmLogin(admName,admPwd);
    }

    @Override
    public boolean UpdataAdmin(Superuser admin) {
        return supDao.UpdataAdmin(admin);
    }

    @Override
    public Page<Customer> showUser(int pagesize, int pageNum, String userid, String name, String usersex) {
        return supDao.showUser(pagesize,pageNum,userid,name,usersex);
    }

    @Override
    public boolean DelUser(long id) {
        return supDao.DelUser(id);
    }

    @Override
    public boolean AddUser(Customer cust) {
        return supDao.AddUser(cust);
    }

    @Override
    public boolean UpdataUser(Customer cust) {
        return supDao.UpdataUser(cust);
    }

    @Override
    public Customer getUser(int id) {
        return supDao.getUser(id);
    }

    @Override
    public Superuser getAdmin(int id) {
        return supDao.getAdmin(id);
    }
}
