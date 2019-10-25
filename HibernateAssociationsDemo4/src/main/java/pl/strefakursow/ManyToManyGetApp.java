package pl.strefakursow;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import pl.strefakursow.entity.Company;
import pl.strefakursow.entity.CompanyDetail;
import pl.strefakursow.entity.Department;
import pl.strefakursow.entity.Employee;
import pl.strefakursow.entity.Property;
import pl.strefakursow.entity.Training;

public class ManyToManyGetApp {

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
//		Training training = new Training("java training");
//		Employee e1 = session.get(Employee.class, 128);
//		Employee e2 = session.get(Employee.class, 129);
//		training.addEmployee(e1);
//		training.addEmployee(e2);
//		session.persist(training);

		String fromTraining = "from Training";
		Query query = session.createQuery(fromTraining);
		
		List<Training> listTraining = query.getResultList();
		
		for(Training t : listTraining) {
			System.out.println("\n" + t);
				for(Employee e : t.getEmployees()) {
					System.out.println("- " + e);
				}
		}
		
//		Training training = session.get(Training.class, 2);
//		System.out.println(training);

		
		session.getTransaction().commit();
		
		factory.close();
	}

}
