# 내 IP 기반 위치 지도 데모 (Spring Boot + Leaflet)

## 📖 개요
사용자의 공용 IP로 대략적인 위치를 추정해 지도를 띄워주는 간단한 데모입니다.
ip-api.com의 무료 엔드포인트를 호출해 위경도/도시/국가를 얻고, Thymeleaf + Leaflet으로 브라우저에 표시합니다.

---

## 🛠 기술 스택
- Java 17
- Spring Boot 3.4
- Thymeleaf
- Leaflet (OpenStreetMap 타일)
- Gradle


## 🔍 주요 기능
- 공용 IP 기반 위치 조회(ip-api.com/json/)
- 조회 결과(위도/경도/도시/국가) 모델 바인딩 → Thymeleaf 템플릿 렌더링
- Leaflet로 지도/마커 표시 (도시명/국가명 팝업)
- API 실패/도시명 없음 시 안전한 Fallback (서울 좌표/“My Location”)
