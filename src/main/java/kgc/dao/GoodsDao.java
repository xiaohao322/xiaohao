package kgc.dao;

import kgc.pojo.EXGoods;
import kgc.pojo.Goods;
import kgc.pojo.Page;

public interface GoodsDao {
    //查询商品
    Page<EXGoods> queryAllGoods(int pageSize, int pageNumber);
    Page<EXGoods> queryUnionGoods(int pageSize, int pageNumber, String whereValue, String smallClassid, String d_id);
    boolean delByIdGoods(int id);
    boolean updateByIdGoods(Object[] obj);
    boolean addGoods(Object[] obj);
    Goods getgoodById(String id);
}
