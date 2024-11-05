package com.sist.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class MainRestController {
	@GetMapping("/names")
	public List<String> main_names(){
		List<String> list = new ArrayList<>();
		list.add("홍길동");
		list.add("고길동");
		list.add("둘리");
		list.add("도우너");
		list.add("연우서");
		
		return list;
	}
}
