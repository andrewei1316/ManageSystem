package com.manageSystem.po;

/**
 * Map entity. @author MyEclipse Persistence Tools
 */

public class Map implements java.io.Serializable {

	// Fields

	private Integer mapId;
	private Integer fid;
	private String fname;

	// Constructors

	/** default constructor */
	public Map() {
	}

	/** full constructor */
	public Map(Integer fid, String fname) {
		this.fid = fid;
		this.fname = fname;
	}

	// Property accessors

	public Integer getMapId() {
		return this.mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getFid() {
		return this.fid;
	}

	public void setFid(Integer fid) {
		this.fid = fid;
	}

	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

}