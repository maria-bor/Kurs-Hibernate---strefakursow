package pl.strefakursow;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Department;
import pl.strefakursow.entity.Property;

public class OneToManyUniGetApp {

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

//		Company company = session.get(Company.class, 12);
//		System.out.println(company);
//
//		Set<Department> departments = company.getDepartments();
//		for(Department d : departments) {
//			System.out.println(d);
//		}
		
		Department d = session.get(Department.class, 1);
		System.out.println(d);
		
		session.getTransaction().commit();
		
		factory.close();
	}

}
