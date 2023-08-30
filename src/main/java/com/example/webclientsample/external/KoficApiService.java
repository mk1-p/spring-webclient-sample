package com.example.webclientsample.external;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
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


    public Mono<String> getMovieDetailFromApi(String movieCd) {
        return webClient.get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/movie/searchMovieInfo.json")
                                .queryParam("key", SERVICE_KEY)
                                .queryParam("movieCd", movieCd)
                                .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);

    }
}
