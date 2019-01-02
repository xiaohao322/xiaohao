package kgc.dao.impl;

import kgc.core.util.PageUtil;
import kgc.dao.BaseDao;
import kgc.dao.BigclassDao;
import kgc.pojo.Bigclass;
import kgc.pojo.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kgc.dao.BaseDao.dbClose;
import static kgc.dao.BaseDao.getQuery;
import static kgc.dao.BaseDao.getUpdate;

public class BigclassImplDao implements BigclassDao {
    private static BigclassImplDao ourInstance = new BigclassImplDao();

    public static BigclassImplDao getInstance() {
        return ourInstance;
    }

    private BigclassImplDao() {
    }


    @Override
    public Page<Bigclass> queryAllBigClass(int pageSize, int pageNumber) {
        Page<Bigclass> page = new Page<Bigclass>();
        //2.为Page的成员变量赋值
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        String sql1 = "select count(1) from bigClass ";
        String sql2 = "select * from bigClass  ";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        ResultSet rs =PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        List<Bigclass> list = new ArrayList<Bigclass>();
        try {
            while(rs.next()){
                int id = rs.getInt(1);
                String bigName = rs.getString(2);
                String bigText = rs.getString(3);
                Bigclass bigclass=new Bigclass();
                bigclass.setId(id);
                bigclass.setBigName(bigName);
                bigclass.setBigText(bigText);
                list.add(bigclass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.dbClose();
        }
        page.setPageData(list);
        return page;
    }


    @Override
    public boolean delByIdBigClass(int id) {
        Object obj[] = {id};
        String sql = "delete from bigclass where id = ?";
        int i = getUpdate(sql,obj);
        if(i>0){
            return  true;
        }
        return false;
    }

    @Override
    public boolean updateByIdBigClass(Object[] obj) {
        String sql="UPDATE bigclass SET bigName='?',bigText='?' where id=?";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addBigClass(Object[] obj) {
        String sql="INSERT INTO bigclass VALUES(NULL,?,?)";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }
}
