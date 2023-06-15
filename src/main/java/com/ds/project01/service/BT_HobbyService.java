package com.ds.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.HobbyDataEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.repository.HobbyDataRepository;
import com.ds.project01.repository.HobbyRepository;



@Service
@Transactional//여러개 쿼리 데이터 실행시 하나라도 실패하면 성공했던 쿼리도 다 취소 
public class BT_HobbyService {

	@Autowired
	private HobbyDataRepository hobbyDataRepo;
	
	@Autowired
	private HobbyRepository hobbyRepo;
	
	public List<HobbyEntity> hobbyList(){

		return hobbyRepo.findAll();
	}
	
	public void hobbyDataInsert(HobbyDataDto hdDto) {
		HobbyDataDelete(hdDto.getUserId());	//유저취미 업데이트하기 위해 취미 데이터를 먼저 싹 지우고 밑에서 다시 추가함
		String splitChoice = hdDto.getHobbyCd();	//dto로 취미코드 1,2,3 받은거 저장
		if (splitChoice != null) {
			
		String[] ArraysStr = splitChoice.split(",");
		HobbyDataDto hobbyDataDto = new HobbyDataDto();	//빈 Dto 만들어서 취미데이터 넣어줌
		for(String s : ArraysStr) {
			hobbyDataDto.setHobbyCd(s);
			hobbyDataDto.setUserId(hdDto.getUserId());
			HobbyDataEntity hobbyDataEntity = HobbyDataEntity.toHobbyDataEntity(hobbyDataDto); //Dto를 entity로 변환
			HobbyDataInsert(hobbyDataEntity); //취미데이터도 저장
		}
		}
	}
	
	public void HobbyDataInsert(HobbyDataEntity entity) {
		hobbyDataRepo.save(entity);
	}
	
	public List<HobbyDataEntity> HobbyDataView(String userId) {
		List<HobbyDataEntity> hobbyDataList = hobbyDataRepo.findByUserEntity_UserId(userId);
		return hobbyDataList;
	}
	
	public void HobbyDataDelete(String userId) {
		System.out.println(userId);
		hobbyDataRepo.deleteByUserEntity_UserId(userId);
	}
}
