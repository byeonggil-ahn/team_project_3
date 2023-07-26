package com.example.perfume01.criteria;

import lombok.Data;

// ** SearchCriteria
// => 검색 조건의 항목들을 관리 

@Data
public class SearchCriteria extends Criteria {
	
	private String searchType;
	private String keyword;
	private String[] check;
} //class
