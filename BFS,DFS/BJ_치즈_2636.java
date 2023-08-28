import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 탐색을 치즈가 아닌 빈 공간부터 하는것이 관건이다.
public class Main {
	public static int H, W;
	static int[][] map;
	static boolean[][] visited;
	static int time;
	static int lastCheeze;
	static int countCheeze;
	static Queue<int[]> que;
	static final int[] dh = new int[] {0, 1, 0, -1};
	static final int[] dw = new int[] {-1, 0 , 1, 0}; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		
		time=0;
		countCheeze = 0;
		for (int h = 0; h < H; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
				if(map[h][w]==1) countCheeze++;
			}
		} // end- roof
		lastCheeze = countCheeze;
		
		while(countCheeze != 0) {
			time++;
			visited = new boolean[H][W];
			bfs();
		}
		System.out.println(time);
		System.out.println(lastCheeze);
	}
	
	private static void bfs() {
		que = new LinkedList<>();
		que.add(new int[] {0,0});
		visited[0][0] = true;
		
		while(!que.isEmpty()) {
			int[] curr = que.poll();
			for (int d = 0; d < 4; d++) {
				int nh = curr[0]+dh[d];
				int nw = curr[1]+dw[d];
				if(check(nh,nw) && !visited[nh][nw]) {// 범위 내 & 미방문
					visited[nh][nw] = true;
					if(map[nh][nw] == 0) {
						que.add(new int[] {nh, nw});
					} else {
						map[nh][nw] = 0;
						countCheeze--;
					}
				}
			}
		}
		if(countCheeze != 0) {
			lastCheeze = countCheeze;
		} 
	}

	private static boolean check(int h, int w) {
		if(h<0 || h>=H || w<0 || w>=W) {
			return false;
		}
		return true;
	}
}
