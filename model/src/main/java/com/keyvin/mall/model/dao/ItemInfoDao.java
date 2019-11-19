package com.keyvin.mall.model.dao;


import com.keyvin.mall.common.entity.ItemInfo;

import java.util.List;

public interface ItemInfoDao {
    int delete(Integer id);

    int insert(ItemInfo record);

    ItemInfo findById(Integer id);

    List<ItemInfo> selectAll();

    int update(ItemInfo record);



}