package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.dao.*;
@RestController
@CrossOrigin(origins = "*")
public class SeoulRestController {
	@Autowired
	private SeoulDAO sdao;
	
	@GetMapping("/seoul/list/{page}")
	public ResponseEntity<Map> seoul_list(@PathVariable("page") int page){
		Map map = new HashMap();
		try {
			int rowsize = 12;
			Pageable pg = PageRequest.of(page -1, rowsize, Sort.by(Sort.Direction.ASC,"no"));
			Page<SeoulEntity> pList = sdao.findAll(pg);
			
			List<SeoulEntity> list = new ArrayList<SeoulEntity>();
			if(pList != null && pList.hasContent()) {
				list = pList.getContent();
			}
//			총 페이지
			int totalpage = (int)(Math.ceil(sdao.count()/12.0));
			final int BLOCK = 10;
			int startpage = ((page -1 ) / BLOCK) + 1;
			int endpage = ((page - 1 ) / BLOCK ) * BLOCK;
			
			if(endpage > totalpage) {
				endpage = totalpage;
			}
			
			map.put("sList", list);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startpage", startpage);
			map.put("endpage", endpage);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			// onError 로 받을 수 있음
		}
		return new ResponseEntity<>(map, HttpStatus.OK);
				
	}
}
