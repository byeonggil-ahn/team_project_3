package com.example.perfume01.criteria;

import lombok.Data;

@Data
public class Criteria {
    private int rowsPerPage; //1Page에 출력할 Row 갯수
    private int currPage; //현재 출력(요청) Page
    private int sno; //start Row 순서번호: 계산필요
    private int eno; //Oracle만 사용

    public Criteria() {
        this.rowsPerPage=5;
        this.currPage=1;
    }
    public void setCurrPage(int currPage) {
        if ( currPage>1 ) this.currPage=currPage;
        else this.currPage=1;
    }
    public void setRowsPerPage(int rowsPerPage) {
        if ( rowsPerPage>5 && rowsPerPage<51 )
            this.rowsPerPage=rowsPerPage;
        else this.rowsPerPage=5;
    }
    public void setSnoEno() {
        if ( this.sno<1 ) this.sno=1;
        this.sno=(this.currPage-1)*this.rowsPerPage;
    }

} //class

