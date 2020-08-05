package io.springboot2.x.controller;

import org.springframework.beans.factory.annotation.Autowired;

import io.springboot2.x.domain.MyUsers;
import io.springboot2.x.repository.MyuserRepo;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyuserController {
	
	

	    @Autowired
	    private MyuserRepo userRepository;
	    
	    @GetMapping(path = "/v1")
	    public String get() {
	    	return "updated version";
	    }
		
		@PostMapping("/addUser")
		public String saveUser(@RequestBody MyUsers emp) {
			userRepository.save(emp);
			return "User added successfully::"+emp.getId();
			
		}
		
		@GetMapping("/users")
		public List<MyUsers> getUsers() {
			return userRepository.findAll();
		}

		@GetMapping("/findUser/{id}")
		public Optional<MyUsers> getUser(@PathVariable Long id) {
			return userRepository.findById(id);
		}
		
		@GetMapping("/deleteUser/{id}")
		public String deleteUser(@PathVariable Long id) {
			userRepository.deleteById(id);
			return "Deleted User Successfully::"+id;
		}
		

	}

