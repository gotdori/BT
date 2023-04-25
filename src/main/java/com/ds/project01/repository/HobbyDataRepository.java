package com.ds.project01.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.project01.domain.HobbyDataEntity;


public interface HobbyDataRepository extends JpaRepository<HobbyDataEntity, String>{

	// findBy참조받는클래스(맨앞대문자)_참조받는테이블Dto명(맨앞대문자)
	List<HobbyDataEntity> findByUserEntity_UserId(String userId);
	void deleteByUserEntity_UserId(String userID);
    
}