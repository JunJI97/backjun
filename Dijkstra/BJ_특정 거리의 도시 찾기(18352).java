import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer; 

// 다익스트라 기본 (우선순위 큐 사용)
// 1. Node 클래스 선언 (Comparable 인터페이스 구현)
// 2. List<List<Node>> 선언 , int[][] ==> 메모리 초과
// 3. 위 리스트에 list.get(start).add(new Node(end, weight));
// 4. 다익스트라 로직에서 우선순위 큐로 순회하며 갱신

public class Main { 
	static int V,E,target,startIndex; 
	static final int INF = Integer.MAX_VALUE;
	static int dist[];
	static List<List<Node>> list;
	
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
		E = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		startIndex = Integer.parseInt(st.nextToken())-1;
		
		dist = new int[V];
		Arrays.fill(dist,INF);
		list = new ArrayList<>();
		for(int i=0; i<=V; i++) {
			list.add(new ArrayList<>());
		}
		
		// 이부분을 2차원 배열로 선언하면, 불필요한 INF까지 모두 순회해서 메모리 초과 발생
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());    
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			list.get(start).add(new Node(end,1));
		}
		
		// logic
		dijkstra(startIndex);
		
		int answer = 0;
		for(int i=0; i<dist.length; i++) {
			if(dist[i] == target) {
				answer++;
				System.out.println(i+1);
			}
		}
		if(answer == 0) {
			System.out.println(-1);
		}
	}

	private static void dijkstra(int index) { 
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V];
		dist[index] =0;
		
		pq.add(new Node(index,0));
		
		while(!pq.isEmpty()) { 
			// 현재 기준에서 가장 인접한 Node, now
			Node node = pq.poll();
			int now = node.index;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Node c : list.get(now)) {
				if(!visited[c.index] && dist[c.index] > (c.weight + dist[now])) {
					dist[c.index] = c.weight + dist[now];
					pq.add(new Node(c.index, dist[c.index]));
				}
			}
		} 
	}
}

