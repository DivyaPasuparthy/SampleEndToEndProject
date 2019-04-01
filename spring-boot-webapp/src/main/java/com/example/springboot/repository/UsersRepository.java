package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByUserName(String userName);
}
