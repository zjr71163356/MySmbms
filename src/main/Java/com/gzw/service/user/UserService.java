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

    // 根据条件查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    // 获得user
    public User selectUserCodeExist(String userCode);

    // 根据userId删除user
    public boolean deleteUserById(Integer delId);

    // 根据userId获得user
    public User getUserById(String id);

    // 修改user
    public boolean modify(User user);

    // 新增user
    public boolean add(User user);
}
