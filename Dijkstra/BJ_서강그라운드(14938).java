import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer; 

// 아이템 개수라는 추가 정보 때문에 헷갈릴 수 있는 문제
// 그러나 라운드마다 구한 dist[]를 기반으로 최대 획득 가능 개수를 카운트하기 때문에
// 일반 다익스트라로 풀면됨

public class Main { 
	static int V, maxRange, nE;
	static int maxItemCnt = 0;
	static int[] dist, items;
	static List<List<Node>> map = new ArrayList<>();
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		maxRange = Integer.parseInt(st.nextToken());
		nE = Integer.parseInt(st.nextToken());
		 
		for(int i=0; i<V; i++) {
			map.add(new ArrayList<>());
		}
		
		// item setting
		items = new int[V];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<V; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}
		
		// 인접노드 setting
		for(int E=0; E<nE; E++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			map.get(a).add(new Node(b,d));
			map.get(b).add(new Node(a,d));
		}
		
		// logic
		for(int i=0; i<V; i++) { 
			Dijkstra(i);
		}  

		System.out.println(maxItemCnt);
 
	}

	private static void Dijkstra(int start) { 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		int[] dist = new int[V];
		Arrays.fill(dist,INF); 
		dist[start] =0; 
		pq.add(new Node(start,0));
		int temp = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now = node.index;
			
			if(visited[now]) {
				continue;
			}
			visited[now] = true; 
			
			for(Node c : map.get(now)) {
				if(!visited[c.index] && dist[c.index] > c.weight + dist[now]) {
					dist[c.index] = c.weight + dist[now];
					pq.add(new Node(c.index, dist[c.index]));
				}
			} 
		}
		
		// 파밍 가능 아이템 Count
		temp += items[start]; 
		for(int i=0; i<dist.length; i++) { 
			if(dist[i] <= maxRange && dist[i] != 0) {
				temp += items[i];  
			} 
		} 
		
		// 최대 개수 비교
		if(temp > maxItemCnt) {
			maxItemCnt = temp;
		} 
	}

}

