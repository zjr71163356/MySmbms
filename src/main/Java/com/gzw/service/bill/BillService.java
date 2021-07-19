package com.gzw.service.bill;

import com.gzw.pojo.Bill;

import java.util.List;

public interface BillService {
    // 增加订单
    public boolean add(Bill bill);


    // 获取订单列表
    public List<Bill> getBillList(Bill bill);

    // 通过billId删除bill
    public boolean deleteBillById(String delId);


    // 通过billId获取bill
    public Bill getBillById(String id);

    // 修改订单信息
    public boolean modify(Bill bill);
}
