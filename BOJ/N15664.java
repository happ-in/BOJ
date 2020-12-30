package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N15664 {
    static int N, M;
    static int[] arr;
    static boolean[] v;
    static StringBuilder sb = new StringBuilder();
    static TreeSet<int[]> set = new TreeSet<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            for(int i=0; i<o1.length; i++){
                if(o1[i]!=o2[i]) return o1[i]-o2[i];
            }
            return o1[0]-o2[0];
        }
    });

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken()); M = parse(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = parse(st.nextToken());

        Arrays.sort(arr);
        v = new boolean[N];

        dfs(0, 0);

        for(int[] arr : set){
            for(int a : arr) System.out.print(a + " ");
            System.out.println();
        }
    }

    static void dfs(int idx, int cnt){
        if(cnt == M){
            int[] tmp = new int[M];
            int k = 0;
            for(int i=0; i<N; i++) if(v[i]) tmp[k++] = arr[i];
            set.add(tmp);
            return;
        }

        for(int i=idx; i<N; i++){
            if(v[i]) continue;
            v[i] = true;
            dfs(i+1, cnt+1);
            v[i] = false;
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
