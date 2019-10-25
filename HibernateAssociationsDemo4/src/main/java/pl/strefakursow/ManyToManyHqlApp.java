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

public class ManyToManyHqlApp {

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
		
		int minEmployeeNumber = 6;
		String course = "javascript";
		int trainingNumber = 1;
		int maxSalary = 12000;
		
		// zakonczymy transakcje
		session.beginTransaction();

		String getTraining = "select t from Training t where size(t.employees) >= :minEmployeeNumber";
		String getEmployees = "select e from Employee e join e.trainings t where t.name=:course";
		String getEmployeeSalary = "select e from Employee e where size(e.trainings)=:trainingNumber and e.salary<:maxSalary ";
		
		Query query = session.createQuery(getEmployeeSalary);
//		query.setParameter("minEmployeeNumber", minEmployeeNumber);
//		query.setParameter("course", course);
		query.setParameter("maxSalary", maxSalary);
		query.setParameter("trainingNumber", trainingNumber);
//		List<Training> resultList = query.getResultList();
//
//		for(Training t : resultList) {
//			System.out.println(t);
//		}
		
		List<Employee> resultList = query.getResultList();
		for(Employee e : resultList) {
			System.out.println(e);
		}
		
		session.getTransaction().commit();
		
		factory.close();
	}

}
