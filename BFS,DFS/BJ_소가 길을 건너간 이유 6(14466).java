package bj.ssafy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList; 
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 스터디원들이 문제 이해가 어렵다고하는 문제,
// 요점은 다리를 건너는 것이 아니라, 건너지 않는 경우의 수를 구하는 것
// 정답 : 전체 가능한 소들의 쌍 수 - 탐색 중 만난 소들의 쌍 

// 첫 시도 => HashMap과 ArrayList를 이용한 방법 
// 두 번째 시도 => int[][][] map 3차원 배열을 이용한 탐색
// 세 번째 시도 ==> boolean visited[][][]를 이용한 방문 처리 (정답)

// 메모리 초과 때문에 많은 시도를 했던 문제
// 문제점을 찾은 결과 visited를 이용한 방문처리가 생각과는 다르게 작동해서 일정 구간에서 무한 반복이 일어남
// 결론은 위 3가지 방법 다 다시 시도했어도 정답이 도출되었을 것 같음

public class Main {
	static int N, K, R, cowN, answer; 
	static int starth, startw;
	static int[][] map;
	static boolean[][][] visited;
	static Queue<int[]> que;
	static int[] dh = new int[] {0,0,1,-1};
	static int[] dw = new int[] {1,-1,0,0};  
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken()); 
 
		// map setting & Queue setting
		// k = depth , 0:value , 1:동 , 2:서, 3:남 , 4:북
		map = new int[N][N];
		que = new LinkedList<>(); 
		ArrayList<int[]> cowList = new ArrayList<>();
		for(int h=0; h<N; h++) {
			for(int w=0; w<N; w++) { 
					map[h][w] = 0; 
			}
		}
		
		// 타일 간 도로 
		visited = new boolean[N][N][5]; 
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			int h1 = Integer.parseInt(st.nextToken())-1;
			int w1 = Integer.parseInt(st.nextToken())-1;
			int h2 = Integer.parseInt(st.nextToken())-1;
			int w2 = Integer.parseInt(st.nextToken())-1;
      // visited의 3번째 domain에 방문 불가능한 (다리가 놓여있는) 방향은 True 처리
			// 1:동 , 2:서, 3:남 , 4:북
			if(h1 == h2) {
				if (w1 < w2) {
					visited[h2][w2][2] = true;
					visited[h1][w1][1] = true; 
				} else {
					visited[h1][w1][2] = true;
					visited[h2][w2][1] = true;  
				}
			} else if (w1 == w2) {
				if (h1 < h2) {
					visited[h1][w1][3] = true;
					visited[h2][w2][4] = true; 
				} else {
					visited[h1][w1][4] = true;
					visited[h2][w2][3] = true; 
				} 
			} 
		} // end - 도로 세팅
		
		// cow 위치 입력
		for(int k=0; k<K; k++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken())-1;
			map[h][w] = 1; // 소
			cowList.add(new int[] {h,w}); 
			cowN ++;
		}
		// end map setting
		 
		answer = 0;
		for(int i=1; i<cowN; i++) {
			answer += i;
		} 
		
		// logic 
		for(int i=0; i<cowList.size(); i++) {
			int[] curr = cowList.get(i); 
			que.add(new int[] {curr[0],curr[1]});   
			starth = curr[0];
			startw = curr[1];   
			bfs(); // bfs 내부에서 소를 만날때 마다 answer 값 차감
			map[starth][startw] = 0; // 중복 카운트 제외를 위해 탐색완료한 소는 삭제
			cowN--;

			for (int h = 0; h < N; h++) {
				for (int w = 0; w < N; w++) { 
						visited[h][w][0] = false;
				}
			}
		} 
		
		System.out.println(answer);
	}
	
	private static void bfs() { 
		int meet = 0;
		while(!que.isEmpty()) { 
			int[] curr = que.poll();  
			int nh = curr[0];
			int nw = curr[1]; 
			
			if(visited[nh][nw][0]) {
				continue;
			}
      
			if(map[nh][nw] == 1 && !visited[nh][nw][0] && (starth != nh || startw != nw)) {
				answer--;
				meet++;
				if(meet == cowN-1) {
					que.clear();
					return;
					}  
			} 
			visited[nh][nw][0] = true;
			for(int d=0; d<4; d++) { // 4way 탐색
				int nexth = nh + dh[d];
				int nextw = nw + dw[d]; 
				if(check(nexth, nextw)) { // check range 
					if(!visited[nh][nw][d+1] && !visited[nexth][nextw][0]) {
						que.offer(new int[] {nexth, nextw});  
					} 
				}
			} 
		} 
	} 

	private static boolean check(int h, int w) {
		if(h>=0 && h<N && w >=0 && w<N) {
			return true;
		}
		return false;
	}

}
 
