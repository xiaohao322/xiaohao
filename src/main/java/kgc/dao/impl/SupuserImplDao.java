package kgc.dao.impl;

import kgc.core.util.PageUtil;
import kgc.dao.BaseDao;
import kgc.dao.SuperuserDao;
import kgc.pojo.Bigclass;
import kgc.pojo.Customer;
import kgc.pojo.Page;
import kgc.pojo.Superuser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kgc.dao.BaseDao.dbClose;
import static kgc.dao.BaseDao.getQuery;

public class SupuserImplDao implements SuperuserDao {
    private static SupuserImplDao ourInstance = new SupuserImplDao();

    public static SupuserImplDao getInstance() {
        return ourInstance;
    }

    private SupuserImplDao() {
    }

    @Override
    public Superuser AdmLogin(String admName, String admPwd) {
        String sql="select * from superuser where userLoginName=? and userPassword=?";
        Object[] parameter = {admName, admPwd};
        ResultSet rs = getQuery(sql,parameter);
        Superuser sup = new Superuser();
        try{
            if (rs.next()){

                sup.setId(rs.getInt("id"));
                sup.setUserName(rs.getString("userName"));
                sup.setUserPassword(rs.getString("userPassword"));
                sup.setUserImage(rs.getString("userImage"));
                sup.setUserStatus(rs.getInt("userStatus"));
                sup.setUserId(rs.getString("userID"));
                sup.setUserLoginName(rs.getString("userLoginName"));
                return sup;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            dbClose();
        }
    return null;
    }

    @Override
    public boolean UpdataAdmin(Superuser admin) {
        String sql = "update superuser set username=?,userPassword=?,userid=?,userloginname=? where id=?";
        Object[] parameter = {admin.getUserName(), admin.getUserPassword(), admin.getUserId(), admin.getUserLoginName(), admin.getId()};

        int i = -1;
        i = BaseDao.getUpdate(sql, parameter);
        if (i > 0) {
            return true;
        }
        return false;
    }




    public Page<Customer> showUser(int pagesize, int pageNum) {
        Page<Customer> page = new Page<Customer>();
        page.setPageNumber(pageNum);
        page.setPageSize(pagesize);
        String sql = "select count(1) from customer";
        page.setTotalRecode(PageUtil.getTotalRecode(sql, null));

        List<Customer> clist = new ArrayList<Customer>();
        String sql2 = "select * from customer";
        ResultSet res = PageUtil.getPageDate(sql2, pagesize, pageNum, null);
        Customer cus = null;
        try {
            while (res.next()) {
                cus = new Customer(res.getLong(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9),
                        res.getDate(10));
                clist.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.dbClose();
        }
        page.setPageData(clist);
        return page;
    }

    public Page<Customer> showUser(int pagesize, int pageNum, String userid, String name, String usersex) {
        Page<Customer> page = new Page<Customer>();
        page.setPageNumber(pageNum);
        page.setPageSize(pagesize);
        ResultSet res = null;
        List<Customer> list = new ArrayList<Customer>();
        //查询共有多少数据
        if (userid != null && usersex != null && name != null) {
            int idsize = userid.length();
            int sexsize = usersex.length();
            int namesize = name.length();
            System.out.println("id" + idsize + ":性别" + sexsize + ":名字" + namesize);
            //都为空查全部
            if (idsize == 0 && sexsize == 0 && namesize == 0) {
                String sql = "select count(1) from customer";
                page.setTotalRecode(PageUtil.getTotalRecode(sql, null));
                String sql2 = "select * from customer";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, null);
                //查id id为详细查询与名称无关 所以即使填了名字和不会有事
            } else if (idsize > 0) {
                System.out.println("查id");
                String sql = "select count(1) from customer where couCode=?";
                Object[] parameter = {userid};
                page.setTotalRecode(PageUtil.getTotalRecode(sql, parameter));
                String sql2 = "select * from customer where couCode=?";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, parameter);
                //id 和性别
            } else if (idsize > 0 && sexsize > 0 && namesize == 0) {
                System.out.println("查名字");
                String sql = "select count(1) from customer where cuscode=? and cussex=?";
                Object[] parameter = {userid, usersex};
                page.setTotalRecode(PageUtil.getTotalRecode(sql, parameter));
                String sql2 = "select * from customer where cuscode=? and cussex=?";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, parameter);
                //id性别和姓名
            } else if (idsize == 0 && sexsize > 0 && namesize > 0) {
                System.out.println("名字和性别");
                String sql = "select count(1) from customer where  cussex=? and cusname like ?";
                Object[] parameter = {usersex, "%" + name + "%"};
                page.setTotalRecode(PageUtil.getTotalRecode(sql, parameter));
                String sql2 = "select * from customer where cussex=? and cusname like ?";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, parameter);
                //性别
            } else if (idsize == 0 && sexsize > 0 && namesize == 0) {
                System.out.println("性别");
                String sql = "select count(1) from customer where cussex=?";
                Object[] parameter = {usersex};
                page.setTotalRecode(PageUtil.getTotalRecode(sql, parameter));
                String sql2 = "select * from customer where cussex=?";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, parameter);
                //姓名
            } else if (idsize == 0 && sexsize == 0 && namesize > 0) {
                System.out.println("名字");
                String sql = "select count(1) from customer where cusname like ?";
                Object[] parameter = {"%" + name + "%"};
                page.setTotalRecode(PageUtil.getTotalRecode(sql, parameter));
                String sql2 = "select *  from customer where cusname like ?";
                res = PageUtil.getPageDate(sql2, pagesize, pageNum, parameter);
                //性别和姓名
            }
        }
        Customer cus = null;
        try {
            while (res.next()) {
                cus = new Customer(res.getLong(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9),
                        res.getDate(10));
                list.add(cus);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.dbClose();
        }
        page.setPageData(list);
        return page;
    }

    //查询用户数据
    public List<Superuser> showUser(String sql, Object[] parameter) {
        ResultSet res = BaseDao.getQuery(sql, parameter);
        List<Superuser> list = new ArrayList<Superuser>();
        Superuser suuser = null;
        try {
            while (res.next()) {
                suuser = new Superuser(res.getLong(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getLong(5),
                        res.getString(6), res.getString(7));
                list.add(suuser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public boolean DelUser(long id) {
        String sql = "delete from customer where id=?";
        Object[] parameter = {id};
        int i = -1;
        i = BaseDao.getUpdate(sql, parameter);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 添加用户
     *
     * @param cust
     * @return
     */
    public boolean AddUser(Customer cust) {
        String sql = "insert into Customer values(null,?,?,?,?,?,?,?,?,?)";
        Object[] parameter = {cust.getCusName(), cust.getCusLoginName(), cust.getCusPassword(), cust.getCusEmail(), cust.getCusSex(), cust.getCusPhoto(), cust.getCusHobby(), cust.getCusCode(), cust.getCusBirthday(), cust.getId()
        };
        int i = -1;
        i = BaseDao.getUpdate(sql, parameter);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新用户数据
     *
     * @param cust
     * @return
     */
    public boolean UpdataUser(Customer cust) {
        String sql = "UPDATE  Customer SET cusName=?,cusEmail=?,cusSex=?,cusHobby=?,cusCode=? , cusBirthday=? WHERE id=?";
        Object[] parameter = {cust.getCusName(), cust.getCusEmail(), cust.getCusSex(), cust.getCusHobby(), cust.getCusCode(), cust.getCusBirthday(),cust.getId()};
        int i = -1;
        i = BaseDao.getUpdate(sql, parameter);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public Customer getUser(int id) {
        String sql = "select * from customer where id=?";
        Object[] parameter = {id};
        ResultSet res = BaseDao.getQuery(sql, parameter);
        Customer cus=null;
        try {
            while (res.next()) {

                cus = new Customer(res.getLong(1), res.getString(2), res.getString(3),
                        res.getString(4), res.getString(5), res.getString(6),
                        res.getString(7), res.getString(8), res.getString(9),
                        res.getDate(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return cus;
    }
    public Superuser getAdmin(int id) {
        String sql = "select * from Superuser where id=?";
        Object[] parameter = {id};
        ResultSet rs = BaseDao.getQuery(sql, parameter);
        Superuser sup=new Superuser();
        try {
            while (rs.next()) {
                sup.setId(rs.getInt("id"));
                sup.setUserName(rs.getString("userName"));
                sup.setUserPassword(rs.getString("userPassword"));
                sup.setUserImage(rs.getString("userImage"));
                sup.setUserStatus(rs.getInt("userStatus"));
                sup.setUserId(rs.getString("userID"));
                sup.setUserLoginName(rs.getString("userLoginName"));
                return sup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
