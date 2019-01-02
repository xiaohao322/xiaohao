package kgc.dao;

import kgc.pojo.Bigclass;
import kgc.pojo.Page;

public interface BigclassDao {
    //查询大分类
    Page<Bigclass> queryAllBigClass(int pageSize, int pageNumber);
    //删除
    boolean delByIdBigClass(int id);
    //修改
    boolean updateByIdBigClass(Object[] obj);
    //添加
    boolean addBigClass(Object[] obj);
}
