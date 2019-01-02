package kgc.service.impl;

import kgc.dao.impl.BigclassImplDao;
import kgc.pojo.Bigclass;
import kgc.pojo.Page;
import kgc.service.BigclassService;

public class BigclassServiceImpl implements BigclassService {
    private static BigclassServiceImpl ourInstance = new BigclassServiceImpl();

    public static BigclassServiceImpl getInstance() {
        return ourInstance;
    }

    private BigclassServiceImpl() {
    }
    private BigclassImplDao bigDao=BigclassImplDao.getInstance();
    @Override
    public Page<Bigclass> queryAllBigClass(int pageSize, int pageNumber) {
        return bigDao.queryAllBigClass(pageSize,pageNumber);
    }

    @Override
    public boolean delByIdBigClass(int id) {
        return bigDao.delByIdBigClass(id);
    }

    @Override
    public boolean updateByIdBigClass(Object[] obj) {
        return bigDao.updateByIdBigClass(obj);
    }

    @Override
    public boolean addBigClass(Object[] obj) {
        return bigDao.addBigClass(obj);
    }
}
