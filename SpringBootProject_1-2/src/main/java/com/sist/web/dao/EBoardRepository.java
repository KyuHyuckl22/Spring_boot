package com.sist.web.dao;
import java.util.*;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.*;
@Repository
public interface EBoardRepository extends ElasticsearchRepository<EBoard, Integer>{
	// 상세보기
	// 전체 데이터 검색 findAll()  이라고 이미 만들어진 함수
	// 수정, 추가  시키는 함수 save()
	// 삭제 시키는 함수 delete()   
	
}
