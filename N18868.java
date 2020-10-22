package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N18868 {

    static int N, M;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = parse(st.nextToken());
        int N = parse(st.nextToken());

        int[][] planets = new int[M][N];
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) planets[i][j] = parse(st.nextToken());
        }

        int[][][] compare = new int[M][N][N];
        for(int x=0; x<M; x++){
            for(int i=0; i<N; i++){
                for(int j=i+1; j<N; j++){
                    compare[x][i][j] = compareTwoNum(planets[x][i], planets[x][j]);
                }
            }
        }

        int count = 0;
        for(int x1=0; x1<M; x1++){
            for(int x2=x1+1; x2<M; x2++){
                boolean flag = true;
                for(int i=0; i<N; i++){
                    for(int j=i+1; j<N; j++){
                        if(compare[x1][i][j]!=compare[x2][i][j]){
                            flag = false;
                            break;
                        }
                    }
                    if(!flag) break;
                }
                if(flag) count++;
            }
        }

        System.out.print(count);
    }

    static int compareTwoNum(int a, int b){
        if(a<b) return -1;
        else if(a==b) return 0;
        else return 1;
    }

    static int parse(String s){ return Integer.parseInt(s); }
}
