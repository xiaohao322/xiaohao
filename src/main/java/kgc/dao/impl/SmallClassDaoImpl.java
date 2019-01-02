package kgc.dao.impl;


import kgc.core.util.PageUtil;
import kgc.dao.BaseDao;
import kgc.dao.SmallClassDao;
import kgc.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName com.vv.dao.impl
 * @Author xiaohongwei
 * @Date 2019/1/1 18:33
 * @Version 1.0
 **/

public class SmallClassDaoImpl extends BaseDao implements SmallClassDao {

    private static SmallClassDaoImpl ourInstance = new SmallClassDaoImpl();

    public static SmallClassDaoImpl getInstance() {
        return ourInstance;
    }

    private SmallClassDaoImpl() {
    }

    /**
     *
     * @param rs
     * @return
     */
    private static EXSmallClass initEXSmallClass(ResultSet rs){
        try {
            while(rs.next()){
                int id = rs.getInt(1);
                String smallName = rs.getString(2);
                String smallBigName = rs.getString(3);
                String smallText = rs.getString(4);
                EXSmallClass E_sc = new EXSmallClass();
                E_sc.setId(id);
                E_sc.setSmallName(smallName);
                Bigclass bigclass=new Bigclass();
                bigclass.setBigName(smallBigName);
                E_sc.setBigclass(bigclass);
                E_sc.setSmallText(smallText);
               return  E_sc;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.dbClose();
        }
        return null;
    }

    /**
     * 展示小分类
     * @return
     */
    @Override
    public List<Smallclass> showSClass() {
        String sql ="select * from smallClass";
        ResultSet res=getQuery(sql,null);
        List<Smallclass> list=new ArrayList<Smallclass>();
        try {
            while (res.next()){
                Smallclass smallclass=new Smallclass();
                smallclass.setId(res.getInt(1));
                smallclass.setSmallName(res.getString(2));
                smallclass.setSmallBigId(res.getInt(3));
                smallclass.setSmallText(res.getString(4));
                list.add(smallclass);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询所有小分类
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @Override
    public Page<EXSmallClass> queryAllSmallClass(int pageSize, int pageNumber) {

        Page<EXSmallClass> page = new Page<EXSmallClass>();
        //2.为Page的成员变量赋值
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        String sql1 = "select count(1) from smallClass s ,bigClass b where s.smallBigId = b.id ";
        String sql2 = "SELECT s.id,s.smallName ,b.bigName,s.smallText FROM smallclass s,bigclass b WHERE s.smallBigId=b.id ";
        page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
        ResultSet rs =PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
        List<EXSmallClass> list = new ArrayList<EXSmallClass>();
        while (true){
        EXSmallClass sc = initEXSmallClass(rs);
        if (sc==null) break;
        list.add(sc);

        }
        page.setPageData(list);
        return page;
    }

    /**
     * 小分类级联查询
     * @param pageSize
     * @param pageNumber
     * @param whereValue
     * @return
     */
    @Override
    public Page<EXSmallClass> queryUnionSmallClass(int pageSize, int pageNumber, String whereValue,String bigClassid) {
        //1.实例化Page对象
        Page<EXSmallClass> page = new Page<EXSmallClass>();
        //2.为Page的成员变量赋值
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        ResultSet res = null;

        List<EXSmallClass> exSmallClassList=new ArrayList<EXSmallClass>();

        if (whereValue!=null&&bigClassid!=null){
            int wherevarSize=whereValue.length();
            int bigclassIdSize=bigClassid.length();
            if (wherevarSize==0&&bigclassIdSize==0){
                //查询所有
                String sql1="select count(1) from smallClass s ,bigClass b where s.smallBigId = b.id ";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
                String sql2 = "SELECT s.id,s.smallName ,b.bigName,s.smallText FROM smallclass s,bigclass b WHERE s.smallBigId=b.id ";
                res=PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
            } else if (wherevarSize>0&&bigclassIdSize==0) {
                //小分类名称查询
                String sql1="SELECT count(1) FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and s.smallName LIKE ? ";
                Object [] parameter={"%"+whereValue+"%"};
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2 = "SELECT s.id,s.smallName ,b.bigName,s.smallText FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and s.smallName LIKE ?";
                res=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (wherevarSize==0&&bigclassIdSize>0){
                //大分类id查询
                String sql1="SELECT count(1) FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and b.id=?";
                Object [] parameter={bigClassid};
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2 = "SELECT s.id,s.smallName ,b.bigName,s.smallText FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and b.id=?";
                res=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (wherevarSize>0&&bigclassIdSize>0){
                String sql1="SELECT count(1) FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and b.id=? and s.smallName LIKE ?";
                Object [] parameter={bigClassid,"%"+whereValue+"%"};
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2 = "SELECT s.id,s.smallName ,b.bigName,s.smallText FROM smallclass s,bigclass b WHERE s.smallBigId=b.id and b.id=? and s.smallName LIKE ?";
                res=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }

        }
        try {
            while(res.next()){
                int id = res.getInt(1);
                String smallName = res.getString(2);
                String smallBigName = res.getString(3);
                String smallText = res.getString(4);
                EXSmallClass E_sc = new EXSmallClass();
                E_sc.setId(id);
                E_sc.setSmallName(smallName);
                Bigclass bigclass=new Bigclass();
                bigclass.setBigName(smallBigName);
                E_sc.setBigclass(bigclass);
                E_sc.setSmallText(smallText);
               exSmallClassList.add(E_sc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            BaseDao.dbClose();
        }
        page.setPageData(exSmallClassList);
        return page;
    }

    /**
     * 根据id删除小分类
     * @param id
     * @return
     */
    @Override
    public boolean delByIdSmallClass(int id) {
        Object obj[] = {id};
        String sql = "delete from smallClass where id = ?";
        int i = getUpdate(sql,obj);
        if(i>0){
            return  true;
        }
        return false;
    }

    /**
     * 根据id修改
     * @param obj {小分类名称，所属大分类Id，小分类描述，小分类id}
     * @return
     */
    @Override
    public boolean updateByIdSmallClass(Object[] obj) {
        String sql="UPDATE smallclass SET smallName='?',smallBigId=?,smallText='?' where id=?";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }
    /**
     * 添加小分类
     * @param obj {小分类名称，所属大分类Id，小分类描述}
     * @return
     */
    @Override
    public boolean addSmallClass(Object[] obj) {
        String sql="INSERT INTO smallclass VALUES(NULL,?,?,?)";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }

    /**
     * 展示折扣
     * @return
     */
    @Override
    public List<Discount> showDisc() {
        String sql ="select * from discount";
        ResultSet res=getQuery(sql,null);
        List<Discount> dlist=new ArrayList<Discount>();
        try {
            while (res.next()){
               Discount discount=new Discount();
                discount.setId(res.getInt(1));
                discount.setDiscRate(res.getDouble(2));
                dlist.add(discount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dlist;

    }
    @Override
    public  List<Bigclass> showBIgClass(){
        String sql ="select * from bigClass";
        ResultSet res=getQuery(sql,null);
        List<Bigclass> bigclasses=new ArrayList<Bigclass>();
        try {
            while (res.next()){
                Bigclass bigclass=new Bigclass();
                bigclass.setId(res.getInt(1));
                bigclass.setBigName(res.getString(2));
                bigclass.setBigText(res.getString(3));
                bigclasses.add(bigclass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bigclasses;
    }

}
