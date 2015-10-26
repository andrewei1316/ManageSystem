package com.manageSystem.po;

/**
 * Event entity. @author MyEclipse Persistence Tools
 */

public class Event implements java.io.Serializable {

	// Fields

	private Integer eventId;
	private String ipccustomer;
	private String actionable;
	private String customercode;
	private String zprocessstate;
	private String cause;
	private String resolutioncode;
	private String class_;
	private String zbnotifystate;
	private String resourcetype;
	private String zgenericactionstate;
	private String ibmmanaged;
	private String zhnotifystate;
	private String lastupdate;
	private String summary;
	private String componenttype;
	private String customer;
	private String ostype;
	private String poll;
	private String expiretime;
	private String processreq;
	private String type;
	private String tasklist;
	private String ticketstatus;
	private String serial;
	private String lastoccurrence;
	private String acknowledged;
	private String zticketseverity;
	private String zticketstate;
	private String node;
	private String resolution;
	private String ownergid;
	private String targetipms;
	private String alertkey;
	private String suppressescl;
	private String flash;
	private String servername;
	private String alertgroup;
	private String tally;
	private String serverserial;
	private String grade;
	private String owneruid;
	private String component;
	private String ticketnumber;
	private String firstoccurrence;
	private String severity;
	private String originalseverity;

	// Constructors

	/** default constructor */
	public Event() {
	}

	/** full constructor */
	public Event(String ipccustomer, String actionable, String customercode,
			String zprocessstate, String cause, String resolutioncode,
			String class_, String zbnotifystate, String resourcetype,
			String zgenericactionstate, String ibmmanaged,
			String zhnotifystate, String lastupdate, String summary,
			String componenttype, String customer, String ostype, String poll,
			String expiretime, String processreq, String type, String tasklist,
			String ticketstatus, String serial, String lastoccurrence,
			String acknowledged, String zticketseverity, String zticketstate,
			String node, String resolution, String ownergid, String targetipms,
			String alertkey, String suppressescl, String flash,
			String servername, String alertgroup, String tally,
			String serverserial, String grade, String owneruid,
			String component, String ticketnumber, String firstoccurrence,
			String severity, String originalseverity) {
		this.ipccustomer = ipccustomer;
		this.actionable = actionable;
		this.customercode = customercode;
		this.zprocessstate = zprocessstate;
		this.cause = cause;
		this.resolutioncode = resolutioncode;
		this.class_ = class_;
		this.zbnotifystate = zbnotifystate;
		this.resourcetype = resourcetype;
		this.zgenericactionstate = zgenericactionstate;
		this.ibmmanaged = ibmmanaged;
		this.zhnotifystate = zhnotifystate;
		this.lastupdate = lastupdate;
		this.summary = summary;
		this.componenttype = componenttype;
		this.customer = customer;
		this.ostype = ostype;
		this.poll = poll;
		this.expiretime = expiretime;
		this.processreq = processreq;
		this.type = type;
		this.tasklist = tasklist;
		this.ticketstatus = ticketstatus;
		this.serial = serial;
		this.lastoccurrence = lastoccurrence;
		this.acknowledged = acknowledged;
		this.zticketseverity = zticketseverity;
		this.zticketstate = zticketstate;
		this.node = node;
		this.resolution = resolution;
		this.ownergid = ownergid;
		this.targetipms = targetipms;
		this.alertkey = alertkey;
		this.suppressescl = suppressescl;
		this.flash = flash;
		this.servername = servername;
		this.alertgroup = alertgroup;
		this.tally = tally;
		this.serverserial = serverserial;
		this.grade = grade;
		this.owneruid = owneruid;
		this.component = component;
		this.ticketnumber = ticketnumber;
		this.firstoccurrence = firstoccurrence;
		this.severity = severity;
		this.originalseverity = originalseverity;
	}

	// Property accessors

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getIpccustomer() {
		return this.ipccustomer;
	}

	public void setIpccustomer(String ipccustomer) {
		this.ipccustomer = ipccustomer;
	}

	public String getActionable() {
		return this.actionable;
	}

	public void setActionable(String actionable) {
		this.actionable = actionable;
	}

	public String getCustomercode() {
		return this.customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getZprocessstate() {
		return this.zprocessstate;
	}

	public void setZprocessstate(String zprocessstate) {
		this.zprocessstate = zprocessstate;
	}

	public String getCause() {
		return this.cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getResolutioncode() {
		return this.resolutioncode;
	}

	public void setResolutioncode(String resolutioncode) {
		this.resolutioncode = resolutioncode;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getZbnotifystate() {
		return this.zbnotifystate;
	}

	public void setZbnotifystate(String zbnotifystate) {
		this.zbnotifystate = zbnotifystate;
	}

	public String getResourcetype() {
		return this.resourcetype;
	}

	public void setResourcetype(String resourcetype) {
		this.resourcetype = resourcetype;
	}

	public String getZgenericactionstate() {
		return this.zgenericactionstate;
	}

	public void setZgenericactionstate(String zgenericactionstate) {
		this.zgenericactionstate = zgenericactionstate;
	}

	public String getIbmmanaged() {
		return this.ibmmanaged;
	}

	public void setIbmmanaged(String ibmmanaged) {
		this.ibmmanaged = ibmmanaged;
	}

	public String getZhnotifystate() {
		return this.zhnotifystate;
	}

	public void setZhnotifystate(String zhnotifystate) {
		this.zhnotifystate = zhnotifystate;
	}

	public String getLastupdate() {
		return this.lastupdate;
	}

	public void setLastupdate(String lastupdate) {
		this.lastupdate = lastupdate;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getComponenttype() {
		return this.componenttype;
	}

	public void setComponenttype(String componenttype) {
		this.componenttype = componenttype;
	}

	public String getCustomer() {
		return this.customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getOstype() {
		return this.ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getPoll() {
		return this.poll;
	}

	public void setPoll(String poll) {
		this.poll = poll;
	}

	public String getExpiretime() {
		return this.expiretime;
	}

	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	public String getProcessreq() {
		return this.processreq;
	}

	public void setProcessreq(String processreq) {
		this.processreq = processreq;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTasklist() {
		return this.tasklist;
	}

	public void setTasklist(String tasklist) {
		this.tasklist = tasklist;
	}

	public String getTicketstatus() {
		return this.ticketstatus;
	}

	public void setTicketstatus(String ticketstatus) {
		this.ticketstatus = ticketstatus;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getLastoccurrence() {
		return this.lastoccurrence;
	}

	public void setLastoccurrence(String lastoccurrence) {
		this.lastoccurrence = lastoccurrence;
	}

	public String getAcknowledged() {
		return this.acknowledged;
	}

	public void setAcknowledged(String acknowledged) {
		this.acknowledged = acknowledged;
	}

	public String getZticketseverity() {
		return this.zticketseverity;
	}

	public void setZticketseverity(String zticketseverity) {
		this.zticketseverity = zticketseverity;
	}

	public String getZticketstate() {
		return this.zticketstate;
	}

	public void setZticketstate(String zticketstate) {
		this.zticketstate = zticketstate;
	}

	public String getNode() {
		return this.node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getResolution() {
		return this.resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOwnergid() {
		return this.ownergid;
	}

	public void setOwnergid(String ownergid) {
		this.ownergid = ownergid;
	}

	public String getTargetipms() {
		return this.targetipms;
	}

	public void setTargetipms(String targetipms) {
		this.targetipms = targetipms;
	}

	public String getAlertkey() {
		return this.alertkey;
	}

	public void setAlertkey(String alertkey) {
		this.alertkey = alertkey;
	}

	public String getSuppressescl() {
		return this.suppressescl;
	}

	public void setSuppressescl(String suppressescl) {
		this.suppressescl = suppressescl;
	}

	public String getFlash() {
		return this.flash;
	}

	public void setFlash(String flash) {
		this.flash = flash;
	}

	public String getServername() {
		return this.servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public String getAlertgroup() {
		return this.alertgroup;
	}

	public void setAlertgroup(String alertgroup) {
		this.alertgroup = alertgroup;
	}

	public String getTally() {
		return this.tally;
	}

	public void setTally(String tally) {
		this.tally = tally;
	}

	public String getServerserial() {
		return this.serverserial;
	}

	public void setServerserial(String serverserial) {
		this.serverserial = serverserial;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getOwneruid() {
		return this.owneruid;
	}

	public void setOwneruid(String owneruid) {
		this.owneruid = owneruid;
	}

	public String getComponent() {
		return this.component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getTicketnumber() {
		return this.ticketnumber;
	}

	public void setTicketnumber(String ticketnumber) {
		this.ticketnumber = ticketnumber;
	}

	public String getFirstoccurrence() {
		return this.firstoccurrence;
	}

	public void setFirstoccurrence(String firstoccurrence) {
		this.firstoccurrence = firstoccurrence;
	}

	public String getSeverity() {
		return this.severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getOriginalseverity() {
		return this.originalseverity;
	}

	public void setOriginalseverity(String originalseverity) {
		this.originalseverity = originalseverity;
	}
	
	/* 
	 * 得到的格式为 字段名::::::值,,,,,, 字段名::::::值,,,,,, ... ,,,,,, 字段名::::::值;
	 */
	@Override
	public String toString() {
		return "ipccustomer::::::" + ipccustomer + ",,,,,, actionable::::::" + actionable
				+ ",,,,,, customercode::::::" + customercode + ",,,,,, zprocessstate::::::"
				+ zprocessstate + ",,,,,, cause::::::" + cause + ",,,,,, resolutioncode::::::"
				+ resolutioncode + ",,,,,, class_::::::" + class_ + ",,,,,, zbnotifystate::::::"
				+ zbnotifystate + ",,,,,, resourcetype::::::" + resourcetype
				+ ",,,,,, zgenericactionstate::::::" + zgenericactionstate
				+ ",,,,,, ibmmanaged::::::" + ibmmanaged + ",,,,,, zhnotifystate::::::"
				+ zhnotifystate + ",,,,,, lastupdate::::::" + lastupdate + ",,,,,, summary::::::"
				+ summary + ",,,,,, componenttype::::::" + componenttype + ",,,,,, customer::::::"
				+ customer + ",,,,,, ostype::::::" + ostype + ",,,,,, poll::::::" + poll
				+ ",,,,,, expiretime::::::" + expiretime + ",,,,,, processreq::::::" + processreq
				+ ",,,,,, type::::::" + type + ",,,,,, tasklist::::::" + tasklist
				+ ",,,,,, ticketstatus::::::" + ticketstatus + ",,,,,, serial::::::" + serial
				+ ",,,,,, lastoccurrence::::::" + lastoccurrence + ",,,,,, acknowledged::::::"
				+ acknowledged + ",,,,,, zticketseverity::::::" + zticketseverity
				+ ",,,,,, zticketstate::::::" + zticketstate + ",,,,,, node::::::" + node
				+ ",,,,,, resolution::::::" + resolution + ",,,,,, ownergid::::::" + ownergid
				+ ",,,,,, targetipms::::::" + targetipms + ",,,,,, alertkey::::::" + alertkey
				+ ",,,,,, suppressescl::::::" + suppressescl + ",,,,,, flash::::::" + flash
				+ ",,,,,, servername::::::" + servername + ",,,,,, alertgroup::::::" + alertgroup
				+ ",,,,,, tally::::::" + tally + ",,,,,, serverserial::::::" + serverserial
				+ ",,,,,, grade::::::" + grade + ",,,,,, owneruid::::::" + owneruid
				+ ",,,,,, component::::::" + component + ",,,,,, ticketnumber::::::" + ticketnumber
				+ ",,,,,, firstoccurrence::::::" + firstoccurrence + ", severity::::::"
				+ severity + ",,,,,, originalseverity::::::" + originalseverity;
	}
}