package com.ds.project01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.repository.DeptRepository;


@Service
@Transactional//여러개 쿼리 데이터 실행시 하나라도 실패하면 성공했던 쿼리도 다 취소 
public class BT_DeptService {
	
	@Autowired
	private DeptRepository deptRepo;
	
	public List<DeptEntity> deptList(){
		return deptRepo.findAll();
	}
	
}
