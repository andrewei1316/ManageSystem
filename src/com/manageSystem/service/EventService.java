package com.manageSystem.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.po.Event;
@Transactional
public interface EventService {
	
	public List<Event> queryAllEvent();
	public List<Event> PreciseQueryEvent(HashMap<String, String> map);
	public List<Event> FuzzyQueryEvent(String key);
}
