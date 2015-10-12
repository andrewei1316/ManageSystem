package com.manageSystem.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.manageSystem.po.Map;

/**
 * A data access object (DAO) providing persistence and search support for Map
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.manageSystem.po.Map
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MapDAO {
	private static final Logger log = LoggerFactory.getLogger(MapDAO.class);
	// property constants
	public static final String FID = "fid";
	public static final String FNAME = "fname";

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

	public void save(Map transientInstance) {
		log.debug("saving Map instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Map persistentInstance) {
		log.debug("deleting Map instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Map findById(java.lang.Integer id) {
		log.debug("getting Map instance with id: " + id);
		try {
			Map instance = (Map) getCurrentSession().get(
					"com.manageSystem.po.Map", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Map instance) {
		log.debug("finding Map instance by example");
		try {
			List results = getCurrentSession()
					.createCriteria("com.manageSystem.po.Map")
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
		log.debug("finding Map instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Map as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFid(Object fid) {
		return findByProperty(FID, fid);
	}

	public List findByFname(Object fname) {
		return findByProperty(FNAME, fname);
	}

	public List findAll() {
		log.debug("finding all Map instances");
		try {
			String queryString = "from Map";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Map merge(Map detachedInstance) {
		log.debug("merging Map instance");
		try {
			Map result = (Map) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Map instance) {
		log.debug("attaching dirty Map instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Map instance) {
		log.debug("attaching clean Map instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MapDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MapDAO) ctx.getBean("MapDAO");
	}
}