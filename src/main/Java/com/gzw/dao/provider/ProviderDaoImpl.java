package com.gzw.dao.provider;

import com.gzw.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public class ProviderDaoImpl implements ProviderDao {
    @Override
    public int add(Connection connection, Provider provider) throws Exception {
        return 0;
    }

    @Override
    public List<Provider> getProviderList(Connection connection, String proName, String proCode) throws Exception {
        return null;
    }

    @Override
    public int deleteProviderById(Connection connection, String delId) throws Exception {
        return 0;
    }

    @Override
    public Provider getProviderById(Connection connection, String id) throws Exception {
        return null;
    }

    @Override
    public int modify(Connection connection, Provider provider) throws Exception {
        return 0;
    }
}
