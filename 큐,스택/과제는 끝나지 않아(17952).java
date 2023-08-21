import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
// 가장 마지막에 들어온 업무가 최우선이므로 Stack을 바로 떠올렸다.
public class Main  {
	public static int T;
	public static int answer;
	public static Stack<int[]> stk = new Stack<>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int work = Integer.parseInt(st.nextToken());
			if (work == 0) { 
				if(!stk.isEmpty()) { 
					int[] curr = stk.pop(); 
					int remain_time = curr[0]; 
					int score = curr[1]; 
					remain_time--; 
					if(remain_time == 0) {
						answer += score;
					} else { 
						stk.add(new int[] {remain_time, score}); 
					}
				}
			} else { 
				int score = Integer.parseInt(st.nextToken()); 
				int remain_time = Integer.parseInt(st.nextToken()); 
				remain_time--; 
				if(remain_time == 0) { 
					answer += score; 
				} else { 
					stk.add(new int[] {remain_time, score}); 
				}
			}
		} // end -roof
		
		System.out.println(answer); 
	}
}
