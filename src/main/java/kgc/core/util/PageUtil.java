package kgc.core.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import static kgc.dao.BaseDao.dbClose;
import static kgc.dao.BaseDao.getQuery;

/**
 * @ClassName kgc.core.util
 * @Author xiaohao
 * @Date 2018/12/21 13:49
 * @Version 1.0
 **/
public class PageUtil {
    public static int getTotalRecode(String sql,Object []parameter){
        int totalRecode = 0;
        ResultSet rs  = getQuery(sql,parameter);
        try {
            if(rs.next()){
                totalRecode = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            dbClose();
        }
        return totalRecode;
    }

    public static ResultSet getPageDate(String sql,int pageSize,int pageNumber,Object []parameter){
        int index =  (pageNumber-1)*pageSize;
        sql = sql+" limit "+index+","+pageSize;
        System.out.println(sql);
        ResultSet rs = getQuery(sql,parameter);
        return  rs;
    }
}

