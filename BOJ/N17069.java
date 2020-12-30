package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N17069 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parse(br.readLine());
        int[][] arr = new int[N+2][N+2];

        for (int i=1; i<=N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j <= N; j++) {
                if(i==0 || j==0 || i>N || j>N) continue;
                arr[i][j] = parse(st.nextToken());
            }
        }

        for(int i=0; i<=N+1; i++){
            arr[N+1][i] = 1;
            arr[i][N+1] = 1;
            arr[0][i] = 1;
            arr[i][0] = 1;
        }

        long[][][] dp = new long[N+2][N+2][3];

        dp[1][2][0] = 1;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(arr[i][j]==1) continue;

                if(arr[i][j+1]==0){
                    dp[i][j+1][0] += dp[i][j][0];
                    dp[i][j+1][0] += dp[i][j][2];
                }

                if(arr[i+1][j]==0){
                    dp[i+1][j][1] += dp[i][j][1];
                    dp[i+1][j][1] += dp[i][j][2];
                }

                if(arr[i+1][j+1]==0 && arr[i+1][j]==0 && arr[i][j+1]==0){
                    dp[i+1][j+1][2] += dp[i][j][0];
                    dp[i+1][j+1][2] += dp[i][j][1];
                    dp[i+1][j+1][2] += dp[i][j][2];
                }
            }
        }

        long result = 0;
        for(int i=0; i<3; i++){
            result += dp[N][N][i];
        }

        System.out.print(result);
    }

    static int parse(String s){ return Integer.parseInt(s); }
}