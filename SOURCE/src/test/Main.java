package test;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;

import dao.MNameDao;

public class Main {
	public static void main(String[] args) {
//		System.out.println("Server start");
		MNameDao dao = new MNameDao();
		//dao.Test();
//		for (MName obj : dao.getAll()) {
//			System.out.println(obj.getNameName());
//		}
		System.out.println("done");
//		Criteria criteria = getSession().createCriteria(Person.class);
//		criteria.addAlias("listChildren","children",CriteriaSpecification.LEFT_JOIN);
//		criteria.list();
	}
}
