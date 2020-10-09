package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N18513 {
    static int parse(String s) {return Integer.parseInt(s);}
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = 210000000;
        boolean[] visited = new boolean[num];

        int N = parse(st.nextToken());
        int K = parse(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Queue<Pos> q = new ArrayDeque<>();
        for(int i=0; i<N; i++){
            int position = parse(st.nextToken()) + num/2;

            q.add(new Pos(position, 0));
            visited[position] = true;
        }

        int[] dir = {-1, 1};
        long answer = 0;
        while(!q.isEmpty()) {
            Pos now = q.poll();
            long idx = now.idx;
            long dist = now.dist;

            answer += dist;
            for(int i=0; i<2; i++){
                if(K==0) break;

                long next_idx = idx+dir[i];
                long next_dist = dist+1;

                if(next_idx<0 || visited[(int) next_idx]) continue;

                visited[(int) next_idx] = true;
                q.add(new Pos(next_idx, next_dist));
                K--;
            }
        }

        System.out.println(answer);
    }

    static class Pos{
        long idx, dist;

        public Pos(long idx, long dist){
            this.idx = idx;
            this.dist = dist;
        }
    }
}
