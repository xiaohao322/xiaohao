package kgc.service.impl;

import kgc.dao.impl.AnnouncementDaoImpl;
import kgc.pojo.Announcement;
import kgc.pojo.Page;
import kgc.service.AnnouncementService;

public class AnnounementServiceImpl implements AnnouncementService {
    private static AnnounementServiceImpl ourInstance = new AnnounementServiceImpl();

    public static AnnounementServiceImpl getInstance() {
        return ourInstance;
    }

    private AnnounementServiceImpl() {
    }

    private AnnouncementDaoImpl annDao=AnnouncementDaoImpl.getInstance();
    @Override
    public Page<Announcement> queryAllAnnoun(int pageSize, int pageNumber) {
        return annDao.queryAllAnnoun(pageSize,pageNumber);
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
