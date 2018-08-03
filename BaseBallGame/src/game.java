import java.util.Random;
import java.util.Scanner;

public class game {
	static final int MAXNUM = 3;
	
	public static void main(String[] args) {
		int strike = 0;
		boolean isLengthErr = false;
		int[] computerArr = new int[MAXNUM]; 
		int[] userArr = new int[MAXNUM];
		boolean gameStart = true;
		
		// Loop
		while(true) {
			if(gameStart == true) {
				System.out.println("게임이 시작되었습니다.");
				computerArr = makeCom(computerArr); // 컴퓨터 숫자 랜덤 생성
				gameStart = false;
			}
			isLengthErr = makeUser(userArr); // user 숫자 생성
			
			if(isLengthErr == true) // 자리수 에러가 있으면 다시 시작
				continue;
			strike = compare(computerArr, userArr);
			if(strike == 3) {
				System.out.println("축하합니다! 3개의 숫자를 모두 맞추셨습니다. 게임종료.");
				gameStart = true;
			}
		}
		
	}
	
	// 컴퓨터 숫자 중복 체크
	public static int[] comOverlap() {
		Random r = new Random(); // 컴퓨터 숫자 랜덤 생성
		int[] comArr = new int[MAXNUM];
		for(int i = 0; i < MAXNUM; i++) {
			comArr[i] = r.nextInt(10); // 0 ~ 9
			if(i > 0 && (comArr[i] == comArr[i-1])) { // maxNum을 3 이상으로 하면 오류가 있긴하지만..
				i--;
				continue;
			}
			if(i == (MAXNUM - 1) && comArr[i] == comArr[0])
				i--;
		}
		return comArr;
	}
	
	//user 숫자 중복 체크
	public static boolean userOverlap(int[] userArr) {
		for(int i = 1; i < MAXNUM; i++) {
			if(userArr[i] == userArr[i-1]) return true;
		}
		if(userArr[0] == userArr[MAXNUM-1]) {
			return true;
		}
		return false;
	}
	
	public static int[] makeCom(int[] computerArr) {
		computerArr = comOverlap();
		// 출력
		System.out.print("컴퓨터 : ");
		for(int i=0; i< computerArr.length; i++) {
			System.out.print(computerArr[i]);
		}
		System.out.println();
		return computerArr;
	}
	
	public static boolean makeUser(int[] userArr) {
		Scanner s = new Scanner(System.in);
		System.out.println("숫자를 입력하세요. ex)123");
		int userBall = s.nextInt();
		if(posCheck(userBall) == true) { // 자리 수 에러 체크
			return true;
		}
		userArr[0] = userBall / 100;
		userArr[1] = userBall % 100 / 10;
		userArr[2] = userBall % 10;
		if(userOverlap(userArr) == true) { // 중복 체크
			System.out.println("숫자를 중복해서 사용하면 안됩니다. 다시 입력하세요.");
			return true;
		}
		return false;  // 오류 없을 때
	}
	
	// 숫자 비교
	public static int compare(int[] computerArr, int[] userArr) {
		int strike = 0, ball = 0;
		String com= "";
		for(int i=0; i < MAXNUM; i++)
			com += computerArr[i];
		for(int i=0; i < MAXNUM; i++) {
			if(computerArr[i] == userArr[i]) strike++;
			else if(com.contains(userArr[i] + "")) ball++; // contains : 문자열이 들어있는지 확인, userArr는 int 형이므로 문자열로 바꾸기 위해 ""
		}
		System.out.println(strike + " 스트라이크, " + ball + "볼");
		return strike;
	}
		
	public static boolean posCheck(int userBall) {
		if(String.valueOf(userBall).length() != MAXNUM) { //user가 입력한 숫자가 maxNum의 자리가 아니면
			System.out.println("오류입니다. 숫자를" + MAXNUM + "개 입력해주세요!!");
			return true;
		}
		else
			return false;
	}
}
