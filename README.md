# 🗺️ TripSpot

<div align="center">

**국내 여행 정보 검색 플랫폼**

권역별 관광지 검색과 상세정보 확인, 주변 관광지 탐색을 지원하는 웹 플랫폼

[![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=flat&logo=github)](https://github.com/kittyjh19/Tripspot)

[📺 시연 영상](https://youtu.be/pPZgLRZAisU)

</div>

---

## 📋 목차

- [프로젝트 소개](#-프로젝트-소개)
- [주요 기능](#-주요-기능)
- [기술 스택](#-기술-스택)
- [담당 역할](#-담당-역할)
- [트러블 슈팅](#-트러블-슈팅)
- [프로젝트 성과](#-프로젝트-성과)

---

## 🎯 프로젝트 소개

**TripSpot**은 국내 여행 정보를 쉽게 찾고 탐색할 수 있는 웹 플랫폼입니다.

대한민국 지도를 기반으로 권역별 관광지를 조회하고, 카카오맵을 통해 로드뷰와 주변 관광지를 확인할 수 있으며, Tour API를 활용한 실시간 공공 관광지 정보를 제공합니다.

### 📅 프로젝트 정보

- **개발 기간**: 2025.03.27 ~ 2025.04.03 (1주)
- **팀 구성**: 6명
- **개발 역할**: 팀장 / Full-Stack 개발 (주요 기능 전반)
- **프로젝트 성격**: 세미 프로젝트 (빠른 프로토타입 개발)

---

## ✨ 주요 기능

### 🗺️ 권역별 관광지 조회
- 대한민국 지도 이미지 기반 인터랙티브 UI
- 4개 권역(수도권, 강원권, 충청권, 경상권) 클릭 선택
- Ajax를 통한 관광지 목록 동적 로딩
- 페이징 처리 (10개씩 표시)

### 🔍 검색 기능
- 지역명 입력을 통한 실시간 검색
- Enter 키 이벤트 처리
- 검색 결과 동적 렌더링
- "검색 결과가 없습니다" 예외 처리

### 📍 지도 연동
- Kakao Map API 로드뷰 기능
- 지도 마커를 통한 주변 관광지 표시
- 관광지 위치 시각화 및 경로 안내

### 📝 게시판
- 공지사항 및 자유게시판 CRUD
- 게시글 전체 조회 및 상세 조회
- 조회수 자동 카운팅
- 작성자 정보 및 작성일 표시

### 🔐 회원 시스템
- Spring Security 기반 인증/인가
- 로그인 시에만 게시판 접근 가능
- 역할 기반 권한 관리 (일반 사용자/관리자)

### 🏛️ 공공 데이터 연동
- Tour API를 통한 실시간 관광지 정보
- 관광지 상세 정보 제공
- 지속적인 데이터 업데이트

---

## 🛠 기술 스택

### Backend
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-000000?style=for-the-badge&logo=mybatis&logoColor=white)

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

### Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![DBeaver](https://img.shields.io/badge/DBeaver-382923?style=for-the-badge&logo=dbeaver&logoColor=white)

### API & Tools
![Kakao Map](https://img.shields.io/badge/Kakao%20Map-FFCD00?style=for-the-badge&logo=kakao&logoColor=black)
![Tour API](https://img.shields.io/badge/Tour%20API-009688?style=for-the-badge)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white)

---

## 👨‍💻 담당 역할 (팀장 / Full-Stack)

### 💻 백엔드 (주요 조회 기능)

#### 권역별 조회 API
- URL에서 권역 이름 추출 및 처리
- MyBatis 기반 데이터 조회
- 페이징 처리 로직 구현
- 한글 인코딩 문제 해결

#### 검색 조회 API
- 검색어 기반 관광지 필터링
- GET 요청 처리 및 검증
- 검색 결과 JSON 응답

#### 게시판 조회 기능
- 게시판 전체 조회 API
- 게시글 상세 조회 API
- 조회수 카운팅 로직

### 🎨 프론트엔드

#### 메인화면 설계 및 구현
- 대한민국 지도 이미지 기반 권역 선택 UI
- 이미지 좌표 계산을 통한 클릭 영역 설정
- Header/Footer 레이아웃 설계
- 전체 페이지 일관된 CSS 디자인 시스템 구축

#### Ajax 기반 동적 페이지
- 권역 클릭 시 비동기 데이터 로딩
- 페이징 처리 UI 구현
- 관광지명 클릭 시 상세 페이지 이동
- 마우스 오버 인터랙션 효과

#### 검색 기능 UI/UX
- 검색창 Enter 이벤트 처리
- 검색 결과 동적 렌더링
- 예외 상황 사용자 안내 메시지

#### 게시판 UI
- 게시판 목록 테이블 레이아웃
- 게시글 상세 페이지 디자인
- 반응형 테이블 및 호버 효과

---

## 🚨 트러블 슈팅

### 1. Footer 하단 고정 문제

**🔴 문제점**
- Footer가 컨텐츠 양에 따라 중간에 떠 있는 현상
- 컨텐츠가 적을 때 하단에 빈 공간 발생

**🟢 해결방안**
```css
/* body 높이 최소 100vh 설정 */
body {
    min-height: 100vh;
    display: flex;
    flex-direction: column;
}

/* main이 남은 공간을 모두 차지하도록 설정 */
main {
    flex-grow: 1;
}

/* footer를 항상 하단에 위치 */
footer {
    margin-top: auto;
}
```

**✅ 결과**
- 컨텐츠 양과 관계없이 Footer가 항상 하단에 고정
- Flexbox를 활용한 깔끔한 레이아웃 구조

**💡 배운 점**
CSS 레이아웃은 body, main, footer 각각의 역할을 고려한 구조적 설계가 필요하며, flex와 min-height 속성을 적절히 조합해야 원하는 레이아웃이 완성됨을 깨달았습니다.

---

### 2. 지도 이미지 좌표 클릭 영역 설정

**🔴 문제점**
- 지도 이미지에서 권역별 클릭 영역을 정확히 설정하기 어려움
- 픽셀 단위로 좌표를 직접 계산해야 함
- 사용자가 클릭한 위치가 의도한 권역과 다르게 인식됨

**🟢 해결방안**
1. **그림판을 활용한 좌표 계산**
   - 그림판에서 클릭 영역을 사각형으로 표시
   - 각 사각형의 좌상단/우하단 좌표 계산
   
2. **HTML Image Map 구현**
```html
<img src="korea-map.jpg" usemap="#korea-regions">
<map name="korea-regions">
    <area shape="rect" coords="50,50,200,150" href="/district/sudogun" alt="수도권">
    <area shape="rect" coords="210,50,360,150" href="/district/gangwon" alt="강원권">
    <area shape="rect" coords="50,160,200,310" href="/district/chungcheong" alt="충청권">
    <area shape="rect" coords="210,160,360,310" href="/district/gyeongsang" alt="경상권">
</map>
```

**✅ 결과**
- 정확한 권역별 클릭 영역 설정 완료
- 사용자 경험 대폭 향상
- 의도하지 않은 영역 클릭 방지

**💡 배운 점**
좌표 클릭 이벤트는 한 픽셀만 어긋나도 전혀 다른 결과가 나오기 때문에, 개발 후 실제 사용 화면에서의 검증 과정이 매우 중요하다는 것을 경험했습니다. 또한 단순한 그림판 같은 도구도 개발에 유용하게 활용될 수 있음을 배웠습니다.

---

### 3. 한글 인코딩 문제

**🔴 문제점**
- 권역별 페이지 조회 시 한글 파라미터가 깨짐
- MyBatis에서 district 값 비교 실패
- URL에서 한글이 영어로 변환되어 조회 불가
- 예: `sudogun` ← 원래는 "수도권"이어야 함

**🔍 원인 분석**
- 클라이언트에서 서버로 한글 전송 시 인코딩 처리 미흡
- MyBatis XML에서 UTF-8 처리 누락
- Controller에서 URL 디코딩 누락

**🟢 해결방안**

1. **MyBatis XML 수정**
```xml
<!-- district-mapper.xml -->
<select id="selectByDistrict" parameterType="string" resultType="District">
    SELECT * FROM districts
    WHERE district = CONVERT(#{district} USING utf8mb4)
</select>
```

2. **Controller에서 URL 디코딩 추가**
```java
// DistrictController.java
@GetMapping("/{district}")
public String getDistrict(@PathVariable String district, Model model) {
    // 한글 URL 깨짐 방지
    district = URLDecoder.decode(district, StandardCharsets.UTF_8);
    
    List<TouristSpot> spots = districtService.getByDistrict(district);
    model.addAttribute("spots", spots);
    
    return "district/list";
}
```

3. **application.properties 설정 확인**
```properties
# UTF-8 인코딩 명시
spring.http.encoding.charset=UTF-8
spring.http.encoding.enabled=true
spring.http.encoding.force=true
```

**✅ 결과**
- 한글 권역명으로 정상 조회 가능
- URL 파라미터 안정성 확보
- 모든 한글 데이터 처리 정상화

**💡 배운 점**
인코딩 문제는 시스템 전반의 설계 단계에서부터 고려해야 할 핵심 요소이며, 특히 국내 서비스에서는 한글 처리 안정성을 초기 설계 단계에서 UTF-8로 통일해야 유지보수성이 높아진다는 것을 배웠습니다. 또한 `URLDecoder.decode()` 같은 작은 코드 한 줄이 사용자 접근성에 큰 영향을 줄 수 있어, 사소한 부분도 초기부터 꼼꼼히 검증하는 습관이 필요함을 깨달았습니다.

---

## 📊 프로젝트 성과

### 개발 성과
- ✅ **1주 만에 핵심 기능 구현 완료** (빠른 프로토타입 개발)
- ✅ 공공 데이터 API 활용 경험
- ✅ 실시간 지도 연동 및 로드뷰 기능 구현
- ✅ Spring Security를 통한 권한 기반 접근 제어
- ✅ Full-Stack 개발로 일관된 서비스 구현

### 기술적 성장
- 💻 **MyBatis 기반 데이터 조회 최적화 경험**
- 🔤 **한글 인코딩 이슈 해결 및 국내 서비스 안정성 확보**
- 🗺️ 외부 API (Tour API, Kakao Map) 연동 경험
- 🔄 Ajax를 활용한 비동기 통신 및 동적 렌더링
- 🎨 프론트엔드 UI/UX 설계 및 구현 능력

### 협업 및 리더십
- 👥 **6명 팀 프로젝트 팀장 역할 수행**
- 📝 Git/GitHub를 통한 협업 경험
- 🤝 프론트/백엔드 간 원활한 소통
- ⏰ 1주라는 짧은 기간 내 효율적인 역할 분담 및 일정 관리

### 학습 포인트
- **백엔드와 프론트엔드의 유기적 연결**이 서비스 전체 품질에 직결됨을 체감
- 데이터 처리 안정성이 사용자 경험에 미치는 영향 경험
- 프론트와 백엔드를 균형 있게 고려한 데이터 흐름 설계의 중요성
- **실제 사용 환경에서의 테스트**가 서비스 완성도를 높이는 핵심
- 협업 과정에서 코드 리뷰와 테스트를 철저히 수행하는 것의 중요성

---

## 📝 데이터베이스 구조

### 주요 테이블

**member 테이블**
```sql
CREATE TABLE member (
    no INT PRIMARY KEY AUTO_INCREMENT,
    id VARCHAR(50) UNIQUE NOT NULL,
    pw VARCHAR(255) NOT NULL,
    name VARCHAR(50) NOT NULL,
    member_role VARCHAR(20) DEFAULT 'USER',
    member_email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

**board 테이블**
```sql
CREATE TABLE board (
    board_no INT PRIMARY KEY AUTO_INCREMENT,
    board_type VARCHAR(20) NOT NULL,
    title VARCHAR(200) NOT NULL,
    content TEXT NOT NULL,
    view_count INT DEFAULT 0,
    member_id VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES member(id)
);
```

---

## 📚 주요 API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | 메인 페이지 (지도) |
| GET | `/district/{region}` | 권역별 관광지 목록 |
| GET | `/search` | 검색 결과 페이지 |
| GET | `/detail/{id}` | 관광지 상세 정보 |
| GET | `/board` | 게시판 목록 |
| GET | `/board/{id}` | 게시글 상세 조회 |
| POST | `/board/write` | 게시글 작성 (로그인 필요) |

---


## 👨‍💻 Contact

**홍주희** - 팀장 / Software Developer

- 📧 Email: kittyjh1019@naver.com
- 🐱 GitHub: [@kittyjh19](https://github.com/kittyjh19)

---

<div align="center">

**🗺️ TripSpot** - 국내 여행 정보 검색 플랫폼

Made with ❤️ by TEAM 200 OK

</div>
