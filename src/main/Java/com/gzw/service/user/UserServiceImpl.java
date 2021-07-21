package com.gzw.service.user;

import com.gzw.dao.BaseBao;
import com.gzw.dao.user.UserDaoImpl;
import com.gzw.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoImpl userDao = new UserDaoImpl();
    private static final String resourcePath = "mybatis/mybatis-config.xml";

    @Override
    public User login(String userCode, String password) {
        // 读取配置文件mybatis/mybatis-config.xml
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建用于查询的User对象
        User queryUser = new User();
        queryUser.setUserCode(userCode);
        queryUser.setUserPassword(password);
        // 执行sql
        User user = sqlSession.selectOne("mybatis.mapper.UserMapper.selectUser",queryUser);
        // 提交并关闭sqlSession
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    @Override
    public boolean updatePwd(int id, String password) {
        // 读取配置文件mybatis/mybatis-config.xml
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resourcePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建用于查询的User对象
        User queryUser = new User();
        queryUser.setId(id);
        queryUser.setUserPassword(password);
        // 执行sql
        try {
            sqlSession.update("mybatis.mapper.UserMapper.updateUser",queryUser);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
        sqlSession.commit();
        sqlSession.close();
        return true;
    }

    @Override
    public int getUserCount(String userName, int userRole) {

        Connection connection = null;
        int userCount = 0;
        try {
            connection = BaseBao.getConnection();
            userCount = userDao.getUserCount(connection, userName, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseBao.closeResource(connection,null,null);
        }
        return userCount;
    }

    @Override
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        System.out.println("queryUserName ---- > " + queryUserName);
        System.out.println("queryUserRole ---- > " + queryUserRole);
        System.out.println("currentPageNo ---- > " + currentPageNo);
        System.out.println("pageSize ---- > " + pageSize);
        try {
            connection = BaseBao.getConnection();
            userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return userList;
    }

    @Override
    public boolean checkUserCode(String userCode) {
        Connection connection=null;
        ResultSet resultSet=null;
        PreparedStatement preparedStatement=null;
        Object [] params={userCode};
        String sql="select id from smbms_user where userCode =?";
        try {
            connection = BaseBao.getConnection();
            resultSet=BaseBao.execute(connection,preparedStatement,resultSet,sql,params);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            BaseBao.closeResource(connection,null,null);
        }
        if(resultSet==null)
            return false;
        else return true;

    }

    @Override
    public boolean registUser(String userCode, String userPassword,Object[]params) {
        Connection connection=null;
        int flag=0;
        PreparedStatement preparedStatement=null;

        String sql="insert into smbms_user values(null,"+userCode+",?,"+userPassword+",?,?,?,?,?,?,?,null,null)";
        try {
            connection = BaseBao.getConnection();
            flag=BaseBao.execute(connection,sql,params,preparedStatement);
        }catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            BaseBao.closeResource(connection,null,null);
        }
        if(flag==0)
            return false;
        else return true;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.updatePwd(1,"123");
    }

    @Test
    public void test(){
        UserServiceImpl userService = new UserServiceImpl();
        int count = userService.getUserCount(null,1);
        System.out.println(count);
    }
}
