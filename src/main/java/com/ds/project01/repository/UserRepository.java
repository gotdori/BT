package com.ds.project01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.project01.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{

	UserEntity findByUserId(String userId);
	List<UserEntity> findByUserNmContaining(String searchName); //Containing을 쓰면 키워드 포함만 되도 검색
	boolean existsByUserId(String userId);
	
}
 