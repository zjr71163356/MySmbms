package com.gzw.service.user;

import com.gzw.pojo.User;

import java.sql.Connection;

public interface UserService {
    // 用户登录
    public User login(String userCode, String password);

    // 根据用户id修改密码
    public boolean updatePwd(int id, String password);

}
