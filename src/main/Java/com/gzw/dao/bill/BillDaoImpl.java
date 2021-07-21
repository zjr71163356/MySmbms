package com.gzw.dao.bill;

import com.gzw.dao.BaseBao;
import com.gzw.pojo.Bill;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao {
    @Override
    public int add(Connection connection, Bill bill) throws Exception {
        PreparedStatement preparedStatement = null;
        int count = 0;
        if(null != connection){
            String sql = "insert into smbms_bill (billCode,productName,productDesc,productUnit,productCount,totalPrice,isPayment,providerId,createdBy,creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getCreatedBy(),bill.getCreationDate()};
            count = BaseBao.execute(connection, sql, params, preparedStatement);
            BaseBao.closeResource(null, preparedStatement, null);
        }
        return count;
    }

    @Override
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Bill> billList = new ArrayList<Bill>();
        if (connection!=null){
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.proName providerName from smbms_bill b, smbms_provider p where b.providerId = p.id");
            List<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(bill.getProductName())){
                sql.append(" and productName like ?");
                list.add("%"+bill.getProductName()+"%");
            }
            if (bill.getProviderId()>0){
                sql.append(" and providerId = ?");
                list.add(bill.getProviderId());
            }
            if (bill.getIsPayment()>0){
                sql.append(" and isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            resultSet = BaseBao.execute(connection, preparedStatement, resultSet, sql.toString(),params);
            while (resultSet.next()){
                Bill _bill = new Bill();
                _bill.setId(resultSet.getInt("id"));
                _bill.setBillCode(resultSet.getString("billCode"));
                _bill.setProductName(resultSet.getString("productName"));
                _bill.setProductDesc(resultSet.getString("productDesc"));
                _bill.setProductUnit(resultSet.getString("productUnit"));
                _bill.setProductCount(resultSet.getBigDecimal("productCount"));
                _bill.setTotalPrice(resultSet.getBigDecimal("totalPrice"));
                _bill.setIsPayment(resultSet.getInt("isPayment"));
                _bill.setProviderId(resultSet.getInt("providerId"));
                _bill.setProviderName(resultSet.getString("providerName"));
                _bill.setCreationDate(resultSet.getTimestamp("creationDate"));
                _bill.setCreatedBy(resultSet.getInt("createdBy"));
                billList.add(_bill);
            }
            BaseBao.closeResource(null,preparedStatement,resultSet);
        }
        return billList;
    }

    @Override
    public int deleteBillById(Connection connection, String delId) throws Exception {
        PreparedStatement preparedStatement = null;
        int count = 0;
        if (connection!=null){
            String sql = "delete from smbms_bill where id = ?";
            Object[] params = {delId};
            count = BaseBao.execute(connection,sql,params,preparedStatement);
            BaseBao.closeResource(null,preparedStatement,null);
        }
        return count;
    }

    @Override
    public Bill getBillById(Connection connection, String id) throws Exception {
        Bill bill = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        if(null != connection){
            String sql = "select b.*,p.proName as providerName from smbms_bill b, smbms_provider p where b.providerId = p.id and b.id=?";
            Object[] params = {id};
            rs = BaseBao.execute(connection, preparedStatement, rs, sql, params);
            if(rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseBao.closeResource(null, preparedStatement, rs);
        }
        return bill;
    }

    @Override
    public int modify(Connection connection, Bill bill) throws Exception {
        PreparedStatement preparedStatement = null;
        int count = 0;
        if (connection!=null){
            String sql = "update smbms_bill set productName=?," +
                    "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
                    "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {
                    bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),
                    bill.getTotalPrice(),bill.getIsPayment(),bill.getProviderId(),
                    bill.getModifyBy(),bill.getModifyDate(),bill.getId()
            };
            count = BaseBao.execute(connection,sql,params,preparedStatement);
            BaseBao.closeResource(null,preparedStatement,null);
        }
        return count;
    }

    @Override
    public int getBillCountByProviderId(Connection connection, String providerId) throws Exception {
        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        if (connection!=null){
            String sql = "select count(1) billCount from smbms_bill where providerId = ?";
            Object[] params = {providerId};
            resultSet = BaseBao.execute(connection,preparedStatement,resultSet,sql,params);
            while (resultSet.next()){
                count = resultSet.getInt("billCount");
            }
            BaseBao.closeResource(null,preparedStatement,resultSet);
        }
        return count;
    }
}
