package kgc.dao;

import kgc.pojo.Bigclass;
import kgc.pojo.Superuser;

import java.util.List;

public interface SuperuserDao {
    //管理员登录
    Superuser AdmLogin (String admName,String admPwd);
    //管理员更改
    boolean UpdataAdmin(Superuser admin);


}
