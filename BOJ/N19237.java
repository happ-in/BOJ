package BOJ.BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class N19237 {
    static Pos[][] map1;
    static Pos[][] map2;
    static Shark[] sharks;
    static int N, M, k, MM;
    static int[][] dirs;
    static int cnt = 0;
    static int INF = Integer.MAX_VALUE;

    /** 1: 위
     *  2: 아래
     *  3: 왼
     *  4: 오른 */
    static int[] dirX = new int[]{0, -1, 1, 0, 0};
    static int[] dirY = new int[]{0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = parse(st.nextToken());
        M = parse(st.nextToken());
        k = parse(st.nextToken());

        MM = M;

        map1 = new Pos[N+1][N+1];
        map2 = new Pos[N+1][N+1];
        sharks = new Shark[M+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++){
                int tmp = parse(st.nextToken());
                if(tmp!=0){
                    map1[i][j] = new Pos(tmp, k);
                    map2[i][j] = new Pos(tmp, k);
                    sharks[tmp] = new Shark(i, j, 0);
                } else{
                    map1[i][j] = new Pos(INF, 0);
                    map2[i][j] = new Pos(INF, 0);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=M; i++) sharks[i].dir = parse(st.nextToken());

        dirs = new int[M*4+1][4];
        for(int i=1; i<=M*4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<4; j++){
                dirs[i][j] = parse(st.nextToken());
            }
        }

        move();

        System.out.print((cnt>=1000)&&MM>1? -1 : cnt);
    }

    static void move(){
        while(MM>1 && cnt<1000){
            for(int i=1; i<=M; i++){
                boolean chk = false;
                int x = sharks[i].x;
                int y = sharks[i].y;

                if(sharks[i].dir == -1) continue;

                // 1. 상어 냄새가 없는 곳
                for(int j=0; j<4; j++){
                    int nextX = x + dirX[dirs[(i-1)*4+sharks[i].dir][j]];
                    int nextY = y + dirY[dirs[(i-1)*4+sharks[i].dir][j]];

                    if(nextX<1 || nextX>N || nextY<1 || nextY>N) continue;

                    if(map2[nextX][nextY].smell == 0){
                        sharks[i].x = nextX;
                        sharks[i].y = nextY;

                        int num = map1[nextX][nextY].num;
                        if(num == INF){
                            map1[nextX][nextY].num = i;
                            map1[nextX][nextY].smell = k+1;
                            sharks[i].dir = dirs[(i-1)*4+sharks[i].dir][j];
                        } else{
                            if(num > i){
                                sharks[num].dir = -1;
                                map1[nextX][nextY].num = i;
                                map1[nextX][nextY].smell = k+1;
                            } else {
                                sharks[i].dir = -1;
                            }
                            MM--;
                        }

                        chk = true;
                        break;
                    }
                }

                if(chk) continue;

                // 2. 상하좌우 모두 상어 냄새가 있는 경우
                for(int j=0; j<4; j++){
                    int nextX = x + dirX[dirs[(i-1)*4+sharks[i].dir][j]];
                    int nextY = y + dirY[dirs[(i-1)*4+sharks[i].dir][j]];
//                    System.out.println("case2>" +i + " " + nextX + " " + nextY);

                    if(nextX<1 || nextX>N || nextY<1 || nextY>N) continue;

                    if(map2[nextX][nextY].num == i){
                        sharks[i].x = nextX;
                        sharks[i].y = nextY;
                        sharks[i].dir = dirs[(i-1)*4+sharks[i].dir][j];
                        map1[nextX][nextY].smell = k+1;
                        break;
                    }
                }
            }

            spread();
            copy();
            cnt++;
        }
   }

    static void spread(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(map1[i][j].smell>0) {
                    map1[i][j].smell -=1;
                    map2[i][j].smell -=1;

                    if(map1[i][j].smell == 0){
                        map1[i][j].num = INF;
                        map2[i][j].num = INF;
                    }
                }
            }
        }
    }

    static void copy(){
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                map2[i][j].num = map1[i][j].num;
                map2[i][j].smell = map1[i][j].smell;
            }
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }

    static class Pos{
        int num;
        int smell;

        public Pos(int num, int smell){
            this.num = num;
            this.smell = smell;
        }
    }

    static class Shark{
        int x; int y;
        int dir;

        public Shark(int x, int y, int dir){
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
}
