package pl.strefakursow.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.hibernatedemo1.entity.Employee;

public class SaveEntityApp {

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
		
		// stworzyc obiekt klasy Employee
		Employee employee = new Employee();
		employee.setIdEmployee(1);
		employee.setFirstName("Jan");
		employee.setLastName("Kowalski");
		employee.setSalary(10000);
		
		// rozpoczecie transakcji
		session.beginTransaction();
		
		// zapisanie pracownika
		session.save(employee);
		
		// zakonczymy transakcje
		session.getTransaction().commit();
		
		// zamkniecie obiektu SessionFactory
		factory.close();

	}

}
