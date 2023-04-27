package com.ds.project01.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobbyDataEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.domain.UserEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.repository.DeptRepository;
import com.ds.project01.repository.HobbyDataRepository;
import com.ds.project01.repository.HobbyRepository;
import com.ds.project01.repository.UserRepository;



@Service
@Transactional//여러개 쿼리 데이터 실행시 하나라도 실패하면 성공했던 쿼리도 다 취소 
public class BT_UserService { //해놓고 보니 서비스를 유저, 취미 등으로 안나눴음

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private DeptRepository deptRepo;
	
	@Autowired
	private HobbyRepository hobbyRepo;
	
	@Autowired
	private HobbyDataRepository hobbyDataRepo;
	
	
	public List<UserEntity> adminList(String searchKeyword){
		List<UserEntity> adminlist = new ArrayList<>();
		
		if (searchKeyword == null) { //검색 키워드 없으면
			adminlist = userRepo.findAll(); //전체리스트 출력
		} else {					//있으면
			adminlist = seachNm(searchKeyword); //검색한 리스트 출력
		}
		
		return adminlist;
	}
	
	public List<UserEntity> seachNm(String searchKeyword){
		return userRepo.findByUserNmContaining(searchKeyword);
	}
	
	public void insert(UserDto dto, HobbyDataDto hdDto) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		userRepo.save(entity);
		
		HobbyDataDelete(dto.getUserId());	//유저취미 업데이트하기 위해 취미 데이터를 먼저 싹 지우고 밑에서 다시 추가함
		String splitChoice = hdDto.getHobbyCd();	//dto로 취미코드 1,2,3 받은거 저장
		String[] ArraysStr = splitChoice.split(",");
		HobbyDataDto hobbyDataDto = new HobbyDataDto();	//빈 Dto 만들어서 취미데이터 넣어줌
		for(String s : ArraysStr) {
			hobbyDataDto.setHobbyCd(s);
			hobbyDataDto.setUserId(dto.getUserId());
			HobbyDataEntity hobbyDataEntity = HobbyDataEntity.toHobbyDataEntity(hobbyDataDto); //Dto를 entity로 변환
			HobbyDataInsert(hobbyDataEntity); //취미데이터도 저장
		}
	}
	
	public void delete(UserDto dto) {
		UserEntity entity = UserEntity.toUserEntity(dto);
		
		HobbyDataDelete(dto.getUserId());
		userRepo.delete(entity);
	}
	
	public UserEntity view(UserDto userDto) {
		UserEntity entity = UserEntity.toUserEntity(userDto);
		UserEntity tempentity = userRepo.findByUserId(entity.getUserId());
		
		
		return tempentity;
	}
	
	public List<DeptEntity> deptList(){
		return deptRepo.findAll();
	}
	
	public List<HobbyEntity> hobbyList(){

		return hobbyRepo.findAll();
	}
	
	public void HobbyDataInsert(HobbyDataEntity entity) {
		hobbyDataRepo.save(entity);
	}
	
	public List<HobbyDataEntity> HobbyDataView(String userId) {
		List<HobbyDataEntity> hobbyDataList = hobbyDataRepo.findByUserEntity_UserId(userId);
		return hobbyDataList;
	}
	
	public void HobbyDataDelete(String userId) {
		hobbyDataRepo.deleteByUserEntity_UserId(userId);
	}
}
