package BOJ.BOJ;

import java.io.*;
import java.util.*;


/** 2sec, 512MB*/
public class N14502 {

    static int N, M;
    static int[][] map;
    static LinkedList<Pos> wall = new LinkedList<>();
    static LinkedList<Pos> virus = new LinkedList<>();

    static int max = Integer.MIN_VALUE;
    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken()); M = parse(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = parse(st.nextToken());
                if(map[i][j] == 0) wall.add(new Pos(i, j));
                if(map[i][j] == 2) virus.add(new Pos(i, j));
            }
        }

        dfs(0);

        System.out.print(max);
    }

    static void dfs(int cnt){
        if(cnt==3) { spread(map); return; }

        for(int i=0; i<wall.size(); i++){
            int x = wall.get(i).x;
            int y = wall.get(i).y;

            if(map[x][y] != 0) continue;
            map[x][y] = 1;
            dfs(cnt+1);
            map[x][y] = 0;
        }
    }

    static void spread(int[][] mapc){
        int[][] mMap = new int[N][M];
        for(int i=0; i<N; i++) for(int j=0; j<M; j++) mMap[i][j] = mapc[i][j];

        LinkedList<Pos> vVirus = (LinkedList<Pos>) virus.clone();

        while(!vVirus.isEmpty()){
            Pos pos = vVirus.poll();

            for(int i=0; i<4; i++){
                int nextX = pos.x + dirX[i];
                int nextY = pos.y + dirY[i];

                if(nextX<0 || nextX>=N || nextY<0 || nextY>=M) continue;
                if(mMap[nextX][nextY] != 0) continue;;

                mMap[nextX][nextY] = 2 ;
                vVirus.add(new Pos(nextX, nextY));
            }
        }

        int cnt = 0;
        for(int i=0; i<N; i++) for(int j=0; j<M; j++) if(mMap[i][j] == 0) cnt++;

        max = Math.max(cnt, max);
    }

    static int parse(String s) {return Integer.parseInt(s);}

    static class Pos{
        int x; int y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}

