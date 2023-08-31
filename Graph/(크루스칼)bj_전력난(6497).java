import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
// 0 0 입력까지 무한입력되는 희한한 양식의 문제
// 일반 크루스칼이랑 동일하지만 최소 값이 아닌 최대로 아낄 수 있는 비용을 구하는 문제이므로 totalCost에서 뺀 값을 출력해야한다.
// 첫 시도에서 시간초과가 발생한 이유 - find에서 부모노드를 찾는 데 노드가 한쪽으로 편향되게 연결되어있으면 탐색 시간이 오래걸린다.
// 때문에 교수님이 수업시간에 알려주셨던 r배열을 따로 생성해서 균등하게 나뉘어지도록 하여 탐색 시간을 줄여야 통과 가능하다.
public class Main {
	static class Node implements Comparable<Node> {
		int a;
		int b;
		int w;
		public Node(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {	
			return this.w - o.w;
		}
		
	}
	
	static int V, E, min, max;
	static PriorityQueue<Node> points;
	static int[] p, r;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 

		while(true){
			st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			if(V==0 && E==0) break;
			
			points = new PriorityQueue<Node>();
			min=0;
			max=0;
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				points.add(new Node(a,b,w));
				max += w;
			}
			p = new int[V];
			r = new int[V];
			for (int i = 0; i < V; i++) {
				p[i] = i;
				r[i] = i;
			}
			
			int pqsize = points.size();
			for (int i = 0; i < pqsize; i++) {
				Node n = points.poll();
				if(union(n.a,n.b)) {
					min += n.w;
				}
			}

			System.out.println(max-min);

		}
		br.close();

	}
	private static boolean union(int a, int b) {
		int x = find(a);
		int y = find(b);
		if(x==y) return false;
		else {
			if(r[x] < r[y]) {
				r[y] += r[x];
				p[x]=y;
			} else {
				r[x]+=r[y];
				p[y]=x;
			}
		}
		return true;
	}
	
	private static int find(int a) {
		if(p[a] == a) return a;
		else return (a=find(p[a]));
	}
	
}
