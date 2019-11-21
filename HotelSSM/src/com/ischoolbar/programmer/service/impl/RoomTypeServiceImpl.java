package com.ischoolbar.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.RoomTypeDao;
import com.ischoolbar.programmer.entity.RoomType;
import com.ischoolbar.programmer.service.RoomTypeService;
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private RoomTypeDao roomTypeDao;
	
	public int add(RoomType roomType) {
		// TODO Auto-generated method stub
		return roomTypeDao.add(roomType);
	}

	public int edit(RoomType roomType) {
		// TODO Auto-generated method stub
		return roomTypeDao.edit(roomType);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return roomTypeDao.delete(id);
	}

	public List<RoomType> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roomTypeDao.findList(queryMap);
	}

	public List<RoomType> findAll() {
		// TODO Auto-generated method stub
		return roomTypeDao.findAll();
	}

	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return roomTypeDao.getTotal(queryMap);
	}

	public RoomType find(Long id) {
		// TODO Auto-generated method stub
		return roomTypeDao.find(id);
	}

	public int updateNum(RoomType roomType) {
		// TODO Auto-generated method stub
		return roomTypeDao.updateNum(roomType);
	}

	@Override
	public void updateDiffNum(int status,int roomTypeId) {
		// TODO Auto-generated method stub
		RoomType type=new RoomType();
		type.setId((long)roomTypeId);
		type.setStatus(status);
		
		roomTypeDao.updateDiffNum(status,roomTypeId);
	}

	@Override
	public RoomType checkName(String name) {
		// TODO Auto-generated method stub
		return roomTypeDao.findCheckName(name);
	}

	@Override
	public Integer findByName(String name) {
		// TODO Auto-generated method stub
		return roomTypeDao.findByName(name);
	}

}
