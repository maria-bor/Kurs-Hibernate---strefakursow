package pl.strefakursow.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.hibernatedemo1.entity.Employee;

public class UpdateManageEntityApp {

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
		
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		
		Employee employee = session.get(Employee.class, 18);
		employee.setFirstName("TadeuszNew");
		session.getTransaction().commit();
		
		System.out.println("Zaktualizowane dane prawcownika: " + employee);
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
