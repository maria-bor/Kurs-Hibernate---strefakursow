package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;

public class BidirectionalApp {

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

//		Company company = new Company("PZU", 10000);
//		CompanyDetail companyDetail = new CompanyDetail("Poland", 17000);
//		companyDetail.setCompany(company);
//		company.setCompanyDetail(companyDetail);
		
		// rozpoczecie transakcji
		session.beginTransaction();
		
//		session.persist(companyDetail);

		CompanyDetail detail = session.get(CompanyDetail.class, 11);
		session.remove(detail);
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
//		System.out.println(detail.getCompany());
		
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
