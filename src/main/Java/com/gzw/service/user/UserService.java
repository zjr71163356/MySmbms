package com.gzw.service.user;

import com.gzw.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserService {
    // 用户登录
    public User login(String userCode, String password);

    // 根据用户id修改密码
    public boolean updatePwd(int id, String password);

    // 根据用户名或者角色查询用户的数量
    public int getUserCount(String userName, int userRole);
    public boolean checkUserCode(String userCode);
    // 根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);
    public boolean registUser(String userCode, String userPassword,Object[]params);
}
