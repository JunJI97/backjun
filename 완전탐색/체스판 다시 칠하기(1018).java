import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int H;
	static int W;
	static int answer = 10000;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new char[H][W];
		
		for (int h = 0; h < H; h++) {
				char[] line = br.readLine().toCharArray();
			for (int w = 0; w < W; w++) {
				map[h][w]= line[w];
			}
		}

		// 1. 반복문 수식 구하기
		for (int sh = 0; sh <= H-8; sh++) {
			for (int sw = 0; sw <= W-8; sw++) {
//				// diff 개수  count => 정답  
				count_tile('B',sh,sw);
				count_tile('W',sh,sw);
			}
		}

		System.out.println(answer);
	}
	private static void count_tile(char start_tile, int sh, int sw) {
		int count = 0;
		int index_line = 0;
		for (int h = sh; h < sh+8; h++) {
			int index_w = 0;
			for (int w = sw; w < sw+8; w++) {
				//////////////////////////////
				if(index_line % 2 == 0) {
					if(index_w % 2 == 0) {
						if(map[h][w] != start_tile) {
							count++;
						}
					} else {
						if(map[h][w] == start_tile) {
							count++;
						}
					}
				} else { // 1, 3 ,5..
					if(index_w % 2 == 0) {
						if(map[h][w] == start_tile) {
							count++;
						}
					} else {
						if(map[h][w] != start_tile) {
							count++;
						}
					}
				}
				//////////////////////////////
				index_w++;
			}
			index_line++;
		}	
		if (count < answer) {
			answer = count;
		}
		
	}
}
