package com.gzw.service.user;

import com.gzw.dao.BaseBao;
import com.gzw.dao.user.UserDao;
import com.gzw.dao.user.UserDaoImpl;
import com.gzw.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

@Deprecated
public class UserServiceImplOld implements UserService {

    private UserDao userDao;

    public UserServiceImplOld() {
        this.userDao = new UserDaoImpl();
    }

    // 业务层都会调用dao层，使用我们要引入模板
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;


        try {
            connection = BaseBao.getConnection();
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseBao.closeResource(connection, null, null);
        }
        return user;
    }


    // 根据用户的id修改密码
    @Override
    public boolean updatePwd(int id, String password) {
        Connection connection = null;
        boolean flag = false;

        try {
            connection = BaseBao.getConnection();
            if (userDao.updatePwd(connection,id,password)>0){
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseBao.closeResource(connection,null,null);
        }

        return flag;
    }

    @Override
    public int getUserCount(String userName, int userRole) {
        return 0;
    }

    @Override
    public boolean checkUserCode(String userCode) {
        return false;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        return null;
    }

    @Override
    public boolean registUser(String userCode, String userPassword, Object[] params) {
        return false;
    }

}
