package kgc.service.impl;

import kgc.dao.impl.GoodsImplDao;
import kgc.pojo.EXGoods;
import kgc.pojo.Goods;
import kgc.pojo.Page;
import kgc.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
    private static GoodsServiceImpl ourInstance = new GoodsServiceImpl();

    public static GoodsServiceImpl getInstance() {
        return ourInstance;
    }

    private GoodsServiceImpl() {
    }

    private GoodsImplDao goodDao=GoodsImplDao.getInstance();
    @Override
    public Page<EXGoods> queryAllGoods(int pageSize, int pageNumber) {
        return goodDao.queryAllGoods(pageSize,pageNumber);
    }

    @Override
    public Page<EXGoods> queryUnionGoods(int pageSize, int pageNumber, String whereValue, String smallClassid, String d_id) {
        return goodDao.queryUnionGoods(pageSize,pageNumber,whereValue,smallClassid,d_id);
    }

    @Override
    public boolean delByIdGoods(int id) {
        return goodDao.delByIdGoods(id);
    }

    @Override
    public boolean updateByIdGoods(Object[] obj) {
        return goodDao.updateByIdGoods(obj);
    }

    @Override
    public boolean addGoods(Object[] obj) {
        return goodDao.addGoods(obj);
    }

    @Override
    public Goods getgoodById(String id) {
        return goodDao.getgoodById(id);
    }
}
