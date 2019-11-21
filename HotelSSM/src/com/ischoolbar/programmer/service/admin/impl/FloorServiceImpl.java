package com.ischoolbar.programmer.service.admin.impl;
/**
 * ¬•≤„service µœ÷¿‡
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ischoolbar.programmer.dao.admin.FloorDao;
import com.ischoolbar.programmer.entity.admin.Floor;
import com.ischoolbar.programmer.service.admin.FloorService;
@Service
public class FloorServiceImpl implements FloorService {

	@Autowired
	private FloorDao floorDao;
	
	public int add(Floor floor) {
		// TODO Auto-generated method stub
		return floorDao.add(floor);
	}

	public int edit(Floor floor) {
		// TODO Auto-generated method stub
		return floorDao.edit(floor);
	}

	public int delete(Long id) {
		// TODO Auto-generated method stub
		return floorDao.delete(id);
	}

	public List<Floor> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return floorDao.findList(queryMap);
	}

	public List<Floor> findAll() {
		// TODO Auto-generated method stub
		return floorDao.findAll();
	}

	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return floorDao.getTotal(queryMap);
	}

}
