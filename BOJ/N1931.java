package com.company.bakjoon;


import java.io.*;
import java.util.*;

public class N1931 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o1[1], o2[1]);
            }
        });

        int cnt = 0;
        int end = -1;
        for(int i=0; i<N; i++) {
            if(end < arr[i][0]) {
                cnt++;
                end = arr[i][1];
            }
        }

        System.out.println(cnt);
    }

}