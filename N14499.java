package com.company.bakjoon;

import java.io.*;
import java.util.*;

/**
 * 2sec, 512MB
 */
public class N14499 {

    static int N, M, K;
    static int[] dice = new int[]{0, 0, 0, 0, 0, 0, 0};
    static int[][] map;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken()); M = parse(st.nextToken());
        Pos pos = new Pos(parse(st.nextToken()), parse(st.nextToken()));
        K = parse(st.nextToken());

        map = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) { map[i][j] = parse(st.nextToken()); }
        }

        String[] turn = br.readLine().split(" ");


        /**     1:동 2:서 3:북 4:남     */
        int i=0;
        while(i<turn.length){
            switch (parse(turn[i])){
                case 1:
                    if(pos.y >= M-1) break;
                    pos = new Pos(pos.x, pos.y+1);
                    East();
                    cal(pos);
                    System.out.println(dice[1]);
                    break;
                case 2:
                    if(pos.y <= 0) break;
                    pos = new Pos(pos.x, pos.y-1);
                    West();
                    cal(pos);
                    System.out.println(dice[1]);
                    break;
                case 3:
                    if(pos.x <= 0) break;
                    pos = new Pos(pos.x-1, pos.y);
                    North();
                    cal(pos);
                    System.out.println(dice[1]);
                    break;
                default:
                    if(pos.x >= N-1) break;
                    pos = new Pos(pos.x+1, pos.y);
                    South();
                    cal(pos);
                    System.out.println(dice[1]);
                    break;
            }
            i++;
        }
    }

    static void cal(Pos pos){
        if(map[pos.x][pos.y]==0) map[pos.x][pos.y] = dice[3];
        else {
            dice[3] = map[pos.x][pos.y];
            map[pos.x][pos.y] = 0;
        }
    }

    static void East(){ dice = new int[]{0, dice[5], dice[2], dice[6], dice[4], dice[3], dice[1]}; }

    static void West(){ dice = new int[]{0, dice[6], dice[2], dice[5], dice[4], dice[1], dice[3]}; }

    static void North(){ dice = new int[]{0, dice[2], dice[3], dice[4], dice[1], dice[5], dice[6]}; }

    static void South(){ dice = new int[]{0, dice[4], dice[1], dice[2], dice[3], dice[5], dice[6]}; }

    static int parse(String s) { return Integer.parseInt(s); }
}

class Pos {
    int x; int y;

    public Pos(int x, int y){
        this.x = x;
        this.y = y;
    }
}
