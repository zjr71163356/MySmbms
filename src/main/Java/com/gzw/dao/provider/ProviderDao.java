package com.gzw.dao.provider;

import com.gzw.pojo.Provider;

import java.sql.Connection;
import java.util.List;

public interface ProviderDao {
    // 增加供应商
    public int add(Connection connection, Provider provider)throws Exception;


    // 通过供应商名称、编码获取供应商列表providerList
    public List<Provider> getProviderList(Connection connection, String proName, String proCode)throws Exception;

    // 通过providerId删除供应商
    public int deleteProviderById(Connection connection, String delId)throws Exception;


    // 通过providerId获取供应商
    public Provider getProviderById(Connection connection, String id)throws Exception;

    // 修改供应商信息
    public int modify(Connection connection, Provider provider)throws Exception;
}
