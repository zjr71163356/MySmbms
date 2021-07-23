package com.gzw.dao.good;

import com.gzw.pojo.Good;
import com.gzw.pojo.GoodInCar;

import java.sql.Connection;
import java.util.List;

public class goodDaoImpl implements  goodDao{
    @Override
    public int add(Connection connection, Good good) {
        return 0;
    }

    @Override
    public int delete(Connection connection, Good good) {
        return 0;
    }

    @Override
    public List<GoodInCar> getGoodList(Connection connection, Integer userID) {
        return null;
    }

    @Override
    public Good getGoodByID(Connection connection, Integer goodID) {
        return null;
    }
}
