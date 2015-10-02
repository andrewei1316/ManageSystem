package com.manageSystem.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface MapService {
	
	public HashMap<Integer, String> queryMap();
}
