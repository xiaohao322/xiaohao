package kgc.service.impl;



import kgc.dao.impl.SmallClassDaoImpl;
import kgc.pojo.*;
import kgc.service.SmallService;

import java.util.List;

public class SmallServiceImpl implements SmallService {
    private static SmallServiceImpl ourInstance = new SmallServiceImpl();

    public static SmallServiceImpl getInstance() {
        return ourInstance;
    }

    private SmallServiceImpl() {
    }

    private SmallClassDaoImpl classDao=SmallClassDaoImpl.getInstance();
    @Override
    public List<Smallclass> showSClass() {
        return classDao.showSClass();
    }
    @Override
    public List<Discount> showDisc() {
        return classDao.showDisc();
    }
    @Override
    public Page<EXSmallClass> queryAllSmallClass(int pageSize, int pageNumber) {
        return classDao.queryAllSmallClass(pageSize,pageNumber);
    }
    @Override
    public Page<EXSmallClass> queryUnionSmallClass(int pageSize, int pageNumber, String whereValue, String bigClassid) {
        return classDao.queryUnionSmallClass(pageSize,pageNumber,whereValue,bigClassid);
    }
    @Override
    public boolean delByIdSmallClass(int id) {
        return classDao.delByIdSmallClass(id);
    }
    @Override
    public boolean updateByIdSmallClass(Object[] obj) {
        return classDao.updateByIdSmallClass(obj);
    }
    @Override
    public boolean addSmallClass(Object[] obj) {
        return classDao.addSmallClass(obj);
    }
@Override
    public List<Bigclass> showBIgClass() {
        return classDao.showBIgClass();
    }
}
