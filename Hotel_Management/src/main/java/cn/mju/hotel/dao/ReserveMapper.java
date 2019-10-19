package cn.mju.hotel.dao;

import cn.mju.hotel.pojo.Reserve;

public interface ReserveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reserve record);

    int insertSelective(Reserve record);

    Reserve selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reserve record);

    int updateByPrimaryKeyWithBLOBs(Reserve record);

    int updateByPrimaryKey(Reserve record);
}