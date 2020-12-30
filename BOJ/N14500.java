package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N14500 {

    static int max = Integer.MIN_VALUE;
    static int N, M;
    static int[][] board;
    static boolean[][] visited;

    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken()); M = parse(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) board[i][j] = parse(st.nextToken());
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                dfs(i, j, 0, 0);
                T(i, j);
            }
        }

        System.out.print(max);
    }

    static void dfs(int x, int y, int cnt, int sum){
        if(cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<4; i++) {
            int nextX = x+dirX[i];
            int nextY = y+dirY[i];

            if(nextX>=N || nextX<0 || nextY>=M || nextY<0) continue;
            if(visited[nextX][nextY]) continue;;

            visited[nextX][nextY] = true;
            dfs(nextX, nextY, cnt+1, sum+board[nextX][nextY]);
            visited[nextX][nextY] = false;
        }
    }

    static void T(int x, int y){
        int sum = 0;
        int down = x+1; int up = x-1;
        int right = y+1; int left = y-1;

        if(x==0) {
            if(y==0 || y==M-1) return;
            sum = board[x][y] + board[x][left] + board[x][right] +board[down][y];
            max = Math.max(max, sum);
        }
        else if(x==N-1){
            if(y==0 || y==M-1) return;
            sum = board[x][y] + board[x][left] + board[x][right] +board[up][y];
            max = Math.max(max, sum);
        }
        else if(y==0){
            sum = board[x][y] + board[up][y] + board[down][y] + board[x][right];
            max = Math.max(max, sum);
        }
        else if(y==M-1){
            sum = board[x][y] + board[up][y] + board[down][y] + board[x][left];
            max = Math.max(max, sum);
        }
        else {
            sum = board[x][y] + board[x][left] + board[x][right] +board[up][y] + board[down][y];
            max = Math.max(max, Math.max(sum-board[x][right], Math.max(sum-board[x][left], Math.max(sum-board[up][y], sum-board[down][y]))));
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
