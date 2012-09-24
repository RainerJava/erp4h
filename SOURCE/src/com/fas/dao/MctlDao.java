/**
 * 
 */
package com.fas.dao;

import org.hibernate.cfg.AnnotationConfiguration;

import com.fas.dao.base.HibernateDao;
import com.fas.vo.MCtl;
import com.fas.vo.MCtlId;

/**
 * @author dntuan
 * 
 */
public class MctlDao extends HibernateDao<MCtl, MCtlId> {
	public MctlDao() {
		this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	public void getMctlVoList(String userid, String mtnFlg){
		
	}
}
