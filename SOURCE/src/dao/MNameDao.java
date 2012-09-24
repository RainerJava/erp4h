package dao;

import org.hibernate.cfg.AnnotationConfiguration;
import dao.base.HibernateDao;
import dao.model.MName;
import dao.model.MNameId;

public class MNameDao extends HibernateDao<MName, MNameId> {
	public MNameDao() {
		this.sessionFactory = new AnnotationConfiguration().configure()
				.buildSessionFactory();
	}
}
