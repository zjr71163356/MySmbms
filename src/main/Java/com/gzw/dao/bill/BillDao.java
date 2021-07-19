package com.gzw.dao.bill;

import com.gzw.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public interface BillDao {

    // 增加订单
    public int add(Connection connection, Bill bill)throws Exception;


    // 获取订单
    public List<Bill> getBillList(Connection connection, Bill bill)throws Exception;

    // 通过billId删除订单
    public int deleteBillById(Connection connection, String delId)throws Exception;


    //通过billId获取订单
    public Bill getBillById(Connection connection, String id)throws Exception;

    // 修改订单信息
    public int modify(Connection connection, Bill bill)throws Exception;

    // 根据billId获取订单数量
    public int getBillCountByProviderId(Connection connection, String providerId)throws Exception;
}
