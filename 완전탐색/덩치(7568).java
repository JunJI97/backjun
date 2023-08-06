import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static int N;
	static int dung;
	
	static class P implements Comparable<P> {
		int x;
		int y;
		public P (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int compareTo(P p) {
			int rx = x - p.x;
			int ry = y - p.y;
			if((rx>0) && (ry>0)) {
				return -1;
			} else if (rx<0 && ry<0) {
				return 1;
			} else return 0;
		}
	}
	
	static List<P> list;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		list=new LinkedList<>();
		for (int i=0; i<N; i++) {
			list.add(new P(sc.nextInt(), sc.nextInt()));
		}

		StringBuilder sb = new StringBuilder();

		
		for (P p1:list) {
			int count=1;
			for (P p2:list) {
				if (p1.compareTo(p2)==1) {
					count++;
				}
			}
			sb.append(count);
			sb.append(" ");
		}
		
		System.out.println(sb);

		
	}

}
