package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;

public class OneToOneApp {

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
		
		Company company = new Company("StrefaKursow", 1000000);
		CompanyDetail companyDetail = new CompanyDetail("Poland", 150);
		
		company.setCompanyDetail(companyDetail);

		// rozpoczecie transakcji
		session.beginTransaction();
		
		session.save(companyDetail);
		session.save(company);
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
