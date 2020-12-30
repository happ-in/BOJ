package BOJ.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7576 {
    static int[][] tomato;
    static int N, M;
    static Queue<int[]> q = new ArrayDeque<>();

    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = parse(st.nextToken()); N = parse(st.nextToken());
        tomato = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                tomato[i][j] = parse(st.nextToken());
                if(tomato[i][j]==1) q.add(new int[]{i, j});
            }
        }

        if(q.isEmpty()) {
            System.out.print("-1");
            return;
        }

        spread(0);

    }

    static void spread(int cnt){
        int n = q.size();

        while(n>0){
            n--;
            int[] tmp = q.poll();
            int x = tmp[0]; int y = tmp[1];

            for(int i=0; i<4; i++){
                int nextX = x+dirX[i]; int nextY = y+dirY[i];
                if(nextX>=N || nextX<0 || nextY>=M || nextY<0) continue;
                if(tomato[nextX][nextY]!=0) continue;
                tomato[nextX][nextY] = 1;
                q.add(new int[]{nextX, nextY});
            }
        }

        if(q.isEmpty()) {
            if(tf()) System.out.print(cnt);
            else System.out.print("-1");
            return;
        }
        else spread(cnt+1);
    }

    static boolean tf(){
        boolean flag = true;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++) {
                if (tomato[i][j] == 0) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
