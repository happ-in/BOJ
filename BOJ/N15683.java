package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N15683 {

    static int N, M;
    static int[][] map;
    static LinkedList<CCTV> cctvs = new LinkedList<>();
    static int min = Integer.MAX_VALUE;

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
                if(map[i][j]<6 && map[i][j]>0) cctvs.add(new CCTV(i, j, map[i][j], 0));
            }
        }

        dfs(0);

        System.out.print(min);
    }

    static int[][] mMap;
    static void init(){
        mMap = new int[N][M];
        for(int i=0; i<N; i++) for(int j=0; j<M; j++) mMap[i][j] = map[i][j];
    }

    static void record(CCTV cctv, int num){
        int x = cctv.x; int y = cctv.y;
        switch (num) {
            case 0:
                while (true) {
                    y++;
                    if (y >= M) break;
                    if (mMap[x][y] == 6) break;
                    mMap[x][y] = 7;
                }
                break;
            case 1:
                while (true) {
                    y--;
                    if (y < 0) break;
                    if (mMap[x][y] == 6) break;
                    mMap[x][y] = 7;
                }
                break;
            case 2:
                while (true) {
                    x++;
                    if (x >= N) break;
                    if (mMap[x][y] == 6) break;
                    mMap[x][y] = 7;
                }
                break;
            case 3:
                while (true) {
                    x--;
                    if (x < 0) break;
                    if (mMap[x][y] == 6) break;
                    mMap[x][y] = 7;
                }
                break;
            default:
                break;
        }

    }

    static void chk(CCTV cctv){
        int n = cctv.num;
        int rt = cctv.rt;

        switch (n){
            case 1:
                if(rt == 0) record(cctv, 0);
                else if(rt == 1) record(cctv, 2);
                else if(rt == 2) record(cctv, 3);
                else record(cctv, 1);
                break;
            case 2:
                if(rt == 0 || rt == 3){
                    record(cctv, 0);
                    record(cctv, 1);
                } else {
                    record(cctv, 2);
                    record(cctv, 3);
                }
                break;
            case 3:
                if(rt == 0){
                    record(cctv, 0);
                    record(cctv, 3);
                } else if (rt == 1){
                    record(cctv, 0);
                    record(cctv, 2);
                } else if (rt == 2) {
                    record(cctv, 1);
                    record(cctv, 3);
                } else {
                    record(cctv, 1);
                    record(cctv, 2);
                }
                break;
            case 4:
                if(rt == 0){
                    record(cctv, 0);
                    record(cctv, 1);
                    record(cctv, 3);
                } else if (rt == 1){
                    record(cctv, 0);
                    record(cctv, 2);
                    record(cctv, 3);
                } else if (rt == 2) {
                    record(cctv, 1);
                    record(cctv, 2);
                    record(cctv, 3);
                } else {
                record(cctv, 0);
                record(cctv, 1);
                record(cctv, 2);
                }
                break;
            case 5:
                record(cctv, 0);
                record(cctv, 1);
                record(cctv, 2);
                record(cctv, 3);
                break;
            default:
                break;
        }

    }

    /** 0:RIGHT, 1:LEFT, 2:DOWN, 3:UP */
    static void dfs(int idx){
        if(idx >= cctvs.size()){
            init();
            for(int i=0; i<cctvs.size(); i++) chk(cctvs.get(i));

            int cnt = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++){
                    if(mMap[i][j] == 0) cnt++;
                }
            }

            min = Math.min(cnt, min);
            return;
        }

        for(int i=0; i<=3; i++){
            cctvs.get(idx).rt = i;
            dfs(idx+1);
        }
    }

    static int parse(String s) {return Integer.parseInt(s);}

    static class CCTV{
        int x; int y; int num; int rt;

        public CCTV(int x, int y, int num, int rt){
            this.x = x;
            this.y = y;
            this.num = num;
            this.rt = rt;
        }
    }
}
