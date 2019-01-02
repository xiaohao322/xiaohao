package kgc.service;


import kgc.pojo.*;

import java.util.List;

public interface SmallService {
    List<Smallclass> showSClass();
   List<Discount> showDisc();
    Page<EXSmallClass> queryAllSmallClass(int pageSize, int pageNumber);
    Page<EXSmallClass> queryUnionSmallClass(int pageSize, int pageNumber, String whereValue, String bigClassid);
    boolean delByIdSmallClass(int id);
    boolean updateByIdSmallClass(Object[] obj);
    boolean addSmallClass(Object[] obj);
    List<Bigclass> showBIgClass();
}
