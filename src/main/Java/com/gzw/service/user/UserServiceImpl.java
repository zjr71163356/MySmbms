package com.gzw.service.user;

import com.gzw.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UserServiceImpl implements UserService {

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

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.updatePwd(1,"123");
    }
}
