package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N15486 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] consulting = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            consulting[i][0] = Integer.parseInt(st.nextToken());
            consulting[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N+1];

        int max = 0;
        for(int i=0; i<N; i++) {
            max = Math.max(max, dp[i]);

            int time = consulting[i][0];
            int price = consulting[i][1];

            if(i+time <= N) {
                dp[i+time] = Math.max(dp[i+time], max+price);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
