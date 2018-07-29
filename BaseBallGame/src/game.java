import java.util.Random;
import java.util.Scanner;

public class game {
	public static void main(String[] args) {
		int maxNum = 3;
		int strick = 0;
		boolean isLengthErr = false;
		int[] computerArr = new int[maxNum]; 
		int[] userArr = new int[maxNum];
		
		makeCom(computerArr, maxNum); // 컴퓨터 숫자 랜덤 생성
		// Loop
		while(true) {
			isLengthErr = makeUser(userArr, maxNum); // user 숫자 생성
			
			if(isLengthErr == true) // 자리수 에러가 있으면 다시 시작
				continue;
			strick = compare(computerArr, userArr, maxNum);
			if(strick == 3) {
				System.out.println("축하합니다! 3개의 숫자를 모두 맞추셨습니다. 게임종료.");
				break;
			}
		}
		
	}
	
	// 컴퓨터 숫자 중복 체크
	public static void comOverlap(int[] comArr, int maxNum) {
		Random r = new Random(); // 컴퓨터 숫자 랜덤 생성
		for(int i=0; i<maxNum; i++) {
			comArr[i] = r.nextInt(10); // 0 ~ 9
			for(int j=0; j<i; j++) {
				if(comArr[i]==comArr[j]) i--; // 이전 단계로 돌아가 다시 랜덤 값 생성
			}
		}
	}
	
	//user 숫자 중복 체크
	public static boolean userOverlap(int[] userArr, int maxNum) {
		for(int i=0; i<maxNum; i++) {
			for(int j=0; j<i; j++) {
				if(userArr[i]==userArr[j]) return true;
			}
		}
		return false;
	}
	
	public static void makeCom(int[] computerArr,int maxNum) {
		comOverlap(computerArr, maxNum);
		// 출력
		System.out.print("컴퓨터 : ");
		for(int i=0; i< computerArr.length; i++) {
			System.out.print(computerArr[i]);
		}
		System.out.println();
	}
	
	public static boolean makeUser(int[] userArr, int maxNum) {
		Scanner s = new Scanner(System.in);
		boolean isErr = false; 
		System.out.println("숫자를 입력하세요. ex)123");
		int userBall = s.nextInt();
		userArr[0] = userBall / 100;
		userArr[1] = userBall % 100 / 10;
		userArr[2] = userBall % 10;
		isErr = errCheck(userBall, maxNum, userArr); // 에러 체크
		return isErr;
	}
	
	public static boolean errCheck(int userBall, int maxNum, int [] userArr) {
		if(posCheck(userBall, maxNum)==true) { // 자리 수 에러 체크
			return true;
		}
		else if(userOverlap(userArr, maxNum)==true) { // 중복 체크
			System.out.println("숫자를 중복해서 사용하면 안됩니다. 다시 입력하세요.");
			return true;
		}
		return false;
	}
	
	// 숫자 비교
	public static int compare(int[] computerArr, int[] userArr, int maxNum) {
		int strick = 0;
		int ball = 0;
		for(int i=0; i<maxNum; i++) {
			for(int j=0; j<maxNum; j++) {
				if(i==j && (computerArr[i] == userArr[j])) strick++;
				else if(computerArr[i] == userArr[j]) ball++;
			}
		}
		System.out.println(strick + " 스트라이크, " + ball + "볼");
		return strick;
	}
		
	public static boolean posCheck(int userBall, int maxNum) {
		if(String.valueOf(userBall).length() != maxNum) { //user가 입력한 숫자가 maxNum의 자리가 아니면
			System.out.println("오류입니다. 숫자를" + maxNum + "개 입력해주세요!!");
			return true;
		}
		else
			return false;
	}
}
