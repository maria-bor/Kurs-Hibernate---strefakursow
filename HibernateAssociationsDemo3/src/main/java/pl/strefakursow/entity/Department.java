package pl.strefakursow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_department")
	private Integer idDepartment;

	@Column(name="name")
	private String name;

	public Integer getId_department() {
		return idDepartment;
	}

	public void setId_department(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Department(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [idDepartment=" + idDepartment + ", name=" + name + "]";
	}
	
}
