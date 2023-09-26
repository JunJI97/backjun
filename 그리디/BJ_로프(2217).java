import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;

// 정렬 후 작은 원소부터 하나씩 경우의 수 계산 후 정답 출력

public class Main { 
	static int N, max=0; 
	static int[] nums;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); 
		
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(br.readLine()); 
		} // end -roof
		
		Arrays.sort(nums);
		
		for (int i = 0; i < N; i++) {
			int temp = nums[i]*(N-i);
			if (max < temp) {
				max = temp;
			} 
		}
		System.out.println(max);
	}
}
