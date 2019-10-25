package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Department;
import pl.strefakursow.entity.Property;

public class OneToManyUniSaveApp {

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

		Company company = session.get(Company.class, 12);
		System.out.println(company);
		
		Department d1 = new Department("Sales");
		Department d2 = new Department("Production");
		Department d3 = new Department("HR");
		
		company.addDepartment(d1);
		company.addDepartment(d2);
		company.addDepartment(d3);
		
		session.persist(company);
		
		session.getTransaction().commit();
		
		factory.close();
	}

}
