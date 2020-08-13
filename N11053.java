package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N11053 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parse(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = parse(st.nextToken());

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for(int i=1; i<N; i++){
            for(int j=0; j<i; j++){
                if(arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }

        Arrays.sort(dp);
        System.out.print(dp[N-1]);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
