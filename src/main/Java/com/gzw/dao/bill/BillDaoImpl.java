package com.gzw.dao.bill;

import com.gzw.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public class BillDaoImpl implements BillDao {
    @Override
    public int add(Connection connection, Bill bill) throws Exception {
        return 0;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
        return null;
    }

    @Override
    public int deleteBillById(Connection connection, String delId) throws Exception {
        return 0;
    }

    @Override
    public Bill getBillById(Connection connection, String id) throws Exception {
        return null;
    }

    @Override
    public int modify(Connection connection, Bill bill) throws Exception {
        return 0;
    }

    @Override
    public int getBillCountByProviderId(Connection connection, String providerId) throws Exception {
        return 0;
    }
}
