import java.util.Scanner;
// 해당 문제가 피보나치랑 같은 점화식을 가지는 이유

// N번째 타일은 =
// 전 타일의 경우에서 | 하나를 추가하는 경우
// +
// 전전 타일에서 = 를 추가하는 경우
public class Main {
	static int N;
	static int answer = 1;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[] dp = new int[1000+1];
		dp[1] = 1;
		dp[2] = 2;
		if(N < 3) {
			System.out.println(dp[N]);
		}
		else {
			for (int i =3; i <= N; i++) {
				dp[i] = (dp[i-1] + dp[i-2])%10007;
				if(i == N) break;
			}
			System.out.println(dp[N]);
		}
 
	}
}
