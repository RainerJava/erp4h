package dao;

import org.hibernate.cfg.AnnotationConfiguration;

import dao.base.HibernateDao;
import dao.model.MMenugrp;

public class MMenugrpDao extends HibernateDao<MMenugrp, Long> {
	public MMenugrpDao() {
		this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
}
