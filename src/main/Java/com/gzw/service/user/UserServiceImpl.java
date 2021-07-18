package com.gzw.service.user;

import com.gzw.dao.BaseBao;
import com.gzw.dao.user.UserDao;
import com.gzw.dao.user.UserDaoImpl;
import com.gzw.pojo.User;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl() {
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
            if (user.getUserPassword().equals(password)){
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseBao.closeResource(connection, null, null);
        }
        return null;
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

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("wen","123456");
        System.out.println(admin.getUserPassword());
    }

}
