package com.example.webclientsample.external.application;


import com.example.webclientsample.external.dto.MovieInfoResponse;
import com.example.webclientsample.external.dto.MovieListResponse;
import com.example.webclientsample.external.type.PathType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KoficApiService {

    @Value("${secret.kofic-key}")
    private String SERVICE_KEY;
    private WebClient webClient = null;


    @PostConstruct
    public void initWebClient() {
        log.info("WebClient Setting Complete!");
        webClient = WebClient.builder()
                .baseUrl("https://www.kobis.or.kr/kobisopenapi/webservice/rest")
                .build();
    }

    /**
     * Movie List External API Request
     * @param curPage
     * @param itemPerPage
     * @return
     */
    public Mono<MovieListResponse> getMoviesFromApi(int curPage, int itemPerPage) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(PathType.MOVIE_LIST.getPath())
                                .queryParam("key", SERVICE_KEY)
                                .queryParam("curPage",curPage)
                                .queryParam("itemPerPage",itemPerPage)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MovieListResponse.class)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(3)));


    }

    /**
     * Movie Detail External API Request
     * @param movieCd
     * @return
     */
    public Mono<MovieInfoResponse> getMovieDetailFromApi(String movieCd) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path(PathType.MOVIE_DETAIL.getPath())
                                .queryParam("key", SERVICE_KEY)
                                .queryParam("movieCd", movieCd)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(MovieInfoResponse.class);

    }


    /**
     * Movie Request Flow Method
     * @param curPage
     * @param itemPerPage
     * @return
     */
//    public void getMovies(int curPage, int itemPerPage) {
//
//        // 영화 리스트 호출
//        // -> movies
//        // movies Mono -> Flux MovieDetail
//        // result list
//        // insert Movie Info to DB
//
//        // Request Info Data
//        RequestInfoDto requestInfo = RequestInfoDto.builder()
//                .request(RequestType.KOPIC_MOVIE)
//                .process(ProcessType.ONGOING)
//                .build();
//        RequestInfoDto savedRequestInfo = requestInfoService.saveRequestInfo(requestInfo);
//
//        // Movie List
//        getMoviesFromApi(curPage, itemPerPage)
//                .doOnSuccess(movieListResponse -> {
//                    // Movie List -> Each Movie Detail Info Request
//                    List<MovieResDto> movies = movieListResponse.getMovieListResult().getMovieList();
//                    List<String> movieCds = movies.stream()
//                            .map(MovieResDto::getMovieCd)
//                            .collect(Collectors.toList());
//
//                    List<MovieResDto> movieInfos = new ArrayList<>();
//
//                    // Mono List -> Flux
//                    Flux<MovieInfoResponse> movieDetailFlux = Flux.fromIterable(movieCds)
//                            .flatMap(this::getMovieDetailFromApi);
//
//                    // Flux Subscribe
//                    movieDetailFlux.subscribe(result -> {
//                        // Movie Detail 리스트화
//                        MovieResDto movieInfo = result.getMovieInfoResult().getMovieInfo();
//                        movieInfos.add(movieInfo);
//                    }, error -> {
//                        // 요청 실패에 대한 처리
//                        log.error("Failed Request : {}",error.getMessage());
//                        savedRequestInfo.setProcess(ProcessType.FAIL);
//                        requestInfoService.saveRequestInfo(savedRequestInfo);
//                    },() -> {
//                        // 모든 요청이 끝나면 데이터 저장
//                        log.info("DB Insert!!");
//                        try {
//                            saveKoficMovieInfo(movieInfos);
//                            savedRequestInfo.setProcess(ProcessType.COMPLETE);
//                        }catch (Exception e) {
//                            log.error("Failed Insert Data : {}",e.getMessage());
//                            savedRequestInfo.setProcess(ProcessType.FAIL);
//                        } finally {
//                            requestInfoService.saveRequestInfo(savedRequestInfo);
//                        }
//                    });
//
//                })
//                .subscribe();
//
//    }

}
