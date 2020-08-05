package io.springboot2.x.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.springboot2.x.domain.MyUsers;

public interface MyuserRepo extends JpaRepository<MyUsers, Long> {
	
}
