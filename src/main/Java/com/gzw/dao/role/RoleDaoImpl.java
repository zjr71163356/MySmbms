package com.gzw.dao.role;

import com.gzw.dao.BaseBao;
import com.gzw.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

    //获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws Exception {

            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            ArrayList<Role> roleList = new ArrayList<Role>();

            if (connection != null){
                String sql = "select * from smbms_role";
                Object[] params = {};
                resultSet = BaseBao.execute(connection,preparedStatement, resultSet,sql,params);

                while (resultSet.next()){
                    Role _role = new Role();
                    _role.setId(resultSet.getInt("id"));
                    _role.setRoleCode(resultSet.getString("roleCode"));
                    _role.setRoleName(resultSet.getString("roleName"));
                    roleList.add(_role);
                }
                BaseBao.closeResource(null,preparedStatement,resultSet);

            }
            return roleList;
        }
}
