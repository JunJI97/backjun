import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 단순한 스택문제
// 하지만 살짝 꼬여있어서 순서를 잘 짜는 것이 중요하다!
// 풀이 방법 보다는 스택 하나만 사용해서 모두 처리하기 위해 코드적인 고민을 했다. 
public class Main { 
	public static int N;
	public static Stack<int[]> stk; 
	public static int[] answer;
	public static void main(String[] args) throws IOException {
		// setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		stk = new Stack<>();
		answer = new int[N+1]; 
		for(int i=1; i<=N; i++) {
			int item = Integer.parseInt(st.nextToken());
			
			if(stk.isEmpty()) { // 왼쪽에 탑이 없는 상태
				answer[i] = 0; 
				stk.add(new int[] {item,i});
			} else {
				boolean flag = false;
				while(!stk.isEmpty()) {
					int[] p = stk.pop(); 
					if(item < p[0]) { // 수신 가능 
						stk.add(p); // 큰거 먼저
						stk.add(new int[] {item,i});
						answer[i] = p[1];
						flag = true;
						break;
					}  
				} 
				if(!flag) { 
					stk.add(new int[] {item,i});
				}
			} 
		}

		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) {
			sb.append(answer[i] + " ");
		}
		System.out.println(sb);  
	}
  
}
