import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static Queue<Integer> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}
		
		while(queue.size() > 1) {
			queue.poll();
			int under = queue.poll();
			queue.add(under);
			
			if(queue.size() == 1) {
				break;
			}
		}
		System.out.println(queue.poll());
		
	}
}

// ArrayList를 사용한 방식은 검색이 많이 필요할 때 이점이 있고,
// 해당 문제처럼 요소의 추가 제거가 잦은 상황에서는 LinkedList를 이용한 구현이 속도 면에서 유리하다.
// 가장 빨리 풀 수 있는 방법은, 수학적 접근으로 답을 도출하는 것
