package com.manageSystem.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.po.Event;
import com.manageSystem.dao.EventDAO;
import com.manageSystem.service.EventService;

@Transactional
public class EventServiceImpl implements EventService {
	
	private EventDAO eventDao;
	
	@Override
	public List<Event> PreciseQueryEvent(HashMap<String, String> map) {
		// TODO 自动生成的方法存根
		List<Event> list = eventDao.PreciseQueryEvent(map);
		return list;
	}

	@Override
	public List<Event> FuzzyQueryEvent(String key) {
		// TODO 自动生成的方法存根
		List<Event> list = eventDao.FuzzyQueryEvent(key);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> queryAllEvent() {
		// TODO 自动生成的方法存根
		List<Event> list = eventDao.findAll();
		return list;
	}
	
	public void setEventDao(EventDAO eventDao){
		this.eventDao = eventDao;
	}
	
	public EventDAO getEventDao(){
		return eventDao;
	}

}
