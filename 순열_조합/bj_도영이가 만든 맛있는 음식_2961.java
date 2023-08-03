import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	public static int[][] p;
	public static int N;
	public static int min=10000000;
	public static boolean[] isSelected;
	public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		isSelected = new boolean[N];
		p = new int[2][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			p[0][i] = Integer.parseInt(st.nextToken());
			p[1][i] = Integer.parseInt(st.nextToken());
		}
		
		powerset(0);
		System.out.println(min);
	}

	private static void powerset(int cnt) {
		if(cnt == N) {
			int sum1=1;
			int sum2=0;
			int count=0;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) {
					sum1 = sum1 * p[0][i];
					sum2 += p[1][i];
					count++;
				}
			}
			int diff = Math.abs(sum1 - sum2);
			if ((count >0)&&(min > diff)) {
				min = diff;
			}
			return;
		}
    // 공식
		isSelected[cnt] = true;
		powerset(cnt+1);
		isSelected[cnt] = false;
		powerset(cnt+1);
	}
}
