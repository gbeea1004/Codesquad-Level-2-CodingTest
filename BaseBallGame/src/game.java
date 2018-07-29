import java.util.Random;
import java.util.Scanner;

public class game {
	public static void main(String[] args) {
		int maxNum = 3;
		int strick = 0;
		boolean isLengthErr = false;
		int[] computerArr = new int[maxNum]; 
		int[] userArr = new int[maxNum];
		
		makeCom(computerArr, maxNum); // ��ǻ�� ���� ���� ����
		// Loop
		while(true) {
			isLengthErr = makeUser(userArr, maxNum); // user ���� ����
			
			if(isLengthErr == true) // �ڸ��� ������ ������ �ٽ� ����
				continue;
			strick = compare(computerArr, userArr, maxNum);
			if(strick == 3) {
				System.out.println("�����մϴ�! 3���� ���ڸ� ��� ���߼̽��ϴ�. ��������.");
				break;
			}
		}
		
	}
	
	// ��ǻ�� ���� �ߺ� üũ
	public static void comOverlap(int[] comArr, int maxNum) {
		Random r = new Random(); // ��ǻ�� ���� ���� ����
		for(int i=0; i<maxNum; i++) {
			comArr[i] = r.nextInt(10); // 0 ~ 9
			for(int j=0; j<i; j++) {
				if(comArr[i]==comArr[j]) i--; // ���� �ܰ�� ���ư� �ٽ� ���� �� ����
			}
		}
	}
	
	//user ���� �ߺ� üũ
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
		// ���
		System.out.print("��ǻ�� : ");
		for(int i=0; i< computerArr.length; i++) {
			System.out.print(computerArr[i]);
		}
		System.out.println();
	}
	
	public static boolean makeUser(int[] userArr, int maxNum) {
		Scanner s = new Scanner(System.in);
		boolean isErr = false; 
		System.out.println("���ڸ� �Է��ϼ���. ex)123");
		int userBall = s.nextInt();
		userArr[0] = userBall / 100;
		userArr[1] = userBall % 100 / 10;
		userArr[2] = userBall % 10;
		isErr = errCheck(userBall, maxNum, userArr); // ���� üũ
		return isErr;
	}
	
	public static boolean errCheck(int userBall, int maxNum, int [] userArr) {
		if(posCheck(userBall, maxNum)==true) { // �ڸ� �� ���� üũ
			return true;
		}
		else if(userOverlap(userArr, maxNum)==true) { // �ߺ� üũ
			System.out.println("���ڸ� �ߺ��ؼ� ����ϸ� �ȵ˴ϴ�. �ٽ� �Է��ϼ���.");
			return true;
		}
		return false;
	}
	
	// ���� ��
	public static int compare(int[] computerArr, int[] userArr, int maxNum) {
		int strick = 0;
		int ball = 0;
		for(int i=0; i<maxNum; i++) {
			for(int j=0; j<maxNum; j++) {
				if(i==j && (computerArr[i] == userArr[j])) strick++;
				else if(computerArr[i] == userArr[j]) ball++;
			}
		}
		System.out.println(strick + " ��Ʈ����ũ, " + ball + "��");
		return strick;
	}
		
	public static boolean posCheck(int userBall, int maxNum) {
		if(String.valueOf(userBall).length() != maxNum) { //user�� �Է��� ���ڰ� maxNum�� �ڸ��� �ƴϸ�
			System.out.println("�����Դϴ�. ���ڸ�" + maxNum + "�� �Է����ּ���!!");
			return true;
		}
		else
			return false;
	}
}
