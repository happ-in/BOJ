package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N7569 {
    static ArrayList<int[][]> tomato = new ArrayList<>();
    static int M, N, H;
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = parse(st.nextToken());
        N = parse(st.nextToken());
        H = parse(st.nextToken());

        for(int i=0; i<H; i++){
            int[][] tmp = new int[N][M];
            for(int x=0; x<N; x++){
                st = new StringTokenizer(br.readLine());
                for(int y=0; y<M; y++){
                    tmp[x][y] = parse(st.nextToken());
                    if(tmp[x][y]==1) q.add(new int[]{x, y, i});
                }
            }
            tomato.add(tmp);
        }

        spread(0);
    }

    static void spread(int cnt){
        int n = q.size();

        while(n>0){
            n--;
            int[] tmp = q.poll();
            int x = tmp[0]; int y = tmp[1]; int h = tmp[2];

            int up = h-1; int down = h+1;
            if(up<H && up>=0 && tomato.get(up)[x][y]==0) {
                q.add(new int[]{x, y, up});
                tomato.get(up)[x][y] = 1;
            }
            if(down<H && down>=0 && tomato.get(down)[x][y]==0) {
                q.add(new int[]{x, y, down});
                tomato.get(down)[x][y] = 1;
            }

            for(int i=0; i<4; i++){
                int nextX = x+dirX[i]; int nextY = y+dirY[i];
                if(nextX>=N || nextX<0 || nextY>=M || nextY<0) continue;
                if(tomato.get(h)[nextX][nextY]!=0) continue;

                tomato.get(h)[nextX][nextY] = 1;
                q.add(new int[]{nextX, nextY, h});
            }
        }

        if(q.isEmpty()){
            if(tf()) System.out.print(cnt);
            else System.out.print("-1");
            return;
        }
        else spread(cnt+1);
    }

    static boolean tf(){
        boolean flag = true;
        for(int i=0; i<H; i++){
            for(int x=0; x<N; x++){
                for(int y=0; y<M; y++){
                    if(tomato.get(i)[x][y]==0){
                        flag = false;
                        break;
                    }
                }
                if(!flag) break;
            }
            if(!flag) break;
        }
        return flag;
    }

    static int parse(String s) {return Integer.parseInt(s);}
}
