package kgc.dao.impl;

import kgc.core.util.PageUtil;
import kgc.dao.AnnouncementDao;
import kgc.dao.BaseDao;
import kgc.pojo.Announcement;
import kgc.pojo.Page;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AnnouncementDaoImpl implements AnnouncementDao {
    private static AnnouncementDaoImpl ourInstance = new AnnouncementDaoImpl();

    public static AnnouncementDaoImpl getInstance() {
        return ourInstance;
    }

    private AnnouncementDaoImpl() {

    }

    @Override
    public Page<Announcement> queryAllAnnoun(int pageSize, int pageNumber) {
        Page<Announcement> page = new Page<Announcement>();
        page.setPageNumber(pageNumber);
        page.setPageSize(pageSize);
        String sql = "select count(1) from announcement";
        page.setTotalRecode(PageUtil.getTotalRecode(sql, null));

        List<Announcement> annlist = new ArrayList<Announcement>();
        String sql2 = "select * from announcement";
        ResultSet res = PageUtil.getPageDate(sql2, pageSize, pageNumber, null);
        Announcement ann = null;
        try {
            while (res.next()) {
                ann = new Announcement(res.getLong(1),res.getString(2),res.getString(3),res.getDate(4));
                annlist.add(ann);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.dbClose();
        }
        page.setPageData(annlist);
        return page;
    }

    @Override
    public boolean delByIdAnnoun(int id) {
        return false;
    }

    @Override
    public boolean updateByIdAnnoun(Object[] obj) {
        return false;
    }

    @Override
    public boolean addAnnoun(Object[] obj) {
        return false;
    }
}
