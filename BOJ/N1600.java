package com.company.bakjoon;

import java.util.*;
import java.io.*;

public class N1600 {
    static int[] horsex = {2, 2, -2, -2, 1, 1, -1, -1};
    static int[] horsey = {1, -1, 1, -1, 2, -2, 2, -2};

    static int[] dirx = {0, 0, 1, -1};
    static int[] diry = {1, -1, 0, 0};

    static int K, W, H;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = parse(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = parse(st.nextToken());
        H = parse(st.nextToken());

        boolean[][] map = new boolean[H][W];
        for(int i=0; i<H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<W; j++) {
                if(parse(st.nextToken())==1) map[i][j] = true;
            }
        }

        Queue<Pos> q = new ArrayDeque<>();
        q.add(new Pos(0, 0, K, 0));

        boolean[][][] visit = new boolean[H][W][K+1];
        visit[0][0][0] = true;

        int max = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Pos now = q.poll();
            if(now.x==H-1 && now.y==W-1) {
                max = Math.min(max, now.move);
                continue;
            }

            if(visit[now.x][now.y][now.k]) continue;
            visit[now.x][now.y][now.k] = true;
            for(int i=0; i<4; i++){
                int nextx = now.x + dirx[i];
                int nexty = now.y + diry[i];

                if(nextx>=H || nextx<0 || nexty>=W || nexty<0 || map[nextx][nexty]) continue;
                q.add(new Pos(nextx, nexty, now.k, now.move+1));
            }

            if(now.k==0) continue;
            for(int i=0; i<8; i++){
                int nextx = now.x + horsex[i];
                int nexty = now.y + horsey[i];

                if(nextx>=H || nextx<0 || nexty>=W || nexty<0 || map[nextx][nexty]) continue;
                q.add(new Pos(nextx, nexty, now.k-1, now.move+1));
            }
        }

        System.out.print(max == Integer.MAX_VALUE ? -1 : max);
    }

    static int parse(String s) { return Integer.parseInt(s); }

    static class Pos{
        int x, y, k, move;

        public  Pos(int x, int y, int k, int move){
            this.x = x;
            this.y = y;
            this.k = k;
            this.move = move;
        }
    }
}
