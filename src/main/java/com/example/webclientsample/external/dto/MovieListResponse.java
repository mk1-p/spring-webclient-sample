package com.example.webclientsample.external.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieListResponse {

    private MovieListResult movieListResult;

    @Getter
    @Setter
    @ToString
    public static class MovieListResult {

        private int totCnt;
        private String source;

        // MovieList 와 MovieDetail 조회 정보가 다르기 떄문에
        // 이 클래스에서는 공통요소만 사용할 것
        private List<MovieResDto> movieList;
    }

}

