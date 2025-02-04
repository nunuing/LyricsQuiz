# 가사 낭독 퀴즈
- 스마트 인재 개발원에서 진행한 미니 프로젝트!
# 설계
## 기획 계기
  ![KakaoTalk_20240725_104129105](https://github.com/user-attachments/assets/fc5e8b21-7ef4-4bb0-b09d-b4e07d0aad03)
평소에 자주보던 놀라운 토요일!
거기서 간식게임으로 나왔던 가사 낭독 퀴즈(가낭퀴)를 집에서 플레이 해보고 싶었습니다!

## 게임 룰
ai 목소리로 가사를 읽어준다.
한곡당 기회는 3번 한번 틀릴때마다 획득할 수 있는 점수가 3점씩 줄어들게 된다(첫번째에 맞추면 10점 획득)
한 게임당 총 10곡이 문제로 나가고 한 게임당 힌트 3개를 사용할 수 있다.

## 데이터 베이스 설계
### 초기 ERD 
![ERD](https://github.com/user-attachments/assets/cde0996f-d67c-48f5-951e-b0c294533b57)
회원 데이터를 담는 Member 테이블, 음악을 담는 Music 테이블

회원 테이블의 경우 id, pw, 점수를 저장할 수 있도록 만들었다.
음악 정보를 담아놓는 music 테이블은 제목, 가수, 힌트1,2,3(한 곡 당 최대 3개의 힌트 사용가능) 그리고 파일 경로를 담아두는 mfile column을 만들었다.

### 최종 ERD
![image](https://github.com/user-attachments/assets/0f45767f-3b2a-44a3-89e8-a5b4d14f8bda)


처음부터 구현하고자 했던 기능이 랭킹확인 기능이었다.
그러다가 문제가 10문제 밖에 없고 감점도 별로 없으니까 동점을 기록하는 경우가 많겠다. 그럴땐 어떻게 처리하지 하는 고민을 했다.
그래서 가장 최근에 기록된 점수에 우선 순위를 부여하기로 결정했고 이를 해결하기 위해 DATE 타입 column SCORE_UPDATED를 만들었다.

그리고 구현하고자 하는 프로젝트가 많이 어려운 기능을 가지고 있는게 아니여서 아쉬워하던 선생님께서 영어, 한글 두가지 다 정답을 판별할 수 있는 기능을 추가하라고 조언해주셨고
이를 위해 title_eng, singer_eng를 만들었다.(타입은 동일하게 VARCHAR)

그리고 또 하나 변경사항이있었는데 사실 이게 정말 골칫거리었다.
mp3파일을 데이터 베이스에 넣고 싶었다.
그래서 여러 방법을 찾아보다가 BLOB라는 자료형을 이용하면 파일을 DB에 넣을 수 있다는 말을 듣고 시도해봤다.
하지만.. 만만치 않았다...ㅎ 프로시져에... 받을 때 뭘 더 해야되고...
3일이라는 시간 안에 못할것 같아서 바로 우회를 해서 경로를 저장하는걸로 했다. (사실 근데 같은 폴더에 제목과 같은 이름으로 저장해놔서 이 column도 딱히 필요가 없었다는 생각도 든다.)

## 유스케이스

![유스케이스_가낭퀴](https://github.com/user-attachments/assets/20771ed3-d339-4075-8438-303535dce988)

비회원의 경우 랭킹 조회, 회원가입, 게임 종료의 기능만 사용할 수 있고
회원의 경우 랭킹조회, 게임종료, 로그인 의 기능을 사용할 수 있다.
(사실 유스케이스는 이제껏 프로젝트를 진행하면서 한 번도 해보지 않았던 과정이라 다른 팀원분이 진행하셨다.)

## 플로우 차트



# 구현

