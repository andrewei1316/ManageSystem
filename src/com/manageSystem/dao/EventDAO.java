package com.manageSystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.po.Event;

/**
 * A data access object (DAO) providing persistence and search support for Event
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.manageSystem.po.Event
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class EventDAO {
	private static final Logger log = LoggerFactory.getLogger(EventDAO.class);
	// property constants
	public static final String IPCCUSTOMER = "ipccustomer";
	public static final String ACTIONABLE = "actionable";
	public static final String CUSTOMERCODE = "customercode";
	public static final String ZPROCESSSTATE = "zprocessstate";
	public static final String CAUSE = "cause";
	public static final String RESOLUTIONCODE = "resolutioncode";
	public static final String CLASS_ = "class_";
	public static final String ZBNOTIFYSTATE = "zbnotifystate";
	public static final String RESOURCETYPE = "resourcetype";
	public static final String ZGENERICACTIONSTATE = "zgenericactionstate";
	public static final String IBMMANAGED = "ibmmanaged";
	public static final String ZHNOTIFYSTATE = "zhnotifystate";
	public static final String LASTUPDATE = "lastupdate";
	public static final String SUMMARY = "summary";
	public static final String COMPONENTTYPE = "componenttype";
	public static final String CUSTOMER = "customer";
	public static final String OSTYPE = "ostype";
	public static final String POLL = "poll";
	public static final String EXPIRETIME = "expiretime";
	public static final String PROCESSREQ = "processreq";
	public static final String TYPE = "type";
	public static final String TASKLIST = "tasklist";
	public static final String TICKETSTATUS = "ticketstatus";
	public static final String SERIAL = "serial";
	public static final String LASTOCCURRENCE = "lastoccurrence";
	public static final String ACKNOWLEDGED = "acknowledged";
	public static final String ZTICKETSEVERITY = "zticketseverity";
	public static final String ZTICKETSTATE = "zticketstate";
	public static final String NODE = "node";
	public static final String RESOLUTION = "resolution";
	public static final String OWNERGID = "ownergid";
	public static final String TARGETIPMS = "targetipms";
	public static final String ALERTKEY = "alertkey";
	public static final String SUPPRESSESCL = "suppressescl";
	public static final String FLASH = "flash";
	public static final String SERVERNAME = "servername";
	public static final String ALERTGROUP = "alertgroup";
	public static final String TALLY = "tally";
	public static final String SERVERSERIAL = "serverserial";
	public static final String GRADE = "grade";
	public static final String OWNERUID = "owneruid";
	public static final String COMPONENT = "component";
	public static final String TICKETNUMBER = "ticketnumber";
	public static final String FIRSTOCCURRENCE = "firstoccurrence";
	public static final String SEVERITY = "severity";
	public static final String ORIGINALSEVERITY = "originalseverity";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Event transientInstance) {
		log.debug("saving Event instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Event persistentInstance) {
		log.debug("deleting Event instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Event findById(java.lang.Integer id) {
		log.debug("getting Event instance with id: " + id);
		try {
			Event instance = (Event) getCurrentSession().get(
					"com.manageSystem.po.Event", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Event instance) {
		log.debug("finding Event instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.manageSystem.po.Event")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Event instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Event as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIpccustomer(Object ipccustomer) {
		return findByProperty(IPCCUSTOMER, ipccustomer);
	}

	public List findByActionable(Object actionable) {
		return findByProperty(ACTIONABLE, actionable);
	}

	public List findByCustomercode(Object customercode) {
		return findByProperty(CUSTOMERCODE, customercode);
	}

	public List findByZprocessstate(Object zprocessstate) {
		return findByProperty(ZPROCESSSTATE, zprocessstate);
	}

	public List findByCause(Object cause) {
		return findByProperty(CAUSE, cause);
	}

	public List findByResolutioncode(Object resolutioncode) {
		return findByProperty(RESOLUTIONCODE, resolutioncode);
	}

	public List findByClass_(Object class_) {
		return findByProperty(CLASS_, class_);
	}

	public List findByZbnotifystate(Object zbnotifystate) {
		return findByProperty(ZBNOTIFYSTATE, zbnotifystate);
	}

	public List findByResourcetype(Object resourcetype) {
		return findByProperty(RESOURCETYPE, resourcetype);
	}

	public List findByZgenericactionstate(Object zgenericactionstate) {
		return findByProperty(ZGENERICACTIONSTATE, zgenericactionstate);
	}

	public List findByIbmmanaged(Object ibmmanaged) {
		return findByProperty(IBMMANAGED, ibmmanaged);
	}

	public List findByZhnotifystate(Object zhnotifystate) {
		return findByProperty(ZHNOTIFYSTATE, zhnotifystate);
	}

	public List findByLastupdate(Object lastupdate) {
		return findByProperty(LASTUPDATE, lastupdate);
	}

	public List findBySummary(Object summary) {
		return findByProperty(SUMMARY, summary);
	}

	public List findByComponenttype(Object componenttype) {
		return findByProperty(COMPONENTTYPE, componenttype);
	}

	public List findByCustomer(Object customer) {
		return findByProperty(CUSTOMER, customer);
	}

	public List findByOstype(Object ostype) {
		return findByProperty(OSTYPE, ostype);
	}

	public List findByPoll(Object poll) {
		return findByProperty(POLL, poll);
	}

	public List findByExpiretime(Object expiretime) {
		return findByProperty(EXPIRETIME, expiretime);
	}

	public List findByProcessreq(Object processreq) {
		return findByProperty(PROCESSREQ, processreq);
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findByTasklist(Object tasklist) {
		return findByProperty(TASKLIST, tasklist);
	}

	public List findByTicketstatus(Object ticketstatus) {
		return findByProperty(TICKETSTATUS, ticketstatus);
	}

	public List findBySerial(Object serial) {
		return findByProperty(SERIAL, serial);
	}

	public List findByLastoccurrence(Object lastoccurrence) {
		return findByProperty(LASTOCCURRENCE, lastoccurrence);
	}

	public List findByAcknowledged(Object acknowledged) {
		return findByProperty(ACKNOWLEDGED, acknowledged);
	}

	public List findByZticketseverity(Object zticketseverity) {
		return findByProperty(ZTICKETSEVERITY, zticketseverity);
	}

	public List findByZticketstate(Object zticketstate) {
		return findByProperty(ZTICKETSTATE, zticketstate);
	}

	public List findByNode(Object node) {
		return findByProperty(NODE, node);
	}

	public List findByResolution(Object resolution) {
		return findByProperty(RESOLUTION, resolution);
	}

	public List findByOwnergid(Object ownergid) {
		return findByProperty(OWNERGID, ownergid);
	}

	public List findByTargetipms(Object targetipms) {
		return findByProperty(TARGETIPMS, targetipms);
	}

	public List findByAlertkey(Object alertkey) {
		return findByProperty(ALERTKEY, alertkey);
	}

	public List findBySuppressescl(Object suppressescl) {
		return findByProperty(SUPPRESSESCL, suppressescl);
	}

	public List findByFlash(Object flash) {
		return findByProperty(FLASH, flash);
	}

	public List findByServername(Object servername) {
		return findByProperty(SERVERNAME, servername);
	}

	public List findByAlertgroup(Object alertgroup) {
		return findByProperty(ALERTGROUP, alertgroup);
	}

	public List findByTally(Object tally) {
		return findByProperty(TALLY, tally);
	}

	public List findByServerserial(Object serverserial) {
		return findByProperty(SERVERSERIAL, serverserial);
	}

	public List findByGrade(Object grade) {
		return findByProperty(GRADE, grade);
	}

	public List findByOwneruid(Object owneruid) {
		return findByProperty(OWNERUID, owneruid);
	}

	public List findByComponent(Object component) {
		return findByProperty(COMPONENT, component);
	}

	public List findByTicketnumber(Object ticketnumber) {
		return findByProperty(TICKETNUMBER, ticketnumber);
	}

	public List findByFirstoccurrence(Object firstoccurrence) {
		return findByProperty(FIRSTOCCURRENCE, firstoccurrence);
	}

	public List findBySeverity(Object severity) {
		return findByProperty(SEVERITY, severity);
	}

	public List findByOriginalseverity(Object originalseverity) {
		return findByProperty(ORIGINALSEVERITY, originalseverity);
	}

	public List findAll() {
		log.debug("finding all Event instances");
		try {
			String queryString = "from Event";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Event merge(Event detachedInstance) {
		log.debug("merging Event instance");
		try {
			Event result = (Event) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Event instance) {
		log.debug("attaching dirty Event instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Event instance) {
		log.debug("attaching clean Event instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static EventDAO getFromApplicationContext(ApplicationContext ctx) {
		return (EventDAO) ctx.getBean("EventDAO");
	}
	
	public List<Event> PreciseQueryEvent(HashMap<String, String> map) {
		log.debug("find by PreSearchKeys");
		try{
			String queryString = "select * from event";
			if(map.size() > 0) queryString = queryString + " where ";
			Set<String> set = map.keySet();
			boolean flag = false;
			for(Iterator<String> iterator = set.iterator();iterator.hasNext();)
	        {
				if(flag) queryString = queryString + " and ";
	            String s1 = (String)iterator.next();
	            queryString = queryString + "event." +s1 +" = ?";
	            flag = true;
	        }
			Query queryObject = getCurrentSession().createSQLQuery(queryString).addEntity(Event.class);
			int dx = 0;
			for(Iterator<String> iterator = set.iterator();iterator.hasNext();)
	        {
	            String s1 = (String)iterator.next();
	            String s2 = (String)map.get(s1);
	            queryObject.setString(dx, s2);
	            dx++;
	        }
			return queryObject.list();
		}catch (RuntimeException re) {
			log.error("find by PreSearchKeys name failed", re);
			throw re;
		}
	}

	public List<Event> FuzzyQueryEvent(String key) {
		log.debug("find by FuzSearchKeys");
		try{
			String queryString = "select * from event";
			String keys[] = key.split(" +");
			int keySize = keys.length;
			String tmp = " concat(IPCCUSTOMER, CAUSE, ACTIONABLE, CUSTOMERCODE, ZPROCESSSTATE, RESOLUTIONCODE, CLASS_, ZBNOTIFYSTATE, RESOURCETYPE, ZGENERICACTIONSTATE, IBMMANAGED, ZHNOTIFYSTATE, LASTUPDATE, SUMMARY, COMPONENTTYPE, CUSTOMER, OSTYPE, POLL, EXPIRETIME, PROCESSREQ, TYPE, TASKLIST, TICKETSTATUS, SERIAL, LASTOCCURRENCE, ACKNOWLEDGED, ZTICKETSEVERITY, ZTICKETSTATE, NODE, RESOLUTION, OWNERGID, TARGETIPMS, ALERTKEY, SUPPRESSESCL, FLASH, SERVERNAME, ALERTGROUP, TALLY, SERVERSERIAL, GRADE, OWNERUID, COMPONENT, TICKETNUMBER, FIRSTOCCURRENCE, SEVERITY, ORIGINALSEVERITY) like ";
			if(keySize > 0){
				queryString = queryString + " where ";
				boolean flag = false;
				for(int i = 0; i < keySize; i++){
					if(flag) queryString = queryString + " or ";
					queryString = queryString + tmp + "'%" + keys[i] + "%'";
					flag = true;
				}
			}
			Query queryObject = getCurrentSession().createSQLQuery(queryString).addEntity(Event.class);
			return queryObject.list();
		}catch (RuntimeException re) {
			log.error("find by FuzSearchKeys name failed", re);
			throw re;
		}
	}
	
	public List<Event> FuzzyQueryEvent1(String key) {
		log.debug("find by FuzSearchKeys");
		try{
			List<Event> allEventList = findAll();
			List<Event> ansList = new ArrayList<Event>();
			String keys[] = key.split(" +");
			int keySize = keys.length;
			if(keySize > 0){
				int size = allEventList.size();
				for(int i = 0; i < size; i++){
					String attriValue[] = allEventList.get(i).toString().split(", ");
					for(int j = 0; j < attriValue.length; j++){	
						String value = attriValue[j].split("=")[1];
						int opNum = 0;
						/////////  »¹Ã»Ð´Íê
					}
				}
			}
			return ansList;
		}catch (RuntimeException re) {
			log.error("find by FuzSearchKeys name failed", re);
			throw re;
		}
	}
}