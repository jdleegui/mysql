package com.coweaver.test.test;

public class CRegion {
	
	CConfig parent = null;
	CRegion next_region = null;
	
	public CRegion(CConfig config) {
		parent = config;
	}

	public CRegion GetNextRegion() {
		return next_region;
	}

	public void SetNextRegion(CRegion region) {
		next_region = region;
	}
	
}
