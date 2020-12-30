package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N2636 {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        // init: 값 받아오기
        int[][] cheese = new int[w][h];
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < h; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> outAir = new ArrayDeque<>();
        Queue<int[]> meltCheese = new ArrayDeque<>();

        int day = 0; int lastCheese = 0;
        while (true) {
            outAir.add(new int[]{0, 0});
            boolean[][] visited = new boolean[w][h];
            visited[0][0] = true;

            //1. 외부 공기 -1로 변경
            while(!outAir.isEmpty()) {
                int[] now = outAir.poll();

                for(int i=0; i<4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];

                    if(nx<0 || nx>=w || ny<0 || ny>=h) continue;
                    if(visited[nx][ny]) continue;

                    //2. 외부 접촉된 치즈
                    if(cheese[nx][ny]==1) {
                        meltCheese.add(new int[]{nx, ny});
                        continue;
                    }

                    cheese[nx][ny] = -1;
                    visited[nx][ny] = true;
                    outAir.add(new int[]{nx, ny});
                }
            }

            //3-1. 녹아야하는 치즈가 없으면 loop 탈출
            if(meltCheese.isEmpty()) break;


            //3-2. 남은 치즈 갯수
            int count = 0;
            for (int[] ch : cheese) {
                for (int c : ch) {
                    if(c==1) count++;
                }
            }
            lastCheese = count;

            //3-3. 치즈 녹이기
            while(!meltCheese.isEmpty()) {
                int[] melt = meltCheese.poll();
                cheese[melt[0]][melt[1]] = -1;
            }

            //4. 날짜 증가
            day++;
        }

        System.out.println(day);
        System.out.println(lastCheese);
    }
}
