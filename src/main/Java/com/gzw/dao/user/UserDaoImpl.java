package com.gzw.dao.user;

import com.gzw.dao.BaseBao;
import com.gzw.pojo.Role;
import com.gzw.pojo.User;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select count(1) as count from smbms_user u,smbms_role r where u.userRole = r.id");

            // 存放参数
            ArrayList<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if (userRole>0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }

            // 把list转换成数组
            Object[] params = list.toArray();
            System.out.println(sql.toString());

            resultSet = BaseBao.execute(connection, preparedStatement, resultSet, sql.toString(), params);
            if (resultSet.next()){
                // 从结果集中获取数量
                count = resultSet.getInt("count");
            }
            BaseBao.closeResource(null,preparedStatement,resultSet);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.userRole = r.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(userName)){
                sql.append(" and u.userName like ?");
                list.add("%"+userName+"%");
            }
            if(userRole > 0){
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo-1)*pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
            System.out.println("sql ----> " + sql.toString());
            rs = BaseBao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }
            BaseBao.closeResource(null, pstm, rs);
        }
        return userList;
    }

}
