# 2023-01-OSSP2-8282-8

### PDFCampus: 전공 교재 PDF 제공 서비스

## 0. 팀원 구성
|팀원|학과|역할|학번|
|------|---|---|---|
|고나연|컴퓨터공학과|프론트엔드|2021111896|
|육지원|컴퓨터공학과|프론트엔드|2019112049|
|이지연|컴퓨터공학과|백엔드|2020111801|
|이현정|컴퓨터공학과|프론트엔드|2021113139|
|정구연|컴퓨터공학과|백엔드|2019112072|

## 1. 요약
 많은 대학생들이 교재 pdf를 불법으로 다운받아 보안상의 위험에 빠지고, 저작권은 위배 되고 있다. 이에 본 프로젝트는 안정성과 저작권을 지킬 수 있고, 비용 부담이 덜한 전공서적 pdf와 필기기능, 그리고 오픈 소스를 활용하여 추출한 필기에 대한 메타데이터를 포함하는 필기 상품화 기능을 제공한다.
## 2. 기술 스택
### 프론트엔드
* VSCode
* <code>react-native 0.69.9</code> <code>expo 46</code> <code>axios 1.4.0</code> <code>react-navigation/native 6.1.6</code> <code>react-navigation/native-stack 6.9.12</code> <code>react-native-canvas 0.1.39</code> <code>react-native-canvas 0.1.39</code> <code>recoil 0.7.7</code> <code>styled-components 5.3.10</code>
* javascript
### 백엔드
* IntelliJ IDEA 2023.1
* <code>Spring Boot v2.7.11</code> <code>MySQL workbench 8.0 CE</code> <code>Apache PDFBox 2.0.24</code> <code>POSTMAN</code>
* Java 11
## 3. 결과  
1.<code>react-native-pdf</code>를 활용한 PDF뷰어 기능 개발

![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/8fa69258-89ef-4e24-8505-b78f7c4d86a2) ![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/c24de104-0124-46c2-984f-a3d44f01b472)

필기 전 콘텐츠



2.<code>react-native-canvas</code>를 활용한 필기 기능 개발

![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/51091a5d-7bf6-41eb-869d-2ff7789c3eb9) ![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/f53671a7-635e-462c-95b0-c9cc75d3f79d)

필기 후 콘텐츠

3.메타데이터만 남겨 필기자료를 상품화  

![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/945f29aa-c2cd-4be3-b7a8-df155751c607) ![image](https://github.com/gouyeonch/PDFCampus/assets/73930285/c316b44e-9b52-4ab1-b72f-c65d16f9b874)

필기 상품
 
