package com.ds.project01.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ds.project01.domain.DeptEntity;
import com.ds.project01.domain.HobbyEntity;
import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.UserDto;
import com.ds.project01.service.BT_UserService;

@RestController
public class BT_UserController {

	@Autowired
	private BT_UserService service;
	
	
	@GetMapping("/bt/deptList")
	public List<DeptEntity> user_deptList() {
		
		return service.deptList();
	}
	
	@GetMapping("/bt/hobbyList")
	public List<HobbyEntity> user_hobbyList() {
		
		return service.hobbyList();
	}
	

	@PostMapping("/user/save")
	public String user_save(UserDto dto, HobbyDataDto hdDto) {
		service.insert(dto, hdDto);
		
		return "redirect:/user/write";
	}

	@GetMapping("/bt/list")
	public Model admin_list(UserDto dto, Model model, String searchKeyword) {
		
		model.addAttribute("hobbyList", service.hobbyList());
		model.addAttribute("deptList", service.deptList());
		model.addAttribute("adminList", service.adminList(searchKeyword));
		return model;
	}
	
	//검색한 리스트 화면을 고정한 상태로 view를 보이고 싶었음. 그래서 비동기인 ajax를 사용하기 위해 json형태로 데이터를 받으니 @ResponseBody
	@ResponseBody
	@GetMapping("/admin/view")
	HashMap<String, Object> userView(UserDto userDto, Model model){ //ajax로 보낸  userID 정보를 userDto에 담아서 받아옴 
		HashMap<String, Object> map = new HashMap<>(); //attribute로 html 태그 안으로 데이터를 넘길 때는 Model, script 태그 안에는 map
		
		String userId =userDto.getUserId();
		for (int i = 0; i < service.HobbyDataView(userId).size(); i++) {
			map.put("userHobbyChoice"+i, service.HobbyDataView(userId).get(i).getHobbyEntity().getHobbyCd()); //각각 여러 취미데이터를 다른 아이디로 저장
		}
		map.put("deptList", service.deptList()); //받아온 부서 데이터 저장 후 script에서 불러낼것
		map.put("getUerId",service.view(userDto).getUserId());
		map.put("getUserNm",service.view(userDto).getUserNm());
		map.put("getUserEmlAddr",service.view(userDto).getUserEmlAddr());
		map.put("getUserTelno",service.view(userDto).getUserTelno());
		map.put("getUserDeptNo",service.view(userDto).getDeptEntity().getDeptNo());
		map.put("getUserAprvYn",service.view(userDto).getUserAprvYn());
		
		return map;
	}
	
	@PostMapping("/admin/delete")
	public String user_delete(UserDto dto) {
		service.delete(dto);
		return "redirect:admin/list";
	}
	
}