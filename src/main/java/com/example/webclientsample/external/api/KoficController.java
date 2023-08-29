package com.example.webclientsample.external.api;


import com.example.webclientsample.external.application.KoficApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/external/kofic")
@RequiredArgsConstructor
public class KoficController {

    private final KoficApiService koficApiService;

    @GetMapping("/api/movies")
    public ResponseEntity getTestMessage(@RequestParam(value = "page", required = false, defaultValue = "1") int curPage,
                                         @RequestParam(value = "size", required = false, defaultValue = "50") int itemPerPage) {
//        RequestInfoDto movies = koficApiService.getMovies(curPage, itemPerPage);
//        return ResponseEntity.ok().body(movies);

        return ResponseEntity.ok().body("Request OK!");

    }


}
