package com.gzw.service.provider;

import com.gzw.dao.BaseBao;
import com.gzw.dao.bill.BillDao;
import com.gzw.dao.bill.BillDaoImpl;
import com.gzw.dao.provider.ProviderDao;
import com.gzw.dao.provider.ProviderDaoImpl;
import com.gzw.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public class ProviderServiceImpl implements ProviderService{
    private ProviderDao providerDao;
    private BillDao billDao;
    public ProviderServiceImpl(){
        providerDao = new ProviderDaoImpl();
        billDao = new BillDaoImpl();
    }
    @Override
    public boolean add(Provider provider) {
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseBao.getConnection();
            connection.setAutoCommit(false);//开启JDBC事务管理
            if(providerDao.add(connection,provider) > 0)
                flag = true;
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
    public List<Provider> getProviderList(String proName, String proCode) {
        Connection connection = null;
        List<Provider> providerList = null;
        try {
            connection = BaseBao.getConnection();
            providerList = providerDao.getProviderList(connection, proName,proCode);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return providerList;
    }

    @Override
    public int deleteProviderById(String delId) {
        Connection connection = null;
        int billCount = -1;
        try {
            connection = BaseBao.getConnection();
            connection.setAutoCommit(false);
            billCount = billDao.getBillCountByProviderId(connection,delId);
            if(billCount == 0){
                providerDao.deleteProviderById(connection, delId);
            }
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            billCount = -1;
            try {
                connection.rollback();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return billCount;
    }

    @Override
    public Provider getProviderById(String id) {
        Provider provider = null;
        Connection connection = null;
        try{
            connection = BaseBao.getConnection();
            provider = providerDao.getProviderById(connection, id);
        }catch (Exception e) {
            e.printStackTrace();
            provider = null;
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return provider;
    }

    @Override
    public boolean modify(Provider provider) {
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseBao.getConnection();
            if(providerDao.modify(connection,provider) > 0)
                flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseBao.closeResource(connection, null, null);
        }
        return flag;
    }
}
