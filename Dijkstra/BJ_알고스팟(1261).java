import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Main {  
	public static int H, W; 
	public static final int INF = Integer.MAX_VALUE; 
	public static int[][] map, dist;
	public static int[][] dr = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
	public static class Node implements Comparable<Node> {
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
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		dist = new int[H][W];
		
		for (int h = 0; h < H; h++) { 
			String[] line = br.readLine().split("");
			for (int w = 0; w < W; w++) {
				map[h][w] = Integer.parseInt(line[w]);   
				dist[h][w] = INF;
			}
		}
		dist[0][0] = 0;
		
		Dijkstra();
		System.out.println(dist[H-1][W-1]);
	}

	private static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[H][W];
		
		pq.add(new Node(0,0,0));
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int now_h = node.h;
			int now_w = node.w;
			if(visited[now_h][now_w]) continue;
			visited[now_h][now_w] = true;
			
			for(int d=0; d<4; d++) {
				int adj_h = now_h + dr[d][0];
				int adj_w = now_w + dr[d][1];
				if(check(adj_h, adj_w)) {
					if(!visited[adj_h][adj_w] && dist[adj_h][adj_w] > dist[now_h][now_w] + map[adj_h][adj_w]) {
						dist[adj_h][adj_w] = dist[now_h][now_w] + map[adj_h][adj_w];
						pq.add(new Node(adj_h, adj_w, dist[adj_h][adj_w]));
					}
				}
			}
		} 
	}

	private static boolean check(int h, int w) { 
		if(h>=0 && h<H && w >=0 && w < W) {
			return true;
		}
		return false;
	}

}

