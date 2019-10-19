package cn.mju.hotel.dao;

import cn.mju.hotel.pojo.Check;

public interface CheckMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKeyWithBLOBs(Check record);

    int updateByPrimaryKey(Check record);
}