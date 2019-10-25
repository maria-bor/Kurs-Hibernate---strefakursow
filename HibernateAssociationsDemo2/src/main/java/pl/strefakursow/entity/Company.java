package pl.strefakursow.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="company")
public class Company {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_company")
	private Integer idCompany;
	
	@Column(name="name")
	private String name;
	
	@Column(name="value")
	private Integer value;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_company_detail")
	private CompanyDetail companyDetail;
	
	@OneToMany(mappedBy="company", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Property> properties;
	
	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Company(String name, Integer value) {
		this.name = name;
		this.value = value;
	}

	public Integer getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	
	public CompanyDetail getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	public void addProperty(Property property) {
		if(property == null) {
			this.properties = new ArrayList<Property>();
		}
		
		this.properties.add(property);
		property.setCompany(this);
	}

	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", name=" + name + ", value=" + value + "]";
	}

}
