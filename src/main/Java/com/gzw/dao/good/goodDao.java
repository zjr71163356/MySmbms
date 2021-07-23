package com.gzw.dao.good;

import com.gzw.pojo.Good;

import java.sql.Connection;

public interface goodDao {
public int  add(Connection connection ,Good  good);

    int delete(Connection connection, Good good);
}
