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
				System.out.println("������ ���۵Ǿ����ϴ�.");
				computerArr = makeCom(computerArr); // ��ǻ�� ���� ���� ����
				gameStart = false;
			}
			isLengthErr = makeUser(userArr); // user ���� ����
			
			if(isLengthErr == true) // �ڸ��� ������ ������ �ٽ� ����
				continue;
			strike = compare(computerArr, userArr);
			if(strike == 3) {
				System.out.println("�����մϴ�! 3���� ���ڸ� ��� ���߼̽��ϴ�. ��������.");
				gameStart = true;
			}
		}
		
	}
	
	// ��ǻ�� ���� �ߺ� üũ
	public static int[] comOverlap() {
		Random r = new Random(); // ��ǻ�� ���� ���� ����
		int[] comArr = new int[MAXNUM];
		for(int i = 0; i < MAXNUM; i++) {
			comArr[i] = r.nextInt(10); // 0 ~ 9
			if(i > 0 && (comArr[i] == comArr[i-1])) { // maxNum�� 3 �̻����� �ϸ� ������ �ֱ�������..
				i--;
				continue;
			}
			if(i == (MAXNUM - 1) && comArr[i] == comArr[0])
				i--;
		}
		return comArr;
	}
	
	//user ���� �ߺ� üũ
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
		// ���
		System.out.print("��ǻ�� : ");
		for(int i=0; i< computerArr.length; i++) {
			System.out.print(computerArr[i]);
		}
		System.out.println();
		return computerArr;
	}
	
	public static boolean makeUser(int[] userArr) {
		Scanner s = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ���. ex)123");
		int userBall = s.nextInt();
		if(posCheck(userBall) == true) { // �ڸ� �� ���� üũ
			return true;
		}
		userArr[0] = userBall / 100;
		userArr[1] = userBall % 100 / 10;
		userArr[2] = userBall % 10;
		if(userOverlap(userArr) == true) { // �ߺ� üũ
			System.out.println("���ڸ� �ߺ��ؼ� ����ϸ� �ȵ˴ϴ�. �ٽ� �Է��ϼ���.");
			return true;
		}
		return false;  // ���� ���� ��
	}
	
	// ���� ��
	public static int compare(int[] computerArr, int[] userArr) {
		int strike = 0, ball = 0;
		String com= "";
		for(int i=0; i < MAXNUM; i++)
			com += computerArr[i];
		for(int i=0; i < MAXNUM; i++) {
			if(computerArr[i] == userArr[i]) strike++;
			else if(com.contains(userArr[i] + "")) ball++; // contains : ���ڿ��� ����ִ��� Ȯ��, userArr�� int ���̹Ƿ� ���ڿ��� �ٲٱ� ���� ""
		}
		System.out.println(strike + " ��Ʈ����ũ, " + ball + "��");
		return strike;
	}
		
	public static boolean posCheck(int userBall) {
		if(String.valueOf(userBall).length() != MAXNUM) { //user�� �Է��� ���ڰ� maxNum�� �ڸ��� �ƴϸ�
			System.out.println("�����Դϴ�. ���ڸ�" + MAXNUM + "�� �Է����ּ���!!");
			return true;
		}
		else
			return false;
	}
}
