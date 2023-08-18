// 시도1 : 실패 ==> 백트래킹에 대한 프루닝 이해 부족
// 시도2 : 실패 ==> 탐색이 15번 돌아야 하는데 Depth가 끝까지 도달 못하도록 잘못 설계함
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] wins, draws, loses, t1,t2;
	public static int win,draw,lose;
	public static boolean isPoss;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 매칭 배열
		t1 = new int[15];
		t2 = new int[15];
		int cnt=0;
		for(int i=0; i<5; i++) {
			for(int j=i+1; j<6; j++) {
				t1[cnt]= i;
				t2[cnt]= j;
				cnt++;
			}
		}
		
		for (int r = 0; r < 4; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// round
			win=0;
			draw=0; 
			lose=0;
			wins = new int[6];
			draws = new int[6];
			loses = new int[6];
			isPoss = false;
			for (int t = 0; t < 6; t++) {
				for (int i = 0; i < 3; i++) {
					if(i==0) {
						wins[t] = Integer.parseInt(st.nextToken());
						win += wins[t];
					} else if(i==1) {
						draws[t] = Integer.parseInt(st.nextToken());
						draw += draws[t];
					} else if(i==2) {
						loses[t] = Integer.parseInt(st.nextToken());
						lose += loses[t];
					}
				}// end -setting
				
			}
			// logic
			if((win+lose+draw) != 30 && win != lose) {
				System.out.print("0 ");
			} else {
				dfs(0);
				if(isPoss) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			// end- logic
		}
	}
	
	private static void dfs(int depth) {
		if(depth == 15) {
			// 전체 0 아니면 리턴
			for (int w : wins) if (w!=0) return;
			for (int d : draws) if (d!=0) return;
			for (int l : loses) if (l!=0) return;
			isPoss = true;
			return;
		}
		int i=t1[depth];
		int j=t2[depth];
		if(wins[i] > 0 && loses[j] > 0) {
			wins[i]--;
			loses[j]--;
			dfs(depth+1);
			wins[i]++;
			loses[j]++;
		}
		if(draws[i] > 0 && draws[j] > 0) {
			draws[i]--;
			draws[j]--;
			dfs(depth+1);
			draws[i]++;
			draws[j]++;
		}
		if(loses[i] > 0 && wins[j] > 0) {
			loses[i]--;
			wins[j]--;
			dfs(depth+1);
			loses[i]++;
			wins[j]++;
		}
	} // end- dfs
}
