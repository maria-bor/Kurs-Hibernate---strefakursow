package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;

public class CascadeApp {

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
		
		Company company = new Company("Orlen", 200000000);
		CompanyDetail companyDetail = new CompanyDetail("Poland", 150000);
		
		company.setCompanyDetail(companyDetail);

		// rozpoczecie transakcji
		session.beginTransaction();
		
		session.persist(company);
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
