package pl.strefakursow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="training")
public class Training {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_training")
	private Integer idTraining;
	
	@Column(name="name")
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name="employee_training", joinColumns=@JoinColumn(name="id_training"), inverseJoinColumns=@JoinColumn(name="id_employee"))
	private List<Employee> employees;

	public Training() {
		// TODO Auto-generated constructor stub
	}

	public Training(String name) {
		this.name = name;
	}

	public Integer getIdTraining() {
		return idTraining;
	}

	public void setIdTraining(Integer idTraining) {
		this.idTraining = idTraining;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public void addEmployee(Employee employee) {
		if(this.employees == null) {
			this.employees = new ArrayList<Employee>();
		}
		
		this.employees.add(employee);
	}

	@Override
	public String toString() {
		return "Training [idTraining=" + idTraining + ", name=" + name + "]";
	}
	
}
