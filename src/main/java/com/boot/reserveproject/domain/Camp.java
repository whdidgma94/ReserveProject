package com.boot.reserveproject.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "camp")
public class Camp {
    @Id
    @Column(name = "contentId")
    private Long contentId; // 콘텐츠 id ( 이미지검색 api에 필요, 중복x )
    private String facltNm; // 숙소 이름
    private String lineIntro; // 숙소 부제
    @Column(length = 5000)
    private String intro; // 숙소 소개

    private String facltDivNm; // 사업 주체 ( 민간, 위탁 )   //
    private String manageSttus; //  운영 상태 ( 운영중인지 표시 )
    private String hvofBgnde; //  휴장기간.휴무기간 시작일
    private String hvofEnddle; //  휴장기간.휴무기간 종료일
    private String induty; //  업종                     ------ 카라반, 일반, 자동차
    private String lctCl; //  입지 ( 주변 입지 ex.호수 )    ------ 산 숲 계곡 해변 강 호수

    private String doNm; //  도 ( 주소 )
    private String sigunguNm; //  시군구 ( 주소 )
    private String zipcode; //  우편번호 ( 주소 )
    private String addr1; //  주소 ( 주소 )
    private String addr2; //  상세주소 ( 주소 )

    private double mapX; //  경도 : X
    private double mapY; //  위도 : Y

    private String direction; //  오시는길
    private String tel; //  전화번호
    private String homepage; //  홈페이지

    private String siteCount; // 주요시설 , 야영시설개수
    private Long sitedStnc; // 사이트 간격
    private String site; // 사이트 별 크기
    // 사이트 종류가 없는 경우도 있음
    private String siteBottom; // ( 바닥형태)
    // 형태 종류가 각각 다 다름, 사이트 면적만있고 형태 없는 경우도 있음

    private String tooltip; // 툴팁 ( 주변 환경 설명 ex.관광지,음식점 )
    // 없는 경우도 있음
    private String glampInnerFclty; // 글램핑 내부 시설 ( 옵션 )
    private String caravInnerFclty; // 카라반 내부 시설 ( 옵션 )
    // 없는 경우도 있음
    private String operPdCl; // 운영기간 ( 봄,여름,가을,겨울 *겨울 없는경우 있음)
    private String operDeCl; // 운영일 ( 평일+주말 )
    private String trlerAcmpnyAt; // 개인 트레일러 동반여부 (Y,N)
    private String caravAcmpnyAt; // 개인 카라반 동반여부 (Y,N)

    private String sanitary; // 위생시설 개수 ( 부대시설 )

    private String brazierCl; // 화로대 ( ex.개별 ) ( 부대시설 )
    private String sbrsCl; // 부대시설 (기본적으로 있는시설)      ----- 옵션
    private String sbrsEtc; // 기타부대시설 (차별화된시설)

    private String fpSystem; // 안전시설현황

    private String posblFcltyCl; // 주변이용가능시설
    private String posblFcltyEtc; // 기타주변이용가능시설
    private String clturEventAt; // 자체문화행사 여부 (Y,N)
    private String clturEvent; // 자체문화행사명
    private String exprnProgrmAt; // 체험프로그램 여부 (Y,N)            ------- 체험
    private String exprnProgrm; // 체험프로그램종류

    private String themaEnvrnCl; // 테마환경 ( ex.낚시,물놀이 등 )    -------  테마종류
    private String animalCmgCl; // 애완동물 출입 ( ex.가능, 소형견가능 등 )  -------  애완동물
    private String firstImageUrl; // 대표이미지

}
