package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N4963 {
    static int[][] map;
    static boolean[][] v;
    static int w, h;

    static StringBuilder sb = new StringBuilder();
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dirX = new int[]{1, -1, 0, 0, 1, -1, 1, -1};
    static int[] dirY = new int[]{0, 0, 1, -1, 1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        while(!s.equals("0 0")){
            String[] tmp = s.split(" ");
            h = parse(tmp[0]); w = parse(tmp[1]);

            map = new int[w][h];
            v = new boolean[w][h];

            for(int i=0; i<w; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<h; j++){
                    map[i][j] = parse(st.nextToken());
                    if(map[i][j] == 1) q.add(new int[]{i, j});
                }
            }

            int cnt = 0;
            while(!q.isEmpty()){
                int[] n = q.poll();
                int x = n[0]; int y = n[1];

                if(v[x][y]) continue;
                bfs(n); cnt++;
            }

            sb.append(cnt+"\n");
            s = br.readLine();
        }

        System.out.print(sb.toString());
    }

    static void bfs(int[] pos){
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(pos);
        while(!queue.isEmpty()){
            int[] n = queue.poll();
            int x = n[0]; int y = n[1];

            for(int i=0; i<8; i++){
                int nextX = x+dirX[i]; int nextY = y+dirY[i];
                if(nextX>=w || nextX<0 || nextY>=h || nextY<0) continue;
                if(map[nextX][nextY]==1 && !v[nextX][nextY]){
                    v[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }
    }

    static int parse(String s) {return Integer.parseInt(s);}
}
