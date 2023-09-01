import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 제곱근끼리 연산의 특징을 응용해서 분할정복을 이용한 방법으로 연산횟수를 줄이는 것이 포인트
public class Main {
	static int c;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		System.out.println(dvd(a,b)%c);
	}
	
	private static long dvd(int a, int b) {
		if(b == 1) {
			return a%c;
		}
		
		long temp = dvd(a,b/2); // 제곱근을 반토막
		if(b%2==1) return (temp*temp%c)*a %c; // 결과 값을 서로 곱하면 원래랑 같음
		return (temp*temp)%c;
	}
}
