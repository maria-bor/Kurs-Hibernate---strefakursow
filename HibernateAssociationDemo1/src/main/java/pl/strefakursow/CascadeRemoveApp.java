package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;

public class CascadeRemoveApp {

	public static void main(String[] args) {
		// stworzyc obiekt Configuration
		Configuration conf = new Configuration();
		
		// wczytac plik konfiguracyjny
		conf.configure("hibernate.cfg.xml");
		
		// wczytac adnotacje
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		
		// stworzyc obiekt SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		
		// pobierac sesje
		Session session = factory.getCurrentSession();

		// rozpoczecie transakcji
		session.beginTransaction();
		
		Company company = session.get(Company.class, 11);
		session.remove(company);
		session.refresh(company);
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
