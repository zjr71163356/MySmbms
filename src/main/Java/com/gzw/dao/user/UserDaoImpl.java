package com.gzw.dao.user;

import com.gzw.dao.BaseBao;
import com.gzw.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl implements UserDao {

    @Override
    public User getLoginUser(Connection connection, String userCode) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        if (connection!=null){
            String sql = "select * from smbms_user where userCode=?";
            Object[] params = {userCode};

            resultSet = BaseBao.execute(connection,preparedStatement,resultSet,sql,params);
            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
            }
            BaseBao.closeResource(null,preparedStatement,resultSet);
        }
        return user;
    }

    // 修改用户密码
    @Override
    public int updatePwd(Connection connection, int id, String password) throws Exception {

        // 执行sql语句的对象
        PreparedStatement preparedStatement = null;
        int execute = 0;

        if (connection!=null){
            String sql = "update smbms_user set userPassword=?where id=?";
            Object params[] = {password,id};
            execute = BaseBao.execute(connection, sql, params, preparedStatement);
            BaseBao.closeResource(null,preparedStatement,null);
        }
        return execute;
    }
}
