package com.likelion.giljobe.service;

import com.likelion.giljobe.domain.EducationEnum;
import com.likelion.giljobe.domain.JobStatusEnum;
import com.likelion.giljobe.domain.ResidenceEnum;
import com.likelion.giljobe.dto.PolicyDetailResponseDto;
import com.likelion.giljobe.dto.PolicyListRequestDto;
import com.likelion.giljobe.dto.PolicyListResponseDto;
import com.likelion.giljobe.dto.PolicySaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PolicyServiceTest {

    @Autowired
    private PolicyService policyService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private JobStatusService jobStatusService;

    @Autowired
    private ResidenceService residenceService;

    @Test
    void save() {
        // given
        createEducation();

        createJobStatus();

        createResidence();

        PolicySaveRequestDto requestDto1 = new PolicySaveRequestDto(
                "송파 청년창업도전 프로젝트",
                "유망한 창업아이템 및 기술을 보유한 청년(예비)창업자에게 사업비를 지원하여 양질의 일자리창출과 청년 자립 지원",
                "R2023040305640",
                "023010",
                "",
                "",
                "",
                "",
                19,
                39,
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                ""
        );

        PolicySaveRequestDto requestDto2 = new PolicySaveRequestDto(
                "청년친화강소기업 선정",
                "중소기업 인식개선 및 괜찮은 일자리로의 조기 입직을 유도하기 위해 청년이 선호하는 근로여건을 갖춘 청년친화강소기업 심사·선정 및 기업정보 제공",
                "R2023031900016",
                "023010",
                "1. 청년 지원내용\n" +
                        "강소기업(고용유지율과 신용평가 등급이 높고 최근 3년 이내 산재사망 발생이 없는 기업 등을 기준으로 선정된 우수한 중소기업) 중 임금, 일생활균형, 고용안정이 우수하여 청년들이 근무할만한 중소기업을 평가하고 청년친화강소기업으로 선정하여 관련 정보 제공\n" +
                        "\n" +
                        "2. 기업 지원내용\n" +
                        " 1) 기업 홍보 : 현장밀착형 맞춤 홍보, 워크넷 강소기업 정보제공 채널 확대\n" +
                        " 2) 재정·금융 지원 : 청년 일자리 지원사업 금융우대, 고용창출장려금·고용안정장려금 지원 선정시 우대, 산재예방시설 융자금 지원 산정시 우대, 클린사업장 조성지원 우대, 공유재산 수의계약 임대 및 임대료 감경, 중소기업 지원사업 신청시 우대\n" +
                        " 3) 선정·선발 우대 : 청년취업아카데미 참여기업 선정 우대, 일학습병행제 참여기업 선정 관련 우대, 산업통상자원부 해외지사화 산업 선정시 우대, 중소기업 탐방 기업 선정시 우대, 청년채용박람회 참여기업 선정시 우대\n" +
                        " 4) 세무조사 제외 : 일자리 창출 우수기업에 대한 정기 세무 조사 대상 제외 시 우대\n" +
                        " 5) 병역특례 지원 : 병역지정업체(산업기능요원) 신청 선정심사 시 가점",
                "연중",
                "신청공고문 참조",
                "-",
                null,
                null,
                "",
                "제한없음",
                "",
                "제한없음",
                "",
                "제한없음",
                "제한없음",
                "제한없음",
                "제외 기업 요건\n" +
                        "3년 이내 근로기준법 43조의 2 및 시행령 23조의 2에 따른 체불명단 공개기업(사업주)\n" +
                        "3년 이내 2회 연속 동종업종*규모별 평균 대비 '고용유지율'이 낮은 기업\n" +
                        "3년 이내 '산재사망' 발생기업\n" +
                        "'신용평가등급'이 B-미만 기업\n" +
                        "상호출자제한기업 집단 및 공기업\n" +
                        "10인 미만 기업(건설업 30인 미만)\n" +
                        "소비향락업(호텔업과 휴양콘도업은 선정대상 포함), 부동산업 등\n" +
                        "대통령령으로 정하는 업종(중소기업인력지원특별법), 고용알선업, 인력공급업",
                "별도없음"
        );

        PolicySaveRequestDto requestDto3 = new PolicySaveRequestDto(
                "전북형 청년취업 지원사업",
                "청년층 미취업자를 대상으로 중소기업 등의 수습기회를 제공함으로 직장경력 및, 정규직으로 취업가능성을 제고하여 기업의 청년층 고용촉진",
                "R2023070716221",
                "023010",
                "실시기업에 대하여 최장 12개월(수습 3개월, 정규직 전환 시 9개월)동안 채용인원 1인당 월 70만원 지원, 청년취업자에게 지원개시일부터 6개월, 12개월, 24개월 경과 시 각100만원(총300만원)씩 분할 지급",
                "2023.01.01.~2023.12.31.",
                "2023.01.04.(수) ~ 2023.01.11.(수)",
                "25명",
                18,
                39,
                "JEONBUK",
                "전라북도에 거주중인 미취업자 또는 채용기업에서 6개월 미만 근무자",
                "",
                "제한없음",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음",
                "정규직 전환시 (70만원 ×9개월) 추가지원(최대 12개월 지원)",
                "참여기업의 3개월 이상 근무자, 외국인, 다른 재정지원 사업에 중복 참여자"
        );

        PolicySaveRequestDto requestDto4 = new PolicySaveRequestDto(
                "대학진학 축하금 지원",
                "대학 생활비, 주거비 목적의 교육에 소요되는 경제적 부담 경감, 지역 인재의 고등 교육기회 보장으로 우수인재 양성 및 애향심 고취",
                "R2023070716044",
                "023010",
                "학생 1명당 200만원(총 1회에 한 함)",
                "2023.01.01. ~ 2023.12.31.",
                "1차 : 2023.03.02 ~ 2023.03.17\n" +
                        "2차 : 2023.03.20 ~ 2023.04.14",
                "185명",
                0,
                99,
                "JEONBUK",
                "관내 고등학교를 졸업 또는 고졸 검정고시 합격 후 3년이내 대학교에 진학한 자\n" +
                        "- 신청기준일 현재 본인과 보호자가 순창군에 주민등록이 되어 있는 자\n" +
                        "- 고졸 검정고시 합격자는 합격 당시 1년 이상 순창군에 주민등록이 되어있는 자만 해당",
                "COLLEGE_STUDENT",
                "대학 재학",
                "",
                "제한없음",
                "제한없음",
                "제한없음",
                "해당 없음",
                "타 지역 고등학교 졸업자, 사이버 대학, 방송통신대학은 지원불가"
        );

        PolicySaveRequestDto requestDto5 = new PolicySaveRequestDto(
                "임실 청년 창업지원 프로젝트",
                "소멸위기지역에서의 신규 창업을 지원하여 지역기반기업 대응 및 추가 일자리 창출 유도를 통한 지역경제 활성화",
                "R2023070716031",
                "023010",
                "15백만원, 월 150만원 * 10개월",
                "2023.01.01.~2023.12.31.",
                "2023년 3월 21일 ~ 2023년 3월 31일\n" +
                        "추가 : 2023. 7. 12.(수) ~  모집완료시까지",
                "4",
                18,
                39,
                "JEONBUK",
                "1. 사업개시일 기준 만 18~39세 청년 중 미취업자\n" +
                        "2. 사업기간 동안 임실군 주민등록 유지\n" +
                        "(단, 청년 선발 과정에서 1차 공고 미달 시, 재공고부터 해당 지자체의 65세 이상 인구비율에 따라 만 46세 이내 또는 해당 지자체 조례가 정한 청년의 연령 범위 내에서 해당 지자체장의 판단에 따라 예외적으로 인정)",
                "",
                "제한없음",
                "",
                "제한없음",
                "제한없음",
                "제한없음",
                "null",
                "- 사업공고일 기준 고용보험 가입자 및 사업등록자(기존 공고 시작기간인 3월 21일 이전의 경우 제외대상임.)\n" +
                        " - 대학 또는 대학원 재학 중인 자\n" +
                        " - 국세 또는 지방세 체납자\n" +
                        " - 정부, 지방자치단체 등 유사사업 중복 참여자\n" +
                        " - 금융기관 등으로부터 금융 불량거래자로 규제중인자"
        );

        PolicySaveRequestDto requestDto6 = new PolicySaveRequestDto(
                "청년 스타트업 기업육성 콘텐츠기업 육성센터 조성",
                "콘텐츠분야 거점형 시설집적화로 실감콘텐츠 산업 클러스터구축 및 전문인재 양성",
                "R2023062915357",
                "023010",
                "콘텐츠분야 지역 인재매칭 인턴십 프로그램",
                "2023.01.01.~2023.12.31",
                "프로그램 별 상이",
                "제한없음",
                18,
                50,
                "CHUNGNAM",
                "도내 주민등록자 등",
                "",
                "제한없음",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음",
                "-",
                "취업일 현재 고등학교 또는 대학 재학, 휴학중인 자"
        );

        PolicySaveRequestDto requestDto7 = new PolicySaveRequestDto(
                "사회적경제 문화예술 청년창업지원 프로젝트",
                "문화예술분야 사회적경제 기업 창업아이템을 보유한 청년 창업가 지원",
                "R2023050912273",
                "023010",
                "1. 초기 창업을 위한 사업비 지원 사업 : 12팀(명) 내외 - 사업비(최소 20백만원 ~ 최대 30백만원), 창업 역량강화 아카데미 지원 등\n" +
                        "2. 사업 안정화를 위한 임차료 지원 사업 : 8팀(명) 내외 - 임차료(최대 5.4백만원/월 60만원 한도), 창업 역량강화 아카데미 지원 등",
                "2023.01.01.~2023.12.31.",
                "2023.01.30.~2022.02.24.",
                "총 20명(팀) 내외",
                19,
                39,
                "SEOUL",
                "서초구 거주 또는 활동하는 만 19세 이상 39세 이하 청년 중 문화/예술분야의 사회적경제기업 진입을 희망하는 예비/신규 창업자(팀)",
                "",
                "제한없음",
                "",
                "제한없음",
                "제한없음",
                "제한없음",
                "초기 창업자는 사업개시일 3년 미만인 자를 의미하며, 임차료 지원사업의 경우 서초구 내 사무실에 한정함",
                "정부지원사업에 참여제한으로 제재중인자 또는 기업"
        );

        PolicySaveRequestDto requestDto8 = new PolicySaveRequestDto(
                "청년 work-play 타운 운영",
                "청년 창업을 위한 인프라 확대로 일자리 창출 기여 및 역세권 청년 커뮤니티 거점 공간 확보로 청년의 사회 참여 활성화와 청년 창업 생태계 조성에 기여",
                "R2023050912269",
                "023010",
                "창업공간 독립형 13㎡ 5개사, 오픈형 1인기업 6개사",
                "상시",
                "상시",
                "청년창업기업 11개사",
                19,
                39,
                "SEOUL",
                "서울시 거주, 창업일 5년이내, 사업장소재지 서울",
                "",
                "제한없음",
                "ENTREPRENEUR",
                "(예비)창업자",
                "제한없음",
                "제한없음 (기술 및 지식창업)",
                "-",
                "-"
        );

        PolicySaveRequestDto requestDto9 = new PolicySaveRequestDto(
                "청년 work-play 타운 운영",
                "청년 창업을 위한 인프라 확대로 일자리 창출 기여 및 역세권 청년 커뮤니티 거점 공간 확보로 청년의 사회 참여 활성화와 청년 창업 생태계 조성에 기여",
                "R2023050912269",
                "023010",
                "창업공간 독립형 13㎡ 5개사, 오픈형 1인기업 6개사",
                "상시",
                "상시",
                "청년창업기업 11개사",
                19,
                39,
                "SEOUL",
                "서울시 거주, 창업일 5년이내, 사업장소재지 서울",
                "",
                "제한없음",
                "ENTREPRENEUR",
                "(예비)창업자",
                "제한없음",
                "제한없음 (기술 및 지식창업)",
                "-",
                "-"
        );

        PolicySaveRequestDto requestDto10 = new PolicySaveRequestDto(
                "대학생 일자리 체험사업",
                "여름방학을 이용하여 관내 대학생들에게 구정참여 기회 제공, 민간기업 인턴 경험을 통한 취업역량을 제고",
                "R2023051212588",
                "023010",
                "일비(7만원) 보상금 지원",
                "2023",
                "미정 (2022. 6월 중)",
                "20명",
                18,
                39,
                "BUSAN",
                "주민등록 주소지가 부산광역시 부산진구인 대학생",
                "COLLEGE_STUDENT",
                "대학 재학",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음 (기타(대학생)",
                "-",
                "주민등록상 부산진구에 1년 미만 거주\n" +
                        "\n" +
                        " 부산시내외 대학(교)에 재학(휴학)중인 자"
        );

        PolicySaveRequestDto requestDto11 = new PolicySaveRequestDto(
                "대학생 일자리 체험사업",
                "여름방학을 이용하여 관내 대학생들에게 구정참여 기회 제공, 민간기업 인턴 경험을 통한 취업역량을 제고",
                "R1323051212588",
                "023010",
                "일비(7만원) 보상금 지원",
                "2023",
                "미정 (2022. 6월 중)",
                "20명",
                18,
                39,
                "BUSAN",
                "주민등록 주소지가 부산광역시 부산진구인 대학생",
                "COLLEGE_STUDENT",
                "대학 재학",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음 (기타(대학생)",
                "-",
                "주민등록상 부산진구에 1년 미만 거주\n" +
                        "\n" +
                        " 부산시내외 대학(교)에 재학(휴학)중인 자"
        );

        PolicySaveRequestDto requestDto12 = new PolicySaveRequestDto(
                "대학생 일자리 체험사업",
                "여름방학을 이용하여 관내 대학생들에게 구정참여 기회 제공, 민간기업 인턴 경험을 통한 취업역량을 제고",
                "R1223051212588",
                "023010",
                "일비(7만원) 보상금 지원",
                "2023",
                "미정 (2022. 6월 중)",
                "20명",
                18,
                39,
                "BUSAN",
                "주민등록 주소지가 부산광역시 부산진구인 대학생",
                "COLLEGE_STUDENT",
                "대학 재학",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음 (기타(대학생)",
                "-",
                "주민등록상 부산진구에 1년 미만 거주\n" +
                        "\n" +
                        " 부산시내외 대학(교)에 재학(휴학)중인 자"
        );


        PolicySaveRequestDto requestDto13 = new PolicySaveRequestDto(
                "대학생 일자리 체험사업",
                "여름방학을 이용하여 관내 대학생들에게 구정참여 기회 제공, 민간기업 인턴 경험을 통한 취업역량을 제고",
                "R1023051212588",
                "023010",
                "일비(7만원) 보상금 지원",
                "2023",
                "미정 (2022. 6월 중)",
                "20명",
                18,
                39,
                "BUSAN",
                "주민등록 주소지가 부산광역시 부산진구인 대학생",
                "COLLEGE_STUDENT",
                "대학 재학",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음 (기타(대학생)",
                "-",
                "주민등록상 부산진구에 1년 미만 거주\n" +
                        "\n" +
                        " 부산시내외 대학(교)에 재학(휴학)중인 자"
        );

        PolicySaveRequestDto requestDto14 = new PolicySaveRequestDto(
                "대학생 일자리 체험사업",
                "여름방학을 이용하여 관내 대학생들에게 구정참여 기회 제공, 민간기업 인턴 경험을 통한 취업역량을 제고",
                "R1123051212588",
                "023010",
                "일비(7만원) 보상금 지원",
                "2023",
                "미정 (2022. 6월 중)",
                "20명",
                18,
                39,
                "BUSAN",
                "주민등록 주소지가 부산광역시 부산진구인 대학생",
                "COLLEGE_STUDENT",
                "대학 재학",
                "JOB_SEEKER",
                "미취업자",
                "제한없음",
                "제한없음 (기타(대학생)",
                "-",
                "주민등록상 부산진구에 1년 미만 거주\n" +
                        "\n" +
                        " 부산시내외 대학(교)에 재학(휴학)중인 자"
        );

        // when
        this.policyService.save(requestDto1);
        this.policyService.save(requestDto2);
        this.policyService.save(requestDto3);
        this.policyService.save(requestDto4);
        this.policyService.save(requestDto5);
        this.policyService.save(requestDto6);
        this.policyService.save(requestDto7);
        this.policyService.save(requestDto8);
        this.policyService.save(requestDto9);
        this.policyService.save(requestDto10);
        this.policyService.save(requestDto11);
        this.policyService.save(requestDto12);
        this.policyService.save(requestDto13);
        this.policyService.save(requestDto14);

        // then
        assertTrue(true);
    }

    private void createResidence() {
        residenceService.save(ResidenceEnum.ALL);
        residenceService.save(ResidenceEnum.SEOUL);
        residenceService.save(ResidenceEnum.BUSAN);
        residenceService.save(ResidenceEnum.DAEGU);
        residenceService.save(ResidenceEnum.INCHEON);
        residenceService.save(ResidenceEnum.GWANGJU);
        residenceService.save(ResidenceEnum.DAEJEON);
        residenceService.save(ResidenceEnum.ULSAN);
        residenceService.save(ResidenceEnum.GYOUNGGI);
        residenceService.save(ResidenceEnum.GANGWON);
        residenceService.save(ResidenceEnum.CHUNGBUK);
        residenceService.save(ResidenceEnum.CHUNGNAM);
        residenceService.save(ResidenceEnum.JEONBUK);
        residenceService.save(ResidenceEnum.JEONNAM);
        residenceService.save(ResidenceEnum.GYEONGBUK);
        residenceService.save(ResidenceEnum.GYEONGNAM);
        residenceService.save(ResidenceEnum.JEJU);
        residenceService.save(ResidenceEnum.SEJONG);
    }

    private void createJobStatus() {
        jobStatusService.save(JobStatusEnum.ALL);
        jobStatusService.save(JobStatusEnum.JOB_SEEKER);
        jobStatusService.save(JobStatusEnum.EMPLOYEE);
        jobStatusService.save(JobStatusEnum.ENTREPRENEUR);
        jobStatusService.save(JobStatusEnum.FREELANCER);
        jobStatusService.save(JobStatusEnum.SELF_EMPLOYED);
        jobStatusService.save(JobStatusEnum.TEMPORARY_WORKER);
    }

    private void createEducation() {
        educationService.save(EducationEnum.UNDER_HIGH_SCHOOL);
        educationService.save(EducationEnum.HIGH_SCHOOL_GRADUATE);
        educationService.save(EducationEnum.COLLEGE_STUDENT);
        educationService.save(EducationEnum.COLLEGE_GRADUATE);
        educationService.save(EducationEnum.DOCTORATE);
        educationService.save(EducationEnum.ALL);
    }

    @Test
    void findByBizId() {
        String bizId = "R2023051212588";

        PolicyDetailResponseDto response = this.policyService.findByBizId(bizId);

        assertEquals(response.getBizId(), bizId);
    }

    @Test
    void findByFilter() {
        PolicyListRequestDto policyListRequestDto = new PolicyListRequestDto(
                null,
                null,
                null,
                null,
                null
                );

        Pageable pageable = PageRequest.of(0, 12);

        Page<PolicyListResponseDto> response = this.policyService.findByFilter(policyListRequestDto, pageable);

        System.out.println("response.getSize() = " + response.getSize());
        System.out.println("response.getTotalPages() = " + response.getTotalPages());
        System.out.println("response.getTotalElements() = " + response.getTotalElements());
        System.out.println("response.nextPageable() = " + response.nextPageable());

//        assertEquals(3, response.getTotalElements());
        List<PolicyListResponseDto> list = response.getContent();
        System.out.println("list.size() = " + list.size());
    }
}