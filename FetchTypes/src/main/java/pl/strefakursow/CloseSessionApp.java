package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Property;

public class CloseSessionApp {

	public static void main(String[] args) {
		// stworzyc obiekt Configuration
		Configuration conf = new Configuration();
		
		// wczytac plik konfiguracyjny
		conf.configure("hibernate.cfg.xml");
		
		// wczytac adnotacje
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		
		// stworzyc obiekt SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		
		// pobierac sesje
		Session session = factory.getCurrentSession();
		
	
		// zakonczymy transakcje
		session.beginTransaction();

		System.out.println("Wybierania obietku company");
		Company company = session.get(Company.class, 29);
		System.out.println("Pobrano obiekt company");
		System.out.println(company);
		
		session.getTransaction().commit();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		Company mergedCompany = (Company) session.merge(company);
		
		System.out.println("...");
		
		System.out.println("Nieruchomosci:");
		for(Property p : mergedCompany.getProperties()) {
			System.out.println(p);
		}
		
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();
	}

}
