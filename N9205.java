package com.company.bakjoon;

import java.util.*;
import java.io.*;

public class N9205 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while(TC>0) {
            TC--;

            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] start = new int[2];
            start[0] = Integer.parseInt(st.nextToken());
            start[1] = Integer.parseInt(st.nextToken());

            ArrayList<int[]> store = new ArrayList<>();
            for(int i=0 ;i<N; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                store.add(new int[]{x, y});
            }

            st = new StringTokenizer(br.readLine());
            int[] end = new int[2];
            end[0] = Integer.parseInt(st.nextToken());
            end[1] = Integer.parseInt(st.nextToken());


            // bfs
            boolean[] v = new boolean[N];
            Queue<int[]> q = new ArrayDeque<>();
            q.add(start);

            boolean flag = false;
            while(!q.isEmpty()) {
                int[] n = q.poll();

                if(Math.abs(n[0]-end[0]) + Math.abs(n[1]-end[1]) <=1000) {
                    sb.append("happy\n");
                    flag = true;
                    break;
                }

                for(int k=0; k<N; k++) {
                    if(!v[k] && Math.abs(n[0]-store.get(k)[0])+Math.abs(n[1]-store.get(k)[1])<=1000) {
                        q.add(store.get(k));
                        v[k] = true;
                    }
                }
            }

            if(!flag) sb.append("sad\n");
        }

        System.out.print(sb.toString());
    }
}
