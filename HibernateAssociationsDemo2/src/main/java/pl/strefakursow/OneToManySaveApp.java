package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Property;

public class OneToManySaveApp {

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
		
		String getCompany = "select c from Company c where c.name='Strefakursow'";
		
		// zakonczymy transakcje
		session.beginTransaction();

		Query query = session.createQuery(getCompany);
		
		Company company = (Company) query.getSingleResult();
		System.out.println(company);
		
		Property property1 = new Property("Warszawa", 40);
		Property property2 = new Property("Gdynia", 30);
		
		company.addProperty(property1);
		company.addProperty(property2);
		
		session.persist(property1);
		session.persist(property2);
		
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();
	}
}
