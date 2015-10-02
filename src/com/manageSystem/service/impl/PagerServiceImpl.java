package com.manageSystem.service.impl;

import com.manageSystem.service.PagerService;
import com.manageSystem.util.Pager;

public class PagerServiceImpl implements PagerService {

	@Override
	public Pager getPager(String currentPage, String pagerMethod, int totalRows) {
		Pager pager = new Pager(totalRows);
		if (currentPage != null) {
			pager.refresh(Integer.parseInt(currentPage));
		}
		if (pagerMethod != null) {
			if (pagerMethod.equals(" first ")) {
				pager.first();
			} else if (pagerMethod.equals(" previous ")) {
				pager.previous();
			} else if (pagerMethod.equals(" next ")) {
				pager.next();
			} else if (pagerMethod.equals(" last ")) {
				pager.last();
			}
		}
		return pager;
	}
}
