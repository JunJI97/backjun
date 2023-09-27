import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue; 
import java.util.StringTokenizer; 
// 쉬워 보이는데 시간초과 때문에 고민해야하는 문제
// 첫 번째 기준으로 정렬하고 시작하는 것은 쉽게 보였지만,
// 두 번째 기준으로 비교하는 과정에서 완전 탐색을 하면 최악의 경우의 복잡도가 N^2로 올라가서 시간초과가 일어난다.
// 두 번째 기준을 최대한 nlogn으로 작성하는 것이 핵심인 문제.

// 반복문 한번이면 정답 도출이 가능하다..!
public class Main { 
	static int T, N, answer, max;
	static PriorityQueue<Node> points;
	static Queue<Integer> queue;
	
	static class Node implements Comparable<Node>{
		int a;
		int b; 
		
		public Node(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.a - o.a;
		}   
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());  
		
		for (int t = 0; t < T; t++) {
			answer = 0;
			points = new PriorityQueue<Node>();
			queue = new LinkedList<Integer>();
			N = Integer.parseInt(br.readLine()); 
			max = N+1;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()); 
				points.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); 
			}
			
			for (int i = 0; i < N; i++) { 
				queue.add(points.poll().b);
			}
			
			// logic 
			while(queue.size() != 0) {  
				int std = queue.poll();  
				if(max > std) {
					max = std;
					answer++;
				}   
			}   
			System.out.println(answer);
		}
	}
}

