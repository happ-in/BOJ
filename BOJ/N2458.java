package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N2458 {
    static int INF = 99999;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = parse(st.nextToken());
        int M = parse(st.nextToken());

        int[][] height = new int[N+1][N+1];
        for(int[] h : height) Arrays.fill(h, INF);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = parse(st.nextToken());
            int b = parse(st.nextToken());

            height[a][b] = 1;
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i==j) continue;
                    height[i][j] = Math.min(height[i][j], height[i][k]+height[k][j]);
                }
            }
        }

        boolean[] visited = new boolean[N+1];
        Arrays.fill(visited, true);
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                if(height[i][j] == INF && height[j][i] == INF){
                    visited[i] = false;
                    break;
                }
            }
        }

        int answer = 0;
        for(int i=1; i<=N; i++){
            if(visited[i]) answer++;
        }

        System.out.print(answer);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}

