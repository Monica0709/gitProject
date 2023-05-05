package com.springboot.Module;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="Employees")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="number")
	private String number;
	@Column(name="gender")
	private String gender;
	@Column(name="country")
	private String country;
	@Column(name="dob")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate dob;
    @Column(name = "interest")
    @ElementCollection
    @CollectionTable(name = "Employee_Interest", joinColumns = @JoinColumn(name = "employee_id"))
    private List<String> interests;
	@Lob
	@Column(name="image")
	private byte[] image;
	public Employee() {
		
	}
	
	public Employee(long id, String name, String email, String number, String gender, String country
			, LocalDate dob, List<String> interests, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.number = number;
		this.gender = gender;
		this.country = country;
		this.interests = interests;
		this.dob = dob;
		this.image = image;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public List<String> getInterests() {
		return interests;
	}
	public void setInterests(List<String> interests) {
		this.interests = interests;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
}
