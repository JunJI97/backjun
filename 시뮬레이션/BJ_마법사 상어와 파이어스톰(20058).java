import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue; 
import java.util.StringTokenizer;

// 문제 해결을 위해 필요한 것
// 1. 배열 구간 나누기 (분할정복 문제에서 다뤘던 경험을 기반으로 구햔)
// 2. 부분 배열 회전하기 
// 3. BFS로 얼음 덩어리 크기 계산

public class Main { 
	static int N, Q, totalIce=0, bigPart=0;
	static int[][] map;
	static boolean[][] visited;
	static int[] layer;
	static int[][] dir = new int[][] {{-1,0},{1,0},{0,1},{0,-1}};
	static Queue<int[]> que = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		// setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		N = 1;
		for(int i=0; i<n; i++) {
			N *= 2;
		}
		Q = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for(int h=0; h<N; h++) { // map -setting
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		} // end -map setting
		
		layer = new int[Q];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			layer[i] = Integer.parseInt(st.nextToken()); 
		}
		
		// logic 
		for(int round=0; round < Q; round++) { // 마법 수행
			int L = layer[round];  
			// 영역 분리, 회전
			dvd_map(L);    
			// 삭제 
			deleteIce();  
		}
		 
		// answer
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) {
				if(map[h][w] > 0) { 
					totalIce += map[h][w]; // 잔여 얼음 총합
				} else if (map[h][w] == 0) {
					visited[h][w] = true;  // 얼음이 없으면 방문 처리
				} 
				
				if(!visited[h][w]) {
					searchPart(h,w); // 잔여 얼음은 크기 계산
				}
			}
		}
		 
		System.out.println(totalIce);
		System.out.println(bigPart);
 
	}
 
	private static void searchPart(int h, int w) { // 얼음의 덩어리 크기 찾기
		int cnt=0;
		 
		que.add(new int[] {h,w}); 
		while(!que.isEmpty()) { // bfs 
			int[] item = que.poll(); 
			cnt++;
			visited[h][w] = true;
			
			for(int d=0; d<4; d++) { 
				int next_h = item[0] + dir[d][0];
				int next_w = item[1] + dir[d][1];

				if(check(next_h, next_w)) { // 미방문 타일 탐색 (얼음이 없는 타일은 이미 방문 처리된 상태)
					if(!visited[next_h][next_w]) {
						visited[next_h][next_w] = true;
						if(map[next_h][next_w] != 0) { 
							que.add(new int[] {next_h, next_w});
						} 
					}
				}
			} 
		}
		
		if(cnt > bigPart) { // 가장 큰 얼음 덩어리 크기 갱신
			bigPart = cnt;
		}
		
	}


	private static boolean check(int h, int w) { // 범위 이탈 방지
		if(h >= 0 && h < N && w >= 0 && w < N) {
			return true;
		}
		return false;
	}


	private static void dvd_map(int l) { // 타일 영역 나누기
		int n = 1;
		for (int i = 0; i < l; i++) {
			n *= 2; 
		}  

		// 각 영역의 0,0 위치만 반환
		for(int h=0; h<N; h++) {
			for (int w = 0; w < N; w++) {
				rotate(h,w,n); // rotate 돌리기 
				w += (n-1);
			}
			h += (n-1);
		}
		
	}

	private static void rotate(int h0, int w0, int n) { // 왼쪽 위 타일 기준으로 n 크기의 부분 배열 회전하기 
		int[][] window = new int[n][n];
		
		for (int h = 0; h < n; h++) {
			for (int w = 0; w < n; w++) {
				window[h][w] = map[h0+h][w0+w];
			}
		}
		
		for (int h = 0; h < n; h++) {
			for (int w = 0; w < n; w++) { 
				map[h0 + w][w0 + n-1-h] = window[h][w];
			}
		}	 
	}
	
	 
	private static void deleteIce() { 
		
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) { 
				int cnt = 0;
				for(int d=0; d<4; d++) {
					int next_h = h + dir[d][0];
					int next_w = w + dir[d][1];
					if(check(next_h, next_w)) {
						if(map[next_h][next_w] > 0) {
							cnt++;
						}
					}
				} // end -dir
				if (cnt < 3) { // 인접한 얼음이 2개 이하인 경우 (녹임)
					que.add(new int[] {h,w}); // 녹일 예정의 얼음들
				}
			} // w
		} // end -roof
		
		while(!que.isEmpty()) {  
			// 따로 모아서 한꺼번에 녹이는 이유는
			// 연쇄작용으로 잘못된 결과가 나오기 떄문
			int[] item = que.poll();
			map[item[0]][item[1]]--;
			if(map[item[0]][item[1]] < 0) {
				map[item[0]][item[1]] = 0;
			}
		}
		 
	}
}
