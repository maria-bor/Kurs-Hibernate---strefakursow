package pl.strefakursow.hibernatedemo1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.hibernatedemo1.entity.Employee;

public class PrimaryKeyApp {

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
				
				// stworzyc 3 obiekty klasy Employee
				Employee employee = new Employee();
				employee.setFirstName("Krzysztof");
				employee.setLastName("Nowak");
				employee.setSalary(10000);
				
				Employee employee2 = new Employee();
				employee2.setFirstName("Janina");
				employee2.setLastName("Kowalskia");
				employee2.setSalary(10000);
				
				Employee employee3 = new Employee();
				employee3.setFirstName("Andrzej");
				employee3.setLastName("Sienkiewicz");
				employee3.setSalary(10000);
				
				// rozpoczecie transakcji
				session.beginTransaction();
				
				// zapisanie 3 pracownikow
				session.save(employee);
				session.save(employee2);
				session.save(employee3);
				
				// zakonczymy transakcje
				session.getTransaction().commit();
				
				// zamkniecie obiektu SessionFactory
				factory.close();

	}

}
