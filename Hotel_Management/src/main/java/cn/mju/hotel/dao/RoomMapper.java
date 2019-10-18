package cn.mju.hotel.dao;

import cn.mju.hotel.pojo.Room;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKeyWithBLOBs(Room record);

    int updateByPrimaryKey(Room record);
}