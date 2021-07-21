package com.gzw.service.bill;

import com.gzw.dao.BaseBao;
import com.gzw.dao.bill.BillDao;
import com.gzw.dao.bill.BillDaoImpl;
import com.gzw.pojo.Bill;

import java.sql.Connection;
import java.util.List;

public class BillServiceImpl  implements BillService{


    private BillDao billDao;
    public BillServiceImpl(){
        billDao = new BillDaoImpl();
    }
    @Override
    public boolean add(Bill bill) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseBao.getConnection();
            connection.setAutoCommit(false);//开启JDBC事务管理
            if(billDao.add(connection,bill) > 0) {
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }finally{
            //在service层进行connection连接的关闭
            BaseBao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public List<Bill> getBillList(Bill bill) {
        Connection connection = null;
        List<Bill> billList = null;

        try {
            connection = BaseBao.getConnection();
            billList = billDao.getBillList(connection, bill);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return billList;
    }

    @Override
    public boolean deleteBillById(String delId) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseBao.getConnection();
            if(billDao.deleteBillById(connection, delId) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Bill getBillById(String id) {
        Bill bill = null;
        Connection connection = null;
        try{
            connection = BaseBao.getConnection();
            bill = billDao.getBillById(connection, id);
        }catch (Exception e) {
            e.printStackTrace();
            bill = null;
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return bill;
    }

    @Override
    public boolean modify(Bill bill) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseBao.getConnection();
            if(billDao.modify(connection,bill) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return flag;
    }

}
