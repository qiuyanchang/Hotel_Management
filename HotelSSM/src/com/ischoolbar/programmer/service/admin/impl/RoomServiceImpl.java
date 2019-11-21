package com.ischoolbar.programmer.service.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.RoomDao;
import com.ischoolbar.programmer.entity.admin.Room;
import com.ischoolbar.programmer.service.admin.RoomService;
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDao roomDao;
	
	public int add(Room room) {
		// TODO Auto-generated method stub
		return roomDao.add(room);
	}

	public int edit(Room room) {
		// TODO Auto-generated method stub
		return roomDao.edit(room);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return roomDao.delete(id);
	}

	public List<Room> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roomDao.findList(queryMap);
	}

	public List<Room> findAll() {
		// TODO Auto-generated method stub
		return roomDao.findAll();
	}

	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roomDao.getTotal(queryMap);
	}

	public Room find(Long id) {
		// TODO Auto-generated method stub
		return roomDao.find(id);
	}

	public Room findBySn(String sn) {
		// TODO Auto-generated method stub
		return roomDao.findBySn(sn);
	}

	
	
	@Override
	public Room findByIdAndName(Long roomTypeId, String sn) {
		// TODO Auto-generated method stub
		Map<String, Object> queryMap=new HashMap();
		queryMap.put("roomTypeId", roomTypeId);
		queryMap.put("sn", roomTypeId);
		return roomDao.findByIdAndName(queryMap);
	}

}
