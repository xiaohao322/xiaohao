package kgc.dao;

import kgc.pojo.*;

import java.util.List;


public interface SmallClassDao {
    List<Smallclass> showSClass();
    Page<EXSmallClass> queryAllSmallClass(int pageSize, int pageNumber);
    Page<EXSmallClass> queryUnionSmallClass(int pageSize, int pageNumber, String whereValue, String bigClassid);
    boolean delByIdSmallClass(int id);
    boolean updateByIdSmallClass(Object[] obj);
    boolean addSmallClass(Object[] obj);
    List<Discount> showDisc();
    List<Bigclass> showBIgClass();

}
