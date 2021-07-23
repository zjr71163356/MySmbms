package com.gzw.service.good;

import com.gzw.dao.BaseBao;
import com.gzw.dao.good.goodDao;
import com.gzw.dao.good.goodDaoImpl;
import com.gzw.pojo.Good;
import com.gzw.pojo.GoodInCar;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class goodServiceImpl implements goodService{
    private goodDao goodDao;
    public goodServiceImpl()
    {
        goodDao=new goodDaoImpl();
    }
    public boolean add(Good good) {
        Connection connection =null;
        boolean flag=false;
        try {
            connection=BaseBao.getConnection();
            connection.setAutoCommit(false);
            if(goodDao.add(connection,good)>0)
                flag=true;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
               connection.rollback();
            }catch (Exception E)
            {
                E.printStackTrace();
            }finally{
                //在service层进行connection连接的关闭
                BaseBao.closeResource(connection, null, null);
            }
        }
            return flag;
    }

    @Override
    public boolean delete(Good good) {
        Connection connection =null;
                boolean flag=false;
        try {
            connection = BaseBao.getConnection();
            connection.setAutoCommit(false);
            if(goodDao.delete(connection,good)>0)
                flag=true;
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try{
                connection.rollback();
            }catch (Exception E)
            {
                E.printStackTrace();
            }finally{
                //在service层进行connection连接的关闭
                BaseBao.closeResource(connection, null, null);
            }
        }
        return flag;
    }

    @Override
    public Good getGoodByID(Integer goodID) {
        Connection connection=null;
        Good good=null;
        try{
            connection=BaseBao.getConnection();
            good=goodDao.getGoodByID(connection,goodID);

        }catch (Exception e ){
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return good;
    }


    @Override
    public List<GoodInCar> getGoodList(Integer userID) {
        Connection connection=null;
        List <GoodInCar> shoppingList=null;
        try{
            connection=BaseBao.getConnection();
           shoppingList=goodDao.getGoodList(connection,userID);


        }catch (Exception e ){
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return shoppingList;
    }
}
