package com.gzw.service.provider;

import com.gzw.pojo.Provider;

import java.util.List;

public interface ProviderService {
    // 增加供应商
    public boolean add(Provider provider);


    // 通过供应商名称、编码获取供应商列表providerList
    public List<Provider> getProviderList(String proName, String proCode);

    // 通过providerId删除供应商
    public int deleteProviderById(String delId);


    // 通过providerId获取供应商
    public Provider getProviderById(String id);

    // 修改供应商信息
    public boolean modify(Provider provider);
}
