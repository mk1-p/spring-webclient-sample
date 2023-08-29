package com.example.webclientsample.external.type;

import lombok.Getter;

@Getter
public enum PathType {


    DAILY_BOX_OFFICE("/boxoffice/searchDailyBoxOfficeList.json","일간 박스오피스 조회"),
    WEEKLY_BOX_OFFICE("/boxoffice/searchWeeklyBoxOfficeList.json","주간 박스오피스 조회"),
    COMMON_CODE("/code/searchCodeList.json","공통 코드 조회"),
    MOVIE_LIST("/movie/searchMovieList.json","영화 목록 조회"),
    MOVIE_DETAIL("/movie/searchMovieInfo.json","영화 상세정보 조회"),

    ;


    private String path;
    private String desc;

    PathType(String path, String desc) {
        this.path = path;
        this.desc = desc;
    }
}
