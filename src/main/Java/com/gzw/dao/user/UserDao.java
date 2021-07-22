package com.gzw.dao.user;

import com.gzw.pojo.Role;
import com.gzw.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {
    // 得到要登录的用户
    public User getLoginUser(Connection connection, String userCode) throws Exception;

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws Exception;

    // 根据用户名（userName）或者角色（userRole）获取用户总数
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception;

    // 获取用户列表
    public List<User> getUserList(Connection connection,String userName, int userRole,int currentPageNo, int pageSize) throws Exception;

    // 新增user
    public int add(Connection connection, User user)throws Exception;

    // 删除用户
    public int deleteUserById(Connection connection, Integer delId)throws Exception;

    // 获得user
    public User getUserById(Connection connection, String id)throws Exception;

    // 修改user
    public int modify(Connection connection, User user)throws Exception;

}
