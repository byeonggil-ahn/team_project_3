package com.example.perfume01.criteria;

import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

//** PageMaker : View 에 필요한 값을 완성
//=> 전체Row 갯수 (전체 Page수 계산위해 필요)
//=> view 에 표시할 첫 PageNo
//=> view 에 표시할 끝 PageNo
//=> 1Page당 표시할 pageNo갯수
//=> 출력 가능한 마지막 PageNo (totalRowsCount, rowsPerPage 로 계산)
//=> 이전 PageBlock 으로
//=> 다음 PageBlock 으로

@Data
public class PageMaker {
	
	private int totalRowsCount; //전체 Row 갯수: DB에서 읽어온다
	private int spageNo; //계산
	private int epageNo; //계산
	// ※ 주의 필드명이 ePage 처럼 두번째 알파벳이 대문자인 경우
	//    => setter, getter 는 setsPageNo , getsPageNo 형태로 만들어지기때문에
	//       Lombok.. 등등 과 규칙이다르므로 사용시 불편 
	//			-> 그러므로 대.소문자 섞어사용시 주의.  
	private int displayPageNo=3; //1Page당 표시할 pageNo갯수
	private int lastPageNo; //출력 가능한 마지막 PageNo: 계산
	private boolean prev; //이전 PageBlock 으로
	private boolean next; //다음 PageBlock 으로

	SearchCriteria criteria;

	// 1) Criteria
	public void setCriteria(SearchCriteria criteria) { //ver02
		this.criteria=criteria;
	}
	// 2) totalRowsCount
	// => 전체 Row 갯수: DB에서 읽어온다
	// => 나머지 필요값 계산 (totalRowsCount 를 이용해서 계산)  
	public void setTotalRowsCount(int totalRowsCount) {
		this.totalRowsCount=totalRowsCount;
		calcData();
	}
	// 3) 나머지 필요값 계산 (totalRowsCount 를 이용해서 계산) 
	// => spageNo, epageNo, lastPageNo, prev, next 계산
	public void calcData() {

		epageNo = (int)Math.ceil(criteria.getCurrPage()/(double)displayPageNo) * displayPageNo;
		spageNo = (epageNo-displayPageNo) + 1;

		
		// 3.2) lastPageNo, epageNo 확인 
		lastPageNo =(int)Math.ceil(totalRowsCount/(double)criteria.getRowsPerPage());
		if ( lastPageNo<epageNo ) epageNo=lastPageNo;
		
		// 3.3) prev, next  
		prev = spageNo==1 ? false : true;
		// 이전코드 오류나면 다시 원상복구 해주기
//		next = epageNo==lastPageNo ? false : true;

		next = epageNo < lastPageNo;
		
	} //calcData
	
	// 4) QueryString 자동 만들기
	// ** 패키지 org.springframework.web.util
	// => 웹개발에 필요한 많은 유틸리티 클래스 제공
	// => UriComponents , UriComponentsBuilder
	//	  Uri를 동적으로 생성해주는 클래스,
	//	  파라미터가 조합된 uri를 손쉽게 만들어줌
	// => ?currPage=7&rowsPerPage=10 이것을 만들어줌
	//	  ? 부터 만들어지므로 jsp Code에서 ? 포함하지 않도록 주의 	
	
	// ** ver01
	// => QueryString 자동생성 
	//    ?currPage=4&rowsPerPage=3 
	public String makeQuery(int currPage) {
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("currPage", currPage)
				.queryParam("rowsPerPage", criteria.getRowsPerPage())
				.build();
		return uriComponents.toString();
	} //makeQuery
	
	// ** ver02
	// => makeQuery + search 조건 추가 (Paging 시에도 조건이 유지되도록 해줘야함)
	// => ?curPage=1&rowsPerPage=5&searchType=t&keyword=Java
	
	// ** 배열Type check 처리 : Map 으로처리
	// => ?curPage=1&rowsPerPage=5&searchType=t&keyword=Java&check=admin&check=banana
	//    위의 쿼리스트링에서 check 부분은 몇개일지 모름
	// => UriComponents 에서 Multi Value 처리 :  queryParams(MultiValueMap<String, String> params) 
	
	// ** MultiValueMap
	// => 키의 중복이 허용됨 즉, 하나의 키에 여러 값을 받을 수 있음
	// => new LinkedMultiValueMap() 으로 생성, add("key","value")
	
	// ** Map (키중복 허용안됨) 과 비교 
	// => HashMap : 순서보장 안됨 
	// => TreeMap : key값 순서로 자동정렬
	// => LinkedHashMap : 입력순서보장
	
	public String searchQuery(int currPage) {
		// ** check 처리
		// => 배열 -> MultiValueMap 으로 -> UriComponents 의 queryParams에 적용
		// => MultiValueMap 생성
		MultiValueMap<String, String> checkMap = new LinkedMultiValueMap<String, String>();
		
		// => check에 선택값이 있는경우에만
		//    배열 check의 원소들을 checkMap 으로
		if ( criteria.getCheck() !=null && criteria.getCheck().length>0 ) {
			for (String c:criteria.getCheck()) {
				checkMap.add("check", c);
			} //for
		}else checkMap=null;
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("currPage", currPage)
				.queryParam("rowsPerPage", criteria.getRowsPerPage())
				.queryParam("searchType", criteria.getSearchType())
				.queryParam("keyword", criteria.getKeyword())
				.queryParams(checkMap)
				.build();
		return uriComponents.toString();
	} //searchQuery	
	
} //class
