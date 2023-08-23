import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
// 크루스칼 (간선이 적은 경우는 크루스칼 알고리즘이 유리하다.)
// 정점이 많은 경우는 프림 !
	static int V, E;
	static long min; // 최소 신장 트리 가중치 값
	static int[] p; 
	static PriorityQueue<Edge> points;
	
	static class Edge implements Comparable<Edge> {
    // 무향 그래프 이므로 정점 순서는 상관 없음
		int a; // 정점 1
		int b; // 정점 2
		int w; // 가중치 
		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) { // 가중치 오름차로 정렬 위함 (핵심 1)
			return this.w - o.w;
		}
	}
	
	/////////////////////
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V=Integer.parseInt(st.nextToken()); // 정점의 개수
		E=Integer.parseInt(st.nextToken()); // 간선의 개수
		
		points = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int w=Integer.parseInt(st.nextToken());
			points.add(new Edge(a,b,w));
		} // 가중치 오름차 순서로 우선순위 큐에 저장
		

		p=new int[V+1];
		makeSet();
		
		int pq_size = points.size();
		for (int i = 0; i < pq_size; i++) {
			Edge e = points.poll();
			if(union(e.a, e.b)) { // 합병 가능한 경우
				min += e.w; 
			}
		}
		
		System.out.println(min);
		
	}

	private static boolean union(int a, int b) { // 두 정점의 부모가 다르면 true
		int x = find(a);
		int y = find(b);
		if(x == y) return false; // 같은 경우 이미 합병된 상태이므로 Pass
		p[y] = x; // 병합당한 정점의 부모노드를 변경
    // 두 노드 중 어느쪽으로 붙을지는 find에 구현한 재귀 때문에 전혀 상관없다.
    // 하지만 조금이라도 속도를 개선하려면 균등하게 퍼지도록 추가구현 한다.
		return true;
	}

	private static int find(int x) {
		if (p[x] == x) return x; // 인덱스 값과 해당 인덱스 위치에 지정된 부모 노드가 같으면 그냥 반환
		else return p[x] = find(p[x]); // 다른 경우 재귀 형태로 타고타고 들어가서 부모 노드 불러옴
	}

	private static void makeSet() {
		for (int i=0; i < V+1; i++) { // 1에서 N까지 연속적인 값으로 배열 채우기
			p[i]=i;
		}
	}
}
