import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
// 전형적인 DP 방식
// N번째 집 기준으로 N-1까지 DP배열에 저장해놓은 경우의 3개(R,G,B 선택) 중에서 현재 탐색하는 색과 다른색 중 더 작은 값을 저장
public class Main {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int[][] dp = new int[3][1001];
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		dp[0][1] = r;
		dp[1][1] = g;
		dp[2][1] = b;
		
		// logic
		for (int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
      // 점화식
			dp[0][i] = Math.min(dp[1][i-1], dp[2][i-1]) + r;
			dp[1][i] = Math.min(dp[0][i-1], dp[2][i-1]) + g;
			dp[2][i] = Math.min(dp[0][i-1], dp[1][i-1]) + b;
		}
 
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			if(min > dp[i][N]) {
				min = dp[i][N];
			}
		}
    
		System.out.println(min);

	}
}
