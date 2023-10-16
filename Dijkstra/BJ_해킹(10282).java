import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 1. 단방향 그래프
// 2. 가중치 대신 시간초 
// 두가지 조건을 고려해서 다익스트라 돌리기

public class Main { 
	static int T, V, E, start;
	static List<List<Node>> map;
	static final int INF = Integer.MAX_VALUE;
	
	static class Node implements Comparable<Node> {
		int index;
		int weight;
		
		public Node(int index, int weight) {
			this.index = index;
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
		T = Integer.parseInt(br.readLine());  
		
		for(int t=0; t<T; t++) {
			// Case
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken())-1;
			
			// 인접 노드 세팅
			map = new ArrayList<>();
			for(int i=0; i<V; i++) {
				map.add(new ArrayList<>());
			}
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int w = Integer.parseInt(st.nextToken());
				map.get(b).add(new Node(a,w));
			}
			
			Dijkstra(start);
		}
	}

	private static void Dijkstra(int index) { 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[V];
		boolean[] visited = new boolean[V];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.add(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.index;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Node c : map.get(now)) {
				if(!visited[c.index] && dist[c.index] > dist[now] + c.weight) {
					dist[c.index] = dist[now] + c.weight;
					pq.add(new Node(c.index,dist[c.index]));
				}
			}
			 
		}
		int cntZombie = 0;
		int tot = 0;
		for(int i=0; i<dist.length; i++) {
			if(dist[i] != INF) {
				cntZombie++;
			} 
			if(dist[i] > tot && dist[i] != INF) {
				tot = dist[i];
			}
		}
		System.out.print(cntZombie + " " + tot + "\n");
	}
}

