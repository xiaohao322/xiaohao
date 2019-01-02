package kgc.dao;

import kgc.pojo.Announcement;
import kgc.pojo.Page;

public interface AnnouncementDao {
    //查询公告
    Page<Announcement> queryAllAnnoun(int pageSize, int pageNumber);
    //删除
    boolean delByIdAnnoun(int id);
    //修改
    boolean updateByIdAnnoun(Object[] obj);
    //添加
    boolean addAnnoun(Object[] obj);
}
