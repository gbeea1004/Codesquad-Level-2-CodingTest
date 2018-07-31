# codeTest
테스트용

작업 기간 :  1 주일

<h3>핵심 로직</h3><hr>

- 컴퓨터 생성 : makeCom() , 사용자 생성 : makeUser()

- 에러 처리 : errCheck() 에서 한꺼번에 처리 - [ posCheck() : 자리수 체크, userOverlap() : 사용자 숫자 중복 체크 ]
         comOverlap() : 컴퓨터 숫자 중복 체크

- 비교 연산 : compare() 를 통해 컴퓨터와 사용자 숫자 비교

- 루프는 무한 반복 "3 STRICK" 가 나올 때 빠져 나옴

<h3>수정 과정 및 어려움</h3><hr>

- depth < 3 을 맞추기 위해 이중 For문을 단일 For문으로 수정

- 중복 체크 과정에서 숫자 3개를 사용할 때는 이상 없이 돌아 가지만 4개 이상(maxNum > = 4)일 때 문제 발생