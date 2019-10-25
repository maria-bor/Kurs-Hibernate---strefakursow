package pl.strefakursow;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Department;
import pl.strefakursow.entity.Employee;
import pl.strefakursow.entity.Property;
import pl.strefakursow.entity.Training;

public class ManyToManySaveApp {

	public static void main(String[] args) {
		// stworzyc obiekt Configuration
		Configuration conf = new Configuration();
		
		// wczytac plik konfiguracyjny
		conf.configure("hibernate.cfg.xml");
		
		// wczytac adnotacje
		conf.addAnnotatedClass(Company.class);
		conf.addAnnotatedClass(CompanyDetail.class);
		conf.addAnnotatedClass(Property.class);
		conf.addAnnotatedClass(Department.class);
		conf.addAnnotatedClass(Employee.class);
		conf.addAnnotatedClass(Training.class);
				
		// stworzyc obiekt SessionFactory
		SessionFactory factory = conf.buildSessionFactory();
		
		// pobierac sesje
		Session session = factory.getCurrentSession();
		
		// zakonczymy transakcje
		session.beginTransaction();

		Training training = new Training("sales training");
		Employee e1 = new Employee("Johny", "Dep",  16000);
		Employee e2 = new Employee("Miley", "Cyrus",  16000);
		
		training.addEmployee(e1);
		training.addEmployee(e2);
		
		session.persist(training);
		
		session.getTransaction().commit();
		
		factory.close();
	}

}
