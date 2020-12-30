package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N2206 {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N+1][M+1];
        int[][] visited = new int[N+1][M+1];
        for(int i=1; i<=N; i++) {
            String[] tmp = br.readLine().split("");
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(tmp[j-1]);
            }
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[1][1] = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{1, 1, 0, 1}); // x좌표, y좌표, 공사횟수, 이동횟수

        while(!q.isEmpty()) {
            int[] n = q.poll();

            if(n[0]==N && n[1]==M) {
                System.out.print(n[3]);
                return;
            }

            for(int i=0; i<4; i++) {
                int nx = n[0]+dx[i];
                int ny = n[1]+dy[i];

                if(nx<1 || ny<1 || nx>N || ny>M) continue;
                if(visited[nx][ny] <= n[2]) continue;

                if(map[nx][ny]==0) {
                    visited[nx][ny] = n[2];
                    q.add(new int[]{nx, ny, n[2], n[3]+1});
                } else {
                    if(n[2]==0) {
                        visited[nx][ny] = n[2]+1;
                        q.add(new int[]{nx, ny, n[2]+1, n[3]+1});
                    }
                }
            }
        }

        System.out.print(-1);
    }
}
