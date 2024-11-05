package com.sist.web.vo;

import java.util.*;

import lombok.Data;
/*
	Spring => Spring - Boot
	최대한 XML 파일이 없게 => 어노테이션 중심
	FrameWork => Spring - Boot 에 포함
		=> 소스를 최소화 : 서버 역할만 수행할 수 있게 만듦
 */
@Data
public class EmpVO {
	private int emono,sal;
	private String ename, job, dbday;
	private Date hiredate;
}
