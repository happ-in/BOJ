package BOJ.BOJ;

import java.util.*;
import java.io.*;

public class N2468 {
    static int max = 0;
    static int N;
    static int[][] town;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());

        town = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                town[i][j] = parse(st.nextToken());
                max = Math.max(town[i][j], max);
            }
        }

        for(int i=0; i<=max; i++) water(i);
        System.out.print(divide);
    }

    static int[] dirX = {0, 0, 1, -1}; static int[] dirY = {1, -1, 0, 0};
    static int divide = 1;
    // true 물에 잠긴 경우
    static void water(int height){
        Queue<Pos> q = new ArrayDeque<>();
        boolean[][] water = new boolean[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(town[i][j] <= height) water[i][j] = true;
                else q.add(new Pos(i, j));
            }
        }

        boolean[][] check = new boolean[N][N];
        int cnt = 0;
        while(!q.isEmpty()){
            Pos now = q.poll();
            int x = now.x; int y = now.y;

            if(check[x][y]) continue;
            cnt++;

            Queue<Pos> q2 = new ArrayDeque<>();
            q2.add(now);
            while(!q2.isEmpty()){
                Pos q2_now = q2.poll();
                int qx = q2_now.x; int qy = q2_now.y;

                check[qx][qy] = true;
                for(int i=0; i<4; i++){
                    int nextX = qx+dirX[i];
                    int nextY = qy+dirY[i];

                    if(nextX>=N || nextX<0 || nextY>=N || nextY<0) continue;
                    if(!water[nextX][nextY] && !check[nextX][nextY]) {
                        check[nextX][nextY] = true;
                        q2.add(new Pos(nextX, nextY));
                    }
                }
            }
        }

        divide = Math.max(divide, cnt);
    }

    static int parse(String s) { return Integer.parseInt(s); }

    static class Pos{
        int x, y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
