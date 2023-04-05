package com.boot.reserveproject.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "camp")
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campId")
    private Long id;
    private Integer contentId; // 콘텐츠 id ( 이미지검색 api에 필요 )
    private Long facltNm; // 숙소 이름
    private Long lineIntro; // 숙소 부제
    private Long intro; // 숙소 소개
    private Long allar; // 숙소 면적

    private Long mgcDiv; // 운영 기관 ( 민간, 위탁 )
    private Long manageSttus; //  운영 상태 ( 운영중인지 표시 )
    private Long hvofBgnde; //  휴장기간.휴무기간 시작일
    private Long hvofEnddle; //  휴장기간.휴무기간 종료일
    private Long featureNm; //  특징 ( 짧은설명글 )
    private Long induty; //  업종
    private Long lctCl; //  입지 ( 주변 입지 ex.호수 )

    private Long doNm; //  도 ( 주소 )
    private Long sigunguNm; //  시군구 ( 주소 )
    private Long zipcode; //  우편번호 ( 주소 )
    private Long addr1; //  주소 ( 주소 )
    private Long addr2; //  상세주소 ( 주소 )
    private Long mapX; //  경도 : X
    private Long mapY; //  위도 : Y
    private Long direction; //  오시는길
    private Long tel; //  전화번호
    private Long homepage; //  홈페이지
    private Long resveUrl; //  예약페이지 링크
    private Long resveCl; //  예약방법 ( 전화, 온라인 등 )

    private Long gnrlSiteCo; //  야영장개수 ( 주요시설 )
    private Long autoSiteCo; //  자동차야영장개수 ( 주요시설 )
    private Long glampSiteCo; //  글램핑개수 ( 주요시설 )
    private Long caravSiteCo; //  카라반개수 ( 주요시설 )
    private Long indvdlCaravSiteCo; //  개인카라반개수 ( 주요시설 )

    private Long sitedStnc; //  간격
    private Long siteMg1Width; //  가로크기 ( 사이트1 )
    private Long siteMg1Vrticl; //  세로크기 ( 사이트1 )
    private Long siteMg1Co; //  사이트개수 ( 사이트1 )
    private Long siteMg2Width; //  가로크기 ( 사이트2 )
    private Long siteMg2Vrticl; //  세로크기 ( 사이트2 )
    private Long siteMg2Co; //  사이트개수 ( 사이트2 )
    private Long iteMg3Width; //  세로크기 ( 사이트3 )
    private Long siteMg3Vrticl; //  가로크기 ( 사이트3 )
    private Long siteMg3Co; //  사이트개수 ( 사이트3 )
    // 사이트 종류가 각각 다 다름, 없는 경우도 있음
    private Long siteBottomCl1; // 잔디 ( 바닥형태 단위:면 )
    private Long siteBottomCl2; // 파쇄석 ( 바닥형태 단위:면 )
    private Long siteBottomCl3; // 데크 ( 바닥형태 단위:면 )
    private Long siteBottomCl4; // 자갈 ( 바닥형태 단위:면 )
    private Long siteBottomCl5; // 맨흙 ( 바닥형태 단위:면 )
    // 형태 종류가 각각 다 다름, 없는 경우도 있음

    private Long tooltip; // 툴팁 ( 주변 환경 설명 ex.관광지,음식점 )
    // 없는 경우도 있음
    private Long glampInnerFclty; // 글램핑 내부 시설 ( 옵션 )
    private Long caravInnerFclty; // 카라반 내부 시설 ( 옵션 )
    // 없는 경우도 있음
    private Long operPdCl; // 운영기간 ( 봄,여름,가을,겨울 *겨울 없는경우 있음)
    private Long operDeCl; // 운영일 ( 평일+주말 )
    private Long trlerAcmpnyAt; // 개인 트레일러 동반여부 (Y,N)
    private Long caravAcmpnyAt; // 개인 카라반 동반여부 (Y,N)

    private Long toiletCo; // 화장실 개수 ( 부대시설 )
    private Long swrmCo; // 샤워실 개수 ( 부대시설 )
    private Long wtrplCo; // 개수대 개수 ( 부대시설 )
    private Long brazierCl; // 화로대 ( ex.개별 ) ( 부대시설 )
    private Long sbrsCl; // 부대시설
    private Long sbrsEtc; // 기타부대시설

    private Long extshrCo; // 소화기 개수 ( 소방시설 )
    private Long frprvtWrppCo; // 방화수 개수 ( 소방시설 )
    private Long frprvtSandCo; // 방화사 개수 ( 소방시설 )
    private Long fireSensorCo; // 화재감지기 개수 ( 소방시설 )

    private Long posblFcltyCl; // 주변이용가능시설
    private Long posblFcltyEtc; // 기타주변이용가능시설
    private Long clturEventAt; // 자체문화행사 여부 (Y,N)
    private Long clturEvent; // 자체문화행사명
    private Long exprnProgrmAt; // 체험프로그램 여부 (Y,N)
    private Long exprnProgrm; // 체험프로그램종류

    private Long themaEnvrnCl; // 테마환경 ( ex.낚시,물놀이 등 )
    private Long animalCmgCl; // 애완동물 출입 ( ex.가능, 소형견가능 등 )
    private Long firstImageUrl; // 대표이미지
}
