package com.springboot.Controller;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.Exception.ResourceNotFound;
import com.springboot.Module.Employee;
import com.springboot.Repository.EmployeeRepository;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		return employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("Employee not exixt with id "+id));
	}
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("Employee not exixt with id "+id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response=new HashMap<>();
		response.put("deleted" ,Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
  @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employeeDetails) {
		Employee employee=employeeRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFound("Employee not exixt with id "+id));
		employee.setName(employeeDetails.getName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setNumber(employeeDetails.getNumber());
		employee.setGender(employeeDetails.getGender());
		employee.setCountry(employeeDetails.getCountry());
		employee.setDob(employeeDetails.getDob());
		employee.setInterests(employeeDetails.getInterests());
	    Employee updatedEmployee = employeeRepository.save(employee);
	    return ResponseEntity.ok(updatedEmployee);
	}
	  @PostMapping("/employees/uploadImage")
	    public String uploadImage(@RequestParam("file") MultipartFile file) {
	        try {
	            Employee imageEntity = new Employee();
	            imageEntity.setImage(file.getBytes());
	            employeeRepository.save(imageEntity);
	            return "File uploaded successfully";
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "Failed to upload file";
	        }
	    }
}
