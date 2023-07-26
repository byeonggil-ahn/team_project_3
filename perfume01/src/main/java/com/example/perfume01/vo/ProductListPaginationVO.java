package com.example.perfume01.vo;

import lombok.Data;
import org.apache.ibatis.javassist.compiler.ast.Keyword;

@Data
public class ProductListPaginationVO {

    private String column = "product_name"; //column의 디폴트를 product_name으로 설정
    private String keyword = "";    //keyword의 디폴트를 "" 으로 설정
    private int page = 1;
    private int size = 20;  // 전 화면에 효시할 데이터 수
    private int count;
    private int blockSize = 10; // 한 화면에 표시할 페이지 버튼 수 (1 ~ 10)

    // 검색인지 목록인지 판정
    public boolean isSearch() {
        return !keyword.equals("");
    }   // keyword가 공백이 아니면 검색
    public boolean isList() {
        return !isSearch();
    }   // 아니라면 목록

    // 파라메터 생성
    // : 목록일 경우 list?size=XX 형태의 문자열 반환
    // : 검색일 경우 size=XX&column=XX&keyword=XX 형태의 문자열 반환
    public String getParameter() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("&size=");
        buffer.append(size);
        // 만약 검색이라면 항목을 추가
        if (isSearch()) {
            buffer.append("&column=");
            buffer.append(column);
            buffer.append("&keyword");
            buffer.append(keyword);
        }
        //위에서 조합된 buffer를 문자열로 반환
        return buffer.toString();
    }

    // 종료행 번호 계산 : 한페이지에 표시되는 최종 데이터 번호
    public int getEnd() {
        return Math.min(page * size, count);
    }

    // 시작행 번호 계산 : 한페이지에 표시되는 첫 데이터 번호
    public int getBegin() {
        return page * size - (size - 1);
    }

    // 전체 페이지
    public int getTotalPage() {
        return (count + size -1) / size;
    }

    // 시작 블록 번호 : 한 페이지에 표시되는 첫번째 페이지 블록번호
    public int getStartBlock() {
        return (page - 1) / blockSize * blockSize + 1;
    }

    // 종료 블록번호 : 한페이지에 표시되는 마지막 페이지 블록번호
    public int getFinishBlock() {
        int value = (page - 1) / blockSize * blockSize + blockSize;
        return Math.min(value, getTotalPage());
    }

    // 첫페이지 확인
    public boolean isFirst() {
        return page == 1;
    }

    // 마지막 페이지 확인
    public boolean isLast() {
        return page == (count + size - 1) / size;
    }

    // [이전] 버튼이 나오는 조건 -> 시작 블록이 1보다 크면 (페이지가 size보다 크면)
    public boolean isPrev() {
        return getStartBlock() > 1;
    }

    // [다음] 버튼이 나오는 조건 -> 종료 블록이 마지막 페이지보다 작으면
    public boolean isNext() {
        return getFinishBlock() < getTotalPage();
    }

    // [이전]을 클릭하면 나올 페이지 번호
    public int getPrevPage() {
        return getStartBlock() - 1;
    }

    // [다음]을 클릭하면 나올 페이지 번호
    public int getNextPage() {
        return getFinishBlock() + 1;
    }

}


