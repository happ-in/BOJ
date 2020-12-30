package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N2667 {
    static int[][] map;
    static boolean[][] v;
    static Queue<int[]> q = new ArrayDeque<>();
    static PriorityQueue pq = new PriorityQueue();

    static int N;

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = parse(br.readLine());

        map = new int[N][N];
        for(int i=0; i<N; i++){
            String[] tmp = br.readLine().split("");
            for(int j=0; j<N; j++) {
                map[i][j] = parse(tmp[j]);
                if(map[i][j] == 1) q.add(new int[]{i, j});
            }
        }

        v = new boolean[N][N];

        int cnt = 0;
        while(!q.isEmpty()){
            int[] n = q.poll();
            int x = n[0]; int y = n[1];

            if(v[x][y]) continue;
            spread(n);
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(cnt+"\n");

        while(!pq.isEmpty()) sb.append(pq.poll() + "\n");
        System.out.print(sb.toString());

    }

    static void spread(int[] n){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(n);

        int cnt = 0;
        while(!queue.isEmpty()){
            cnt++;
            int[] tmp = queue.poll();
            int x = tmp[0]; int y = tmp[1];

            v[x][y] = true;
            for(int i=0; i<4; i++){
                int nextX = x+dirX[i]; int nextY = y+dirY[i];

                if(nextX>=N || nextX<0 || nextY>=N || nextY<0 || v[nextX][nextY]) continue;
                if(map[nextX][nextY] == 1){
                    v[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        pq.add(cnt);
    }

    static int parse(String s){return Integer.parseInt(s);}
}
