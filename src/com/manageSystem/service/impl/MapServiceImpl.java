package com.manageSystem.service.impl;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.dao.MapDAO;
import com.manageSystem.service.MapService;

@Transactional
public class MapServiceImpl implements MapService {
	
	private MapDAO mapDao;
	
	@Override
	public HashMap<Integer, String> queryMap() {
		// TODO 自动生成的方法存根
		HashMap<Integer, String> map = mapDao.queryMap();
		return map;
	}
	
	public void setMapDao(MapDAO mapDao){
		this.mapDao = mapDao;
	}
	
	public MapDAO getMapDao(){
		return mapDao;
	}

}
