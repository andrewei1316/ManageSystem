package com.manageSystem.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.dao.MapDAO;
import com.manageSystem.service.MapService;

@Transactional
public class MapServiceImpl implements MapService {
	
	private MapDAO mapDao;
	
	@Override
	public HashMap<Integer, String> queryMap() {
		// TODO 自动生成的方法存根
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		List<com.manageSystem.po.Map> list = mapDao.findAll();
		for(int i = 0; i < list.size(); i++){
    		map.put(list.get(i).getFid(), list.get(i).getFname());
    	}
		return map;
	}
	
	public void setMapDao(MapDAO mapDao){
		this.mapDao = mapDao;
	}
	
	public MapDAO getMapDao(){
		return mapDao;
	}

}
