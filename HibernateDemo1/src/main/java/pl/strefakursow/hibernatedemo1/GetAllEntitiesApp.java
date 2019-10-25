package pl.strefakursow.hibernatedemo1;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.hibernatedemo1.entity.Employee;

public class GetAllEntitiesApp {

	public static void main(String[] args) {

		// stworzyc obiekt Configuration
		Configuration conf = new Configuration();
		
		// wczytac plik konfiguracyjny
		conf.configure("hibernate.cfg.xml");
		
		// wczytac adnotacje
		conf.addAnnotatedClass(Employee.class);
		
		// stworzyc obiekt SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		
		// pobierac sesje
		Session session = factory.getCurrentSession();
		
		// rozpoczecie transakcji
		session.beginTransaction();
		
		List<Employee> listEmployee = session.createQuery("from Employee").getResultList();
		for(Employee e : listEmployee) {
			System.out.println(e);
		}
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();
	}

}
