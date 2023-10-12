import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer; 

// 일반적인 다익스트라 문제에서 BFS 방식으로 탐색하는 문제
// 선언한 Node 클래스의 멤버 변수를 노드의 인덱스가 아니라 h,w 좌표로 받는다.
// 다익스트라 로직에서 인접노드를 탐색하는 부분은 BFS처럼 4방향으로 구현한다.

public class Main {  
	public static int N;
	public static int[][] map,dist;
	public static final int INF = Integer.MAX_VALUE;
	public static int[][] dr = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
	
	static class Node implements Comparable<Node> {
		int h;
		int w;
		int weight;
		
		public Node(int h, int w, int weight) {
			this.h = h;
			this.w = w;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st; 
		int round = 1;
		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if(N==0) {
				break;
			}
			map = new int[N][N];
			dist = new int[N][N];    
			for (int h = 0; h < N; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < N; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
					dist[h][w] = INF;
				}
			} 
			dist[0][0] = 0;
			
			// logic
			Dijkstra();
			System.out.println("Problem " + round + ": " + (dist[N-1][N-1]+map[0][0]));
			round++; 
		} 
	}

	private static void Dijkstra() { 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		
		pq.add(new Node(0,0,map[0][0]));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int ph = node.h;
			int pw = node.w;
			
			if(visited[ph][pw]) continue;
			visited[ph][pw] = true;
		
			for(int d=0; d<4; d++) {
				int nexth = ph + dr[d][0];
				int nextw = pw + dr[d][1]; 
				if(check(nexth, nextw)) {
					if(!visited[nexth][nextw] && dist[nexth][nextw] > dist[ph][pw] + map[nexth][nextw]) {
						dist[nexth][nextw] = dist[ph][pw] + map[nexth][nextw]; 
						pq.add(new Node(nexth,nextw,dist[nexth][nextw]));
					}
				}
			} 
		} 
	}

	private static boolean check(int h, int w) { 
		if(h>=0 && h<N && w >=0 && w<N) return true;
		return false;
	} 
}

