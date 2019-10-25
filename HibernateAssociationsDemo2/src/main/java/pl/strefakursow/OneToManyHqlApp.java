package pl.strefakursow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Property;

public class OneToManyHqlApp {

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
		
		String getCompany = "select c.name from Property p join p.company c where p.city='Sevilla'";
		String getCompany2  = "select c.name from Property p join p.company c join c.companyDetail cd where p.city='Barcelona' and cd.residence='Switzerland'";
		String getCompany3 = "select c.name from Company c where size(c.properties) > 4";
		
		// zakonczymy transakcje
		session.beginTransaction();

		Query query = session.createQuery(getCompany3);
		
		List<String> resultList = query.getResultList();
		
		session.getTransaction().commit();
		
		for(String s : resultList) {
			System.out.println(s);
		}
		
		factory.close();
	}

}
