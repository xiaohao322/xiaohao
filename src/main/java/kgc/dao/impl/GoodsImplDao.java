package kgc.dao.impl;

import kgc.core.util.PageUtil;
import kgc.dao.GoodsDao;
import kgc.pojo.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static kgc.dao.BaseDao.dbClose;
import static kgc.dao.BaseDao.getQuery;
import static kgc.dao.BaseDao.getUpdate;

public class GoodsImplDao implements GoodsDao {
    private static GoodsImplDao ourInstance = new GoodsImplDao();

    public static GoodsImplDao getInstance() {
        return ourInstance;
    }

    private GoodsImplDao() {
    }

    @Override
    public Page<EXGoods> queryAllGoods(int pageSize, int pageNumber) {
        return null;
    }
    @Override
    public Goods getgoodById(String id) {
        Goods goods=null;
        String sql="select * goods where id =?";
        Object [] obj={id};
        ResultSet rs=getQuery(sql,obj);
        try {
            if (rs.next()){
                goods=new Goods(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getDouble(4),rs.getInt(5),
                        rs.getString(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return goods;
    }

    //商品级连查询
    @Override
    public Page<EXGoods> queryUnionGoods(int pageSize, int pageNumber, String whereValue, String smallClassid, String d_id) {

        Page<EXGoods> page = new Page<EXGoods>();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);

        ResultSet rs=null;
        List<EXGoods> ExList = new ArrayList<EXGoods>();
        if (whereValue!=null&&smallClassid!=null&&d_id!=null){
            int valSize=whereValue.length();
            int sidSize=smallClassid.length();
            int dSize=d_id.length();
            if (valSize==0&&sidSize==0&&dSize==0){
                //查询所有
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,null));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,null);
            }else if (valSize>0&&sidSize==0&&dSize==0){
                //商品名称模糊查询
                Object [] parameter={"%"+whereValue+"%"};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName LIKE ?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName LIKE ?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize==0&&sidSize>0&&dSize==0){
                //小分类查询
                Object [] parameter={smallClassid};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsSmalId=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsSmalId=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize==0&&sidSize==0&&dSize>0){
                //折扣id查询
                Object [] parameter={d_id};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and d.id=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and d.id=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize>0&&sidSize>0&&dSize==0){
                //商品名称 和 小分类查询
                Object [] parameter={"%"+whereValue+"%",smallClassid};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and g.goodsSmalId=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and g.goodsSmalId=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize>0&&sidSize==0&&dSize>0){
                //商品名称 和折扣id 查询
                Object [] parameter={"%"+whereValue+"%",d_id};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and d.id=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and d.id=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize==0&&sidSize>0&&dSize>0){
                //小分类 和折扣id 查询
                Object [] parameter={smallClassid,d_id};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsSmalId=? and d.id=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsSmalId=? and d.id=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }else if (valSize>0&&sidSize>0&&dSize>0){
                //名称模糊、 小分类 和折扣id 查询
                Object [] parameter={"%"+whereValue+"%",smallClassid,d_id};
                String sql1="SELECT COUNT(1) FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and g.goodsSmalId=? and d.id=?";
                page.setTotalRecode(PageUtil.getTotalRecode(sql1,parameter));
                String sql2="SELECT g.id,g.goodsName,s.smallName,g.goodsMoney,g.goodsNumber,g.goodsImage,g.goodsCarriage,d.discRate FROM goods AS g,smallclass as s,discount as d WHERE g.goodsSmalId=s.id and g.goodsDiscId=d.id " +
                        "and g.goodsName like ? and g.goodsSmalId=? and d.id=?";
                rs=PageUtil.getPageDate(sql2,pageSize,pageNumber,parameter);
            }
        }

        try {
            while(rs.next()){
                EXGoods exGoods=new EXGoods();
                exGoods.setId(rs.getInt(1));
                exGoods.setGoodsName(rs.getString(2));
                Smallclass smallclass=new Smallclass();
                smallclass.setSmallName(rs.getString(3));
                exGoods.setSmallclass(smallclass);
                exGoods.setGoodsMoney(rs.getDouble(4));
                exGoods.setGoodsNumber(rs.getInt(5));
                exGoods.setGoodsImage(rs.getString(6));
                exGoods.setGoodsCarriage(rs.getDouble(7));
                Discount discount=new Discount();
                discount.setDiscRate(rs.getDouble(8));
                exGoods.setDiscount(discount);
                ExList.add(exGoods);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        page.setPageData(ExList);
        return page;
    }

    //删除
    @Override
    public boolean delByIdGoods(int id) {
        Object obj[] = {id};
        String sql = "delete from goods where id = ?";
        int i = getUpdate(sql,obj);
        if(i>0){
            return  true;
        }
        return false;
    }

    //修改
    @Override
    public boolean updateByIdGoods(Object[] obj) {
        String sql="UPDATE goods SET goodsName=?,goodsSmalId=?,goodsMoney=?,goodsNumber=?,goodsCarriage=?,goodsDiscId=? where id=?";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }

    //添加
    @Override
    public boolean addGoods(Object[] obj) {
        String sql="INSERT INTO goods VALUES (null,?,?,?,?,?,?,0,?);";
        int i=getUpdate(sql,obj);
        if (i>0){
            return true;
        }
        return false;
    }
}
