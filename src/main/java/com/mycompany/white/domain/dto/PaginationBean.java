package com.mycompany.white.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PaginationBean {

    private int min;
    private int max;
    private int currentPage;
    private int prevPage;
    private int nextPage;
    private int totPageCnt;

    // totCnt : 전체 글 갯수,
    // page : 현재 페이지 번호
    // contentPageCnt : 페이지당 글의 갯수
    // paginationCnt : 한 페이지당 버튼의 갯수
    public PaginationBean(int totCnt, int page, int contentPageCnt, int paginationCnt) {

        // 현재 페이지 번호
        this.currentPage = page;

        // 전체 페이지 갯수
        this.totPageCnt = totCnt / contentPageCnt;
        if (totCnt % contentPageCnt > 0) totPageCnt ++;

        this.min = (((currentPage - 1) / paginationCnt) * paginationCnt) + 1;
        this.max = (min + paginationCnt) - 1;
        if (max > totPageCnt) max = totPageCnt;

        this.prevPage = min - 1;
        this.nextPage = max + 1;
        if (nextPage > totPageCnt) nextPage = totPageCnt;

    }
}
