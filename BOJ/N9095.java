package com.company.bakjoon;

import java.io.*;

public class N9095 {

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = parse(br.readLine());
        int[] dp = new int[11];

        dp[1] = 1; dp[2] = 2; dp[3] = 4;
        for(int i=0; i<TC; i++) {
            int n = parse(br.readLine());
            for(int j=4; j<=n; j++){
                dp[j] = dp[j-1] + dp[j-2] + dp[j-3];
            }
            System.out.println(dp[n]);
        }
    }

    static int parse(String s) {return Integer.parseInt(s);}
}
