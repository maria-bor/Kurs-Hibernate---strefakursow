package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Department;
import pl.strefakursow.entity.Property;

public class OneToManyUniDeleteApp {

	public static void main(String[] args) {
		// stworzyc obiekt Configuration
		Configuration conf = new Configuration();
		
		// wczytac plik konfiguracyjny
		conf.configure("hibernate.cfg.xml");
		
		// wczytac adnotacje
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
				
		// stworzyc obiekt SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		
		// pobierac sesje
		Session session = factory.getCurrentSession();
		
		// zakonczymy transakcje
		session.beginTransaction();
//		first version
//		Department d = session.get(Department.class, 3);
//		
//		session.delete(d);
		
//		second version
//		Company company = session.get(Company.class, 12);
//		
//		for(Department d : company.getDepartments()) {
//			if(d.getName().equals("HR")) {
//				company.getDepartments().remove(d);
//				session.delete(d);
//			}
//		}
		
		
		String deleteDepartment = "delete Department d where d.idDepartment=:idDepartment";
		Query query = session.createQuery(deleteDepartment);
		query.setParameter("idDepartment", 1);
		query.executeUpdate();
		
		session.getTransaction().commit();
		
		factory.close();
	}

}
