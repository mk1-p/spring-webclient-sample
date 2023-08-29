package com.example.webclientsample.external.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInfoResponse {
    private MovieInfoResult movieInfoResult;

    @Getter
    @Setter
    public static class MovieInfoResult {
        private MovieResDto movieInfo;
    }

}

