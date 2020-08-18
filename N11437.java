package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N11437 {
    static int N, M;
    static ArrayList<ArrayList<Integer>> list;
    static int[] depth, parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parse(br.readLine());

        list = new ArrayList<>();
        for(int i=0; i<=N; i++) list.add(new ArrayList<>());

        for(int i=0; i<N-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parse(st.nextToken());
            int y = parse(st.nextToken());

            list.get(x).add(y);
            list.get(y).add(x);
        }

        depth = new int[N+1];
        parent = new int[N+1];

        setDepth(1, 1);

        M = parse(br.readLine());
        for(int i=0; i<M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = parse(st.nextToken());
            int y = parse(st.nextToken());

            int num = solve(x, y);
            System.out.println(num);
        }
    }

    static void setDepth(int from, int d){
        depth[from] = d;
        for(int to : list.get(from)){
            if(depth[to] == 0){
                setDepth(to, d+1);
                parent[to] = from;
            }
        }
    }

    static int solve(int x, int y){
        int dx = depth[x]; int dy = depth[y];

        // depth 맞추기
        if(dx>dy){
            while(dx!=dy){
                x = parent[x];
                dx = depth[x];
            }
        } else if(dx<dy){
            while(dx!=dy){
                y = parent[y];
                dy = depth[y];
            }
        }

        // 같은 조상 찾기
        while(x!=y){
            x = parent[x];
            y = parent[y];
        }

        return x;
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
