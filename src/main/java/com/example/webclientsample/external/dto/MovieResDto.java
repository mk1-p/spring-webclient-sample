package com.example.webclientsample.external.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MovieResDto {
    private String movieCd;
    private String movieNm;
    private String movieNmEn;
    private String showTm;
    private String openDt;
    private String prdtYear;
    private String prdtStatNm;
    private List<NationResDto> nations;
    private List<GenreResDto> genres;
    private List<DirectorResDto> directors;
    private List<ActorResDto> actors;
    private List<CompanyResDto> companys;
    private List<AuditResDto> audits;
    private List<StaffResDto> staffs;
}
