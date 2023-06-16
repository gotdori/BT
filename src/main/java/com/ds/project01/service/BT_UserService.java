package com.ds.project01.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.UserDto;
import com.ds.project01.repository.UserRepository;



@Service
@Transactional//여러개 쿼리 데이터 실행시 하나라도 실패하면 성공했던 쿼리도 다 취소 
public class BT_UserService {

	@Autowired
	private UserRepository userRepo;
	
	
	public List<UserEntity> adminList(String searchName){
		List<UserEntity> adminlist = new ArrayList<>();
		
		if (searchName == null) { //검색 키워드 없으면
			adminlist = userRepo.findAll(); //전체리스트 출력
		} else {					//있으면
			adminlist = seachNm(searchName); //검색한 리스트 출력
		}
		
		return adminlist;
	}
	
	public List<UserEntity> seachNm(String searchName){
		return userRepo.findByUserNmContaining(searchName);
	}
	
	public void insert(UserEntity entity) {
		userRepo.save(entity);
	}
	
	public void delete(UserEntity entity) {
		userRepo.delete(entity);
	}
	
	public UserEntity view(String userId) {
		UserDto dto = new UserDto();
		dto.setUserId(userId);
		UserEntity entity = UserEntity.toUserEntity(dto);
		UserEntity tempentity = userRepo.findByUserId(entity.getUserId());
		
		return tempentity;
	}
	
	public boolean idcheck(String userId) {
		boolean userIdDuplicate = userRepo.existsByUserId(userId);
		return userIdDuplicate;
	}
}
