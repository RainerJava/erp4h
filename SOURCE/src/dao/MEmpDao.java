package dao;

import org.hibernate.cfg.AnnotationConfiguration;

import dao.base.HibernateDao;
import dao.model.MEmp;

public class MEmpDao extends HibernateDao<MEmp, String> {
	public MEmpDao() {
		this.sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
}
