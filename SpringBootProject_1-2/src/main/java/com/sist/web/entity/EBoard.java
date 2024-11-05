package com.sist.web.entity;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;
// DB => @Entity, Elasticsearch 에 들어갈때는 @Document 를 사용해야한다
@Document(indexName = "eboard")
@Data
public class EBoard {
	private int id;
	private int hit;
	private String name,subject,content,pwd, regdate;
}
