package com.manageSystem.service;

import com.manageSystem.util.Pager;

public interface PagerService {
	public  Pager getPager(String currentPage,String pagerMethod, int  totalRows);
}
