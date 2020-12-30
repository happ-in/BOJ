package BOJ.BOJ;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class N17144 {
    static int R, C, T;
    static int[][] house;
    static Queue<Pos> dust = new ArrayDeque<>();

    static int[] dirX = new int[]{1, -1, 0, 0};
    static int[] dirY = new int[]{0, 0, 1, -1};

    static Pos top = new Pos(-1, -1);
    static Pos bot = new Pos(-1, -1);

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = parse(st.nextToken());
        C = parse(st.nextToken());
        T = parse(st.nextToken());

        house = new int[R][C];
        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++) {
                house[i][j] = parse(st.nextToken());
                if(house[i][j]==-1 && top.r==-1) top = new Pos(i, j);
                else if(house[i][j]==-1 && bot.r==-1) bot = new Pos(i, j);
            }
        }

        for(int i=0; i<T; i++){
            spread();
            air_condition();
        }

        int cnt = 0;
        for(int[] home : house){
            for(int h : home) if(h!=-1) cnt += h;
        }

        System.out.print(cnt);
    }

    static void spread(){
        int[][] tmp = new int[R][C];

        for(int i=0; i<R; i++) for(int j=0; j<C; j++) if(house[i][j]>0) dust.add(new Pos(i,j));
        while(!dust.isEmpty()){
            Pos pos = dust.poll();
            int spread_dust = house[pos.r][pos.c]/5;

            int cnt = 0;
            for(int i=0 ;i<4; i++){
                int nextR = pos.r+dirX[i];
                int nextC = pos.c+dirY[i];

                if(nextC>=C || nextC<0 || nextR>=R || nextR<0 || house[nextR][nextC]==-1) continue;

                cnt++;
                tmp[nextR][nextC] += spread_dust;
            }

            tmp[pos.r][pos.c] += (house[pos.r][pos.c] - spread_dust*cnt);
        }

        house = copy(tmp);
        house[top.r][top.c] = -1;
        house[bot.r][bot.c] = -1;
    }

    static void air_condition(){
        // top ->
        int top_right = 0;
        for(int i=top.c+1; i<C; i++){
            int tmp = house[top.r][i];
            house[top.r][i] = top_right;
            top_right = tmp;
        }

        // top up
        int top_up = top_right;
        for(int i=top.r-1; i>=0; i--){
            int tmp = house[i][C-1];
            house[i][C-1] = top_up;
            top_up = tmp;
        }

        // top <-
        int top_left = top_up;
        for(int i=C-2; i>=top.c; i--){
            int tmp = house[0][i];
            house[0][i] = top_left;
            top_left = tmp;
        }

        // top down
        int top_down = top_left;
        for(int i=1; i<top.r; i++){
            int tmp = house[i][top.c];
            house[i][top.c] = top_down;
            top_down = tmp;
        }

        // bottom ->
        int bot_right = 0;
        for(int i=bot.c+1; i<C; i++){
            int tmp = house[bot.r][i];
            house[bot.r][i] = bot_right;
            bot_right = tmp;
        }

        // bottom down
        int bot_down = bot_right;
        for(int i=bot.r+1; i<R; i++){
            int tmp = house[i][C-1];
            house[i][C-1] = bot_down;
            bot_down = tmp;
        }

        // bottom <-
        int bot_left = bot_down;
        for(int i=C-2; i>=bot.c; i--){
            int tmp = house[R-1][i];
            house[R-1][i] = bot_left;
            bot_left = tmp;
        }

        // bottom up
        int bot_up = bot_left;
        for(int i=R-2; i>bot.r; i--){
            int tmp = house[i][bot.c];
            house[i][bot.c] = bot_up;
            bot_up = tmp;
        }
    }

    static int[][] copy(int[][] arr){
        int[][] copy_dust = new int[R][C];
        for(int i=0; i<R; i++) for(int j=0; j<C; j++) copy_dust[i][j] = arr[i][j];
        return copy_dust;
    }

    static int parse(String s) {return Integer.parseInt(s);}

    static class Pos{
        int r, c;

        public Pos(int r, int c){
            this.r = r;
            this.c = c;
        }
    }
}
