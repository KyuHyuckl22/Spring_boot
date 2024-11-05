package com.sist.web.restcontroller;

import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.EBoardRepository;
import com.sist.web.entity.*;
import org.springframework.data.domain.Pageable;

/*				 => 모바일 (kotlin, flutter)
				 => python
				 => 호환성이 좋은 파일 : xml
				 => 자동으로 JSON 출력
				 => Rest API => CRUD 
				 	GET / POST / PUT / DELETE
				 데이터 읽기  |		  |       |
				 		데이터 추가  |       |
				 				데이터 수정  |
				 				        데이터 삭제
*/
@RestController // => 다른 언어와 연결할 때 사용 : javascript(ajax, vue, react)
@CrossOrigin(origins = "*")
public class EBoardRestController {
	@Autowired
	private EBoardRepository bdao;
	

	@GetMapping("eboard/list_react")
	public Map eboard_list(int page) {
	    int rowSize = 10;
	    if (page == 0) {
	    	page = 1;
	    }
	    Pageable pg = PageRequest.of(page - 1, rowSize, Sort.by(Sort.Direction.DESC, "id")); // 페이지 번호는 0부터 시작
	    Page<EBoard> pList = bdao.findAll(pg); // 정렬 후에 데이터 가져오기

	    List<EBoard> list = new ArrayList<>();
	    if (pList.hasContent()) { // pList에 값이 있는 경우
	        list = pList.getContent(); // Page 객체를 List로 변환
	    }
	    int count = (int)bdao.count();
	    int totalpage = (int)(Math.ceil(count / 10.0));
	    Map map = new HashMap();
	    map.put("list", list);
	    map.put("curpage", page);
	    map.put("totalpage", totalpage);
	    map.put("today", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    return map;
	}
	
	@PostMapping("eboard/insert_react")
	public String eboard_insert(EBoard vo) {
		String result = "";
		try {
//			1. id , 2. hit, 3. regdate
			vo.setHit(0);
			vo.setRegdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			vo.setId(idMaxData());
			bdao.save(vo);
			result = "yes";
		}catch(Exception ex) {
			result = ex.getMessage();
		}
		return result;
	}	
	
	public int idMaxData() {
		int max = 0;
		try {			
			int rowSize = 10;
			int start = 0;
			Pageable pg = PageRequest.of(start, rowSize, Sort.by(Sort.Direction.DESC, "id")); // 페이지 번호는 0부터 시작
			Page<EBoard> pList = bdao.findAll(pg); // 정렬 후에 데이터 가져오기
			List<EBoard> list = new ArrayList<>();
			if (pList.hasContent()) { // pList에 값이 있는 경우
				list = pList.getContent(); // Page 객체를 List로 변환
				max = list.get(0).getId();
			}
		}catch(Exception ex) {
			max = 0;
		}
	    
	    return max + 1;
	}
//	상세보기 
	@GetMapping("eboard/detail_react")
	public EBoard eboard_detail(int id) {
		EBoard vo = bdao.findById(id).get();
		vo.setHit(vo.getHit()+1);
		bdao.save(vo);
//		hit 수 증가
		vo = bdao.findById(id).get();
		return vo;
		
	}
//	수정하기
	@GetMapping("eboard/update_react")
	public EBoard eboard_update(int id) {
		EBoard vo = bdao.findById(id).get();
		
		return vo;
	}
	@PostMapping("eboard/update_ok_react")
	public String board_update_ok(EBoard vo) {
		String result = "";
		EBoard dbvo = bdao.findById(vo.getId()).get();
		if(dbvo.getPwd().equals(vo.getPwd())) {
			vo.setHit(dbvo.getHit());
			vo.setRegdate(dbvo.getRegdate());
			bdao.save(vo);
			result = "yes";			
		} else {
			result = "no";
		}
		return result;
			
	}
	
	@GetMapping("eboard/delete_ok_react")
	public String eboard_delete(int id, String pwd) {
		String reslut = "";
		EBoard vo = bdao.findById(id).get();
		if(vo.getPwd().equals(pwd)) {
			bdao.delete(vo);
			reslut = "yes";
		}else {
			reslut = "no";
		}
		return reslut;
		
	}
	
	
	
	 	
	
}











