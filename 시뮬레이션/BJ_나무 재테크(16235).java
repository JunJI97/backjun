import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList; 
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

 // 시뮬레이션 & 자료구조 문제
// 타일 한칸에 어떻게 다수의 나무 정보를 관리하는지 고민하는 것이 관건인 문제
// map을 3차원으로 구성해서 풀이한 사람도 많았지만
// 나이가 작은 나무부터 양분을 흡수하는 조건 덕분에 우선순위 큐를 사용해서 모든 나무를 관리함

public class Main { 
	static int N, M, K;
	static int[][] map;
	static int[][] energy; 
	static PriorityQueue<Tree> pq = new PriorityQueue<>();
	static Queue<Tree> deadTree = new LinkedList<>();
	static int[][] dir = new int[][] {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
	
	static class Tree implements Comparable<Tree> {
		public int h;
		public int w;
		public int age;
		
		public Tree(int h, int w, int age) {
			this.h = h;
			this.w = w;
			this.age = age;
		}
		
		public void grow() {
			this.age++;
		}
		
		public int compareTo(Tree o) {
			return this.age - o.age;
		} 
	}
	
	public static void main(String[] args) throws IOException {
		// setting
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		energy = new int[N][N];
		 
		for (int h = 0; h < N; h++) {
			st = new StringTokenizer(br.readLine());
			for (int w = 0; w < N; w++) {
				energy[h][w] = Integer.parseInt(st.nextToken());
				map[h][w] = 5;
			}
		}
		 
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());
			pq.add(new Tree(h,w,age));
		}
		
		for (int year = 0; year < K; year++) { 
			// 봄
			growTree();
			// 여름
			deadToEnergy();
			// 가을
			increaseTree();
			// 겨울
			suplyEnergy();  
		}  
		// 남아 있는 나무 개수
		System.out.println(pq.size());
	}
 	  
	private static void growTree() { 
		// 나무가 자신의 나이만큼 양분을 먹고 나이가 1 증가 (어린 나무부터), 못먹은 나무는 즉시 사망
		ArrayList<Tree> temp = new ArrayList<>();
		int qsize = pq.size();
		for(int i=0; i<qsize; i++) {
			Tree tree = pq.poll();
			if(tree.age <= map[tree.h][tree.w]) {
				// 양분 흡수 
				map[tree.h][tree.w] -= tree.age;
				tree.grow();
				// temp에 보관
				temp.add(tree); 
			} else {
				// dead
				deadTree.add(tree);
//				map[tree.h][tree.w] = 0;
			}
		} // end -roof
		
		for(Tree tree : temp) {
			pq.add(tree);
		}
	}

	private static void deadToEnergy() { 
		// 죽은 나무의 나이 / 2 값을 양분으로 추가
		while(!deadTree.isEmpty()) {
			Tree tree = deadTree.poll(); 
			int plus = (tree.age) /2; 
			map[tree.h][tree.w] += plus;
		} 
	}

	private static void increaseTree() { 
		// 나이가 5의 배수인 인접 8칸에 나이 1 나무 생성
		ArrayList<Tree> temp = new ArrayList<>();
		while(!pq.isEmpty()) { 
			Tree tree = pq.poll();
			temp.add(tree);
			if(tree.age % 5 == 0) {
				for (int d = 0; d < 8; d++) {
					int next_h = tree.h + dir[d][0];
					int next_w = tree.w + dir[d][1];
					if(check(next_h, next_w)) {
						// 해당 타일에 나무 추가 (pq)
						temp.add(new Tree(next_h, next_w, 1));
					}
				}
			}
		} 
		
		for(Tree tree : temp) {
			pq.add(tree);
		}
	}

	private static void suplyEnergy() { 
		// map += energy
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) {
				map[h][w] += energy[h][w];
			}
		}
	}
	
	private static boolean check(int h, int w) {
		if(h >= 0 && h < N && w >= 0 && w < N) {
			return true;
		}
		return false;
	}

	public static void print() {
		System.out.println("====");
		StringBuilder sb = new StringBuilder();
		for (int h = 0; h < N; h++) {
			for (int w = 0; w < N; w++) {
				sb.append(map[h][w] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}

