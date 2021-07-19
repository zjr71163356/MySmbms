package com.gzw.service.role;

import com.gzw.pojo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class RoleServiceImplNew implements RoleService {

    private static final String resourcePath = "mybatis/mybatis-config.xml";

    @Override
    public List<Role> getRoleList() {
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
        // 将查询到的信息放到列表
        List<Role> roleList = sqlSession.selectList("mybatis.mapper.RoleMapper.selectAllRole");
        // 提交并关闭
        sqlSession.commit();
        sqlSession.close();
        return roleList;
    }
}
