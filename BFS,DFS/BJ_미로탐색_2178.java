import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int H, W;
	static int[][] map;
	static int[][] visited;
	static int[] dh = {-1,0,1,0};
	static int[] dw = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map= new int[H][W];
		visited = new int[H][W];
		
		for (int h = 0; h < H; h++) {
			String s = br.readLine();
			char[] cs = s.toCharArray();
			for (int w = 0; w < W; w++) {
				map[h][w] = cs[w]-'0';
			}
		}
//		print();
		
		int result=0;
		Queue<int[]> queue = new LinkedList<>();
		visited[0][0]=1;
		queue.offer(new int[] {0,0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int h=cur[0];
			int w=cur[1];
			if(h==H-1 && w==W-1) {
				break;
			}
			for(int d=0; d<4; d++) {
				int nexth = h+dh[d];
				int nextw = w+dw[d];
				if(!check(nexth,nextw)) continue;
				if(map[nexth][nextw]==0) continue;
				if(visited[nexth][nextw] == 0) {
					queue.add(new int[] {nexth, nextw});
					visited[nexth][nextw] =visited[h][w]+1;
				}
			}
		}
		
		System.out.println(visited[H-1][W-1]);
	}
	
	
	private static boolean check(int h, int w) {
		return h>=0 && h<H && w>=0 && w<W;
	}


	public static void print() {
		StringBuilder sb = new StringBuilder();
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				sb.append(map[h][w] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
}
