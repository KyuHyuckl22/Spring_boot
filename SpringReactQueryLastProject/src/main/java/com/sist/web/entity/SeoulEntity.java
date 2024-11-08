package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
NO int 
TITLE text 
POSTER text 
MSG text 
ADDRESS text
 */
@Entity(name = "seoul_location") // 일레스틱 서치 =  @Document(indexName="")
// table = > index
// row   = > document
@Data
public class SeoulEntity {
	@Id
	private int no;
	private String title, poster, msg, address;
}
