import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
// 반례 
//50 12341234123412341234123412341234123412341234123412
//500000 3000000000

// 첫 시도 - 2^n 크기의 map을 사전에 만들어서 분할정복 로직을 설계해 값을 채워넣은 후 탐색하는 완전탐색 방식으로 접근했다.
// (힘들었던 점 : 그래프를 채워나가는 분할정복 문제는 처음 풀어봐서 수식을 작성하는데 많은 노력과 시도기 들었다.

// 제출 결과 --> 런타임 에러
// 원인 : 주어진 테스트 케이스가 매우 작은 값이라 해당 방법이 통했지만, 위와 같은 반례같이 매우 큰 숫자가 들어오면 Integer로 받지못한다. 

// 해결방법 
// 1. 기존의 점화식은 그대로 두고 맵만 삭제 후 좌표값 자체로만 탐색한다 (주어진 숫자에 해당하는 좌표 서칭)
//    1-1. 탐색은 4사분면 모두 진행하지 않고, 탐색할 값을 앞에서부터 쪼개서 탐색할 경우의 수를 하나로 줄인다 (프루닝, Queue 사용)
// 2. 주어진 좌표값에서 이동 후 좌표를 구한다 (Integer 값을 초과 하므로 long 선언 주의, 범위 밖으로 나갈 시 -1 출력)
// 3. 주어진 좌표의 값을 찾는 로직 설계 (위 분할 정복을 응용해서 , 좌표값 범위를 통한 가지치기를 한다.)
//    3-1. 탐색한 좌표의 값은 String을 ""에서 쌓아가는 방식으로 도출


public class Main {
	public static long H=1, W, N;
	public static long start_h = 1; 
	public static long start_w = 1; 
	public static long target_h; 
	public static long target_w; 
	public static Queue<Character> queStr = new LinkedList<>();
	public static String start;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for(int i=0; i<N; i++) H = H*2;
		W = H;
		start = st.nextToken();
		char[] arrChar = start.toCharArray();
		for (char c : arrChar) queStr.add(c);
		st = new StringTokenizer(br.readLine());
		long go_w = Long.parseLong(st.nextToken());
		long go_h = -Long.parseLong(st.nextToken());
		
		// logic
		dc_start(0,0,H-1, W-1);
		target_h = start_h+go_h;
		target_w = start_w+go_w;
		if(!check(target_h,target_w)) System.out.println(-1);
		else dc_target(0,0,H-1, W-1,"");
	}
	
	private static boolean check(long h, long w) {
		if(h<0 || h>=H || w<0 || w>=W) return false;
		return true;
	}

	public static void dc_start(long p1h, long p1w, long p2h, long p2w) {
		if(p1h == p2h && p1w == p2w) {
			start_h = p1h;
			start_w = p1w;
			return;
		}
		if(queStr.isEmpty()) return;
		char now = queStr.poll();
		if(now == '1') {
			dc_start(p1h, p1w+(p2w-p1w)/2+1, p1h+(p2h-p1h)/2, p2w); // 1사분
		} else if (now == '2'){
			dc_start(p1h, p1w, p1h+(p2h-p1h)/2 , p1w+(p2w-p1w)/2); // 2사분
		} else if (now == '3') {
			dc_start(p1h+(p2h-p1h)/2+1, p1w, p2h, p1w+(p2w-p1w)/2); // 3사분
		} else if (now == '4') {
			dc_start(p1h+(p2h-p1h)/2+1, p1w+(p2w-p1w)/2+1, p2h, p2w); // 4사분
		}  
	}
	
	private static void dc_target(long p1h, long p1w, long p2h, long p2w, String now) {
		if(p1h==target_h && p1w==target_w && p2h==target_h && p2w==target_w ) {
			System.out.println(now);
			return;
		}
		if(p1h > target_h || p2h < target_h || p1w > target_w || p2w < target_w ) return;
		dc_target(p1h, p1w+(p2w-p1w)/2+1, p1h+(p2h-p1h)/2, p2w,now+"1"); // 1사분
		dc_target(p1h, p1w, p1h+(p2h-p1h)/2 , p1w+(p2w-p1w)/2,now+"2"); // 2사분
		dc_target(p1h+(p2h-p1h)/2+1, p1w, p2h, p1w+(p2w-p1w)/2,now+"3"); // 3사분
		dc_target(p1h+(p2h-p1h)/2+1, p1w+(p2w-p1w)/2+1, p2h, p2w,now+"4"); // 4사분
	}
	
}
