package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N14501 {

    static int N, max;
    static int[] T, P, dp;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine()); max = 0;
        T = new int[N+5]; P = new int[N+5]; dp = new int[N+5];

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = parse(st.nextToken()); P[i] = parse(st.nextToken());
        }

        for(int i=0; i<=N; i++){
            dp[i] = Math.max(dp[i], max);
            dp[T[i]+i] = Math.max(dp[T[i]+i], P[i]+dp[i]);
            max = Math.max(dp[i], max);
        }

        System.out.print(max);
    }

    static int parse(String s){
        return Integer.parseInt(s);
    }
}
