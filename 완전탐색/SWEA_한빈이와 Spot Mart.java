문제 링크 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AW8Wj7cqbY0DFAXN
// 그리디와 완전탐색을 응용해서 해결한 문제
// 진짜 모두 완전 탐색을 돌려버리면 시간초과가 일어난다.
// 정렬 후 뒤에서 부터 탐색하고, 특정 조건을 넣어서 연산을 모두 하지 않도록 해결했다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
public class Solution {
    public static int N;
    public static int max_weight;
    public static int max_now;
    public static int[] snack;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t = 1; t < T+1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            snack = new int[N];
            max_weight = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
             
            for (int i = 0; i < N; i++) {
                snack[i] = Integer.parseInt(st.nextToken());
            }
 
            // logic
            max_now = -1;
            Arrays.sort(snack);
             
            for (int i = N-1; i >= 1; i--) {
                for (int j = i-1; j >= 0; j--) {
                    int temp = snack[i] + snack[j];
                    if(max_weight >= temp && max_now < temp) {
                        max_now = temp;
                        break;
                    }
                }
            }
            System.out.println("#"+t+" "+max_now);
        }
    }
}
