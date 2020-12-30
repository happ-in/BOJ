package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N3109 {

    static int[] dirx = {-1, 0, 1};
    static int[] diry = {1, 1, 1};

    static int R, C;
    static int cnt = 0;

    static boolean[][] v;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        v = new boolean[R][C];
        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'x') v[i][j] = true;
            }
        }

        for(int i=0; i<R; i++) {
            dfs(i, 0);
        }

        System.out.print(cnt);
    }

    static boolean dfs(int x, int y) {
        for(int i=0; i<3; i++) {
            int nx = x+dirx[i];
            int ny = y+diry[i];

            if(nx<0 || nx>=R || ny<0 || ny>=C || v[nx][ny]) continue;

            v[nx][ny] = true;
            if(ny == C-1) {
                cnt++;
                return true;
            }

            if(dfs(nx, ny)) return true;
        }

        return false;
    }
}
