package com.company.bakjoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 2sec, 512MB
 * 입력 : 수의 개수 N(2<= N <=11)
 *       Ai, ..., An (1<= Ai=100
 *       +, -, x, % 개수
 */

public class N14888 {
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int N;
    static int[] A;
    static int[] op = new int[4];

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());

        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());;
        for(int i=0; i<N; i++) A[i] = parse(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) op[i] = parse(st.nextToken());

        dfs(1, A[0]);

        System.out.print(max + "\n" +min);
    }

    static void dfs(int idx, int sum){
        if(idx == N) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<4; i++){
            if(op[i] == 0) continue;
            op[i]--;
            switch (i){
                case 0:
                    dfs(idx+1, sum+A[idx]);
                    break;
                case 1:
                    dfs(idx+1, sum-A[idx]);
                    break;
                case 2:
                    dfs(idx+1, sum*A[idx]);
                    break;
                case 3:
                    dfs(idx+1, sum/A[idx]);
                    break;
            }
            op[i]++;
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
