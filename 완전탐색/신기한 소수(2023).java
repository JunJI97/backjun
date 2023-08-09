import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N;
	static int[] odds = new int[] {1,3,5,7,9};
	static Queue<Integer> queue = new LinkedList<>(); 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		queue.add(2);
		queue.add(3);
		queue.add(5);
		queue.add(7);
		recur(0);

	}

	private static void recur(int cnt) {
		if(cnt == N-1) {
			int n = queue.size();
			for (int i = 0; i < n; i++) {
				System.out.println(queue.poll());
			}
			return;
		}
		int n = queue.size();
		for (int i = 0; i < n; i++) {
			int prev = queue.poll();
			for (int j = 0; j < odds.length; j++) {
				int temp = prev*10 + odds[j];
				if(isPrime(temp)) {
					queue.add(temp);
				}
			}
		}
		recur(cnt+1);
	}

	private static boolean isPrime(int n) {
		for (int i = 2; i <= (int)(Math.sqrt(n)); i++) {
			if(n%i==0) { return false;}
		}
		return true;
	}

}
