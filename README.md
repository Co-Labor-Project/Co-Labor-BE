<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/Co-Labor.png" width="100%" height="100%" />

# 👷 Co-Labor: 외국인 근로자 고용 서포트 플랫폼 서비스

- 외국인 근로자들이 한국에서 안정적으로 정착하고 적응할 수 있도록 돕는 웹사이트
- 외국인 근로자들이 겪는 일자리, 정보 부족, 법률 문제 등을 해결하고, 다양한 지원 서비스를 제공

## ✨ 주요 기능

### 1️⃣ 채용 공고 및 기업 리뷰

&nbsp;다양한 직무, 경력, 고용 형태, 근무 지역 등을 포함한 상세한 채용 공고를 제공합니다. 사용자는 기업의 상세 페이지에서 기업 정보와 함께 다른 근로자들의 리뷰를 통해 기업의 신뢰도를 평가할 수 있습니다. 이를 통해 본인에게 적합한 직무와 신뢰할 수 있는 기업을 효율적으로 찾아 지원할 수 있습니다.

### 2️⃣ AI 검색 기능

&nbsp;DB에서 추출한 데이터를 기반으로 Word2Vec 모델을 학습시켜 유사 키워드를 생성합니다. 사용자가 입력한 키워드와 유사한 키워드를 통해 관련된 데이터를 보다 빠르고 정확하게 검색할 수 있습니다. 이를 통해 사용자는 필요한 정보를 신속하고 효율적으로 찾을 수 있습니다.

### 3️⃣ 법률 챗봇

&nbsp;OpenAI API 모델을 활용하여 외국인 근로자들이 직장에서 겪을 수 있는 법률적인 문제에 대해 도움을 받을 수 있는 법률 상담 챗봇을 제공합니다. 챗봇은 한국의 노동법과 관련된 다양한 질문에 신속하고 정확하게 답변하여 법적 문제를 해결하는 데 도움을 줍니다.

## 🔗 개발 진행 상황

- **Front-end** &nbsp;&nbsp;&nbsp;[FE Repository](https://github.com/Co-Labor-Project/Co-Labor-FE)
- **Back-end** &nbsp;&nbsp;&nbsp;&nbsp;[BE Repository](https://github.com/Co-Labor-Project/Co-Labor-BE)

## 🗂️ 시스템 아키텍처

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/System_Architecture.png" width="80%" height="80%" />

## 🗃️ ERD

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/ERD.png" width="100%" height="100%" />

### `labor_user` - 일반 회원

- 외국인 근로자(사용자) 정보

### `enterprise_user` - 기업 회원

- 채용 공고를 게시할 수 있는 기업 사용자 정보

### `enterprise` - 기업

- 기업 상세 정보

### `enterprise_queue` - 기업 대기열

- 등록 승인을 기다리는 기업 정보

### `job` - 채용 공고

- 기업 회원이 게시하는 채용 공고

### `review` - 리뷰

- 사용자가 기업에 대해 남긴 리뷰

### `hospital` - 병원

- 병원 상세 정보

### `support_center` - 외국인 근로자 지원 센터

- 외국인 근로자 지원 센터 상세 정보

### `chatting` - 채팅

- 사용자와 법률 챗봇간의 채팅 메시지

### Foreign Key(외래 키) 제약 조건

- `job` : `enterprise_user_id` 및 `enterprise_id`는 `enterprise_user` 테이블 참조
- `review` : `labor_user_id`는 `labor_user` 테이블 참조, `enterprise_id`는 `enterprise` 테이블 참조
- `enterprise_user` : `enterprise_id`는 `enterprise` 테이블 참조
- `chatting` : `labor_user_id`는 `labor_user` 테이블 참조

## 📚 기술 스택

|                            |                                                                                                                                                                                                                      Skills                                                                                                                                                                                                                       |
| :------------------------- | :-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| **Front-end**              | ![Static Badge](https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white) ![Static Badge](https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white) ![Static Badge](https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black) ![Static Badge](https://img.shields.io/badge/react.js-61DAFB?style=for-the-badge&logo=react&logoColor=black) |
| **Back-end**               |                                                         ![Static Badge](https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![Static Badge](https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white) ![Static Badge](https://img.shields.io/badge/openai-412991?style=for-the-badge&logo=openai&logoColor=white) ![Static Badge](https://img.shields.io/badge/flask-333333?style=for-the-badge&logo=flask&logoColor=white)                                                        |
| **Version Control System** |                                                                                     [![Static Badge](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/Co-Labor-Project/demo-repository/)![Static Badge](https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white)                                                                                      |
| **Collaboration**          |                                                                      ![Static Badge](https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white) [![Static Badge](https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white)](https://mixolydian-idea-627.notion.site/7d14829c5a3e4b84930b2c5a8b0c9838?pvs=4)                                                                      |
| **Design**                 |                                                                                                         [![Static Badge](https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/design/YddHONkDl0nqcbQVkUEVTa/%EA%B3%B5%EB%AA%A8%EC%A0%84?node-id=33-251&t=ocRWZPY412U61gb0-0)                                                                                                          |
| **Deployment**                 |                                                                                                         ![Static Badge](https://img.shields.io/badge/aws_s3-569A31?style=for-the-badge&logo=amazonaws&logoColor=white) ![Static Badge](https://img.shields.io/badge/route_53-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white) ![Static Badge](https://img.shields.io/badge/aws_ec2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white) ![Static Badge](https://img.shields.io/badge/gabia-4285F4?style=for-the-badge&logo=google&logoColor=white) |                                                                                                          |
| **AI**                     | ![Static Badge](https://img.shields.io/badge/kobert-4285F4?style=for-the-badge&logoColor=white)

## 💻 상세 페이지

### 📍 메인 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Main%20Page.gif" width="100%" height="100%" />

- Co-Labor 메인 페이지

### 📍 로그인 / 회원가입 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Login.gif" width="100%" height="100%" />

- Co-Labor 로그인 및 일반 회원, 기업 회원 가입 페이지

### 📍 기업 등록 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Company%20Registration.gif" width="100%" height="100%" />

- 채용 공고를 게시하고자 하는 기업 회원이 본인의 소속 기업을 등록하는 페이지
- 등록한 기업은 추후 관리자에 의해 승인

### 📍 기업 상세 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Company%20Details.gif" width="100%" height="100%" />

- Co-Labor에 등록된 기업들의 상세 정보, 채용 공고, 기업 리뷰 등을 확인 할 수 있는 페이지
- 해당 기업의 전체 리뷰 통계와 각 리뷰별 세부 내용 확인 가능

### 📍 채용 공고 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Recruitment%20Notice.gif" width="100%" height="100%" />

- 기업 회원들이 게시한 기업 채용 공고를 확인 할 수 있는 페이지
- 직군, 경력, 근무형태, 우대사항 등 세부 내용과 연관된 채용 공고 확인 가능

### 📍 법률 챗봇 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Legal%20Chatbot.gif" width="100%" height="100%" />

- 근로계약, 비자 규정, 법적 권리와 의무 등에 관한 정보를 제공하며, 법적 문제 해결을 도움
- 과거 대화 내용 확인 가능

### 📍 외국인 근로자 지원 센터 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Foreign%20Worker%20Support%20Center.gif" width="100%" height="100%" />

- 사용자의 현재 위치를 기반으로 인근 외국인 근로자 지원 센터 및 병원 위치 및 정보 확인 가능
- 각 센터 클릭시, 지도에서 해당 위치로 이동

### 📍 일반 검색 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/Search.gif" width="100%" height="100%" />

- 기업 정보, 채용 공고, 기업 리뷰 등을 키워드로 검색

### 📍 AI 검색 페이지

<img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/gif/AI%20Search.gif" width="100%" height="100%" />

- AI 모델을 활용하여 사용자 입력에 따라 관련성 높은 검색 결과를 빠르고 정확하게 제공

## 🧑‍💻 팀원 구성

<div align="center">

|                                                                                 **김도현**                                                                                 |                                                                            **김문기**                                                                             |                                                                            **정한울**                                                                             |                                                                            **조준화**                                                                             |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/kdhqwe1030_img.jpg" height=240 width=180> <br/> _[@kdhqwe1030](https://github.com/kdhqwe1030)_ | <img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/mk-isos_img.jpg" height=240 width=180> <br/> _[@mk-isos](https://github.com/mk-isos)_ | <img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/jho7535_img.jpg" height=240 width=180> <br/> _[@jho7535](https://github.com/jho7535)_ | <img src="https://github.com/Co-Labor-Project/demo-repository/blob/main/img/jjj5306_img.jpg" height=240 width=180> <br/> _[@jjj5306](https://github.com/jjj5306)_ |

</div>


## 📋 제안서
[Co-Labor_Proposal.pdf](https://github.com/user-attachments/files/16548366/Co-Labor_Proposal.pdf)


