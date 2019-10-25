package pl.strefakursow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;

public class OnetoOneHqlApp {

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

		String select = "select c from Company c";
		String join = "select c from Company c join c.companyDetail cd where cd.residence='Italy'";
		String sum = "select sum(c.value) from Company c join c.companyDetail cd where cd.residence='Poland'";	
		String name = "select c.name from CompanyDetail cd join cd.company c where cd.employeeNumber < 35000 order by c.value";
		// rozpoczecie transakcji
		session.beginTransaction();
		
		Query query = session.createQuery(name); 
		
//		List<Company> list = query.getResultList();
//		Long result = (Long) query.getSingleResult();
		
		List<String> listName = query.getResultList();
		// zakonczymy transakcje
		session.getTransaction().commit();
		
//		for(Company c : list) {
//			System.out.println(c + ", " + c.getCompanyDetail());
//		}
//		
		
//		System.out.println("Wartosc wszystkich pl firm w DB: " + result);

		for(String c : listName) {
			System.out.println(c);
		}
	
		
		// zamkniecie obiektu SessionFactory
		factory.close();
	}

}
