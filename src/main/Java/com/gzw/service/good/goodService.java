package com.gzw.service.good;

import com.gzw.pojo.Good;

import java.util.List;

public interface goodService {
    //上架商品到数据库
    public boolean add(Good good);
    //删除数据库内上架的商品
    public boolean delete(Good good);
    public  Good query(Integer goodID);
    //得到用户的购物车清单
    //通过userId 查找smbms_address表中含有该userID的行，去查找其productId,查smbms_good表
    //需要smbms_address :addressDesc,productNumber，creationDate
    // smbms_good :goodName，goodPrice
    //Object[] 是集合以上五个字段的数组，指的是用户购物车里单个商品信息

    public List<Object[]> getGoodList(Integer userID);


}
