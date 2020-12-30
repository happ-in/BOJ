package BOJ.BOJ;

import java.io.*;
import java.util.*;

/**
 * 2sec, 512MB
 * 축구 모인 총 인원 N명 -> 스타트 vs 링크 (4<= N <=20, 짝수)
 * 스타트, 링크 팀 능력치 차이 최솟값을 출력
 */

public class N14889 {

    static int N, start, link;
    static int[][] S;
    static int min = Integer.MAX_VALUE;
    static boolean[] team;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());
        S = new int[N][N];

        // 값 받아오기
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){ S[i][j] = parse(st.nextToken()); }
        }

        team = new boolean[N];

        combination(0, 0);

        System.out.print(min);
    }

    static void combination(int idx, int cnt){
        if(cnt == N/2){ cal(); return; }
        for(int i=idx; i<N; i++){
            team[i] = true;
            combination(i+1, cnt+1);
            team[i] = false;
        }
    }

    static void cal(){
        // false : link, true : start
        start = 0; link = 0;

        for(int i=0; i<N; i++){
            if(team[i]) {
                for(int j=i+1; j<N; j++) if(team[j]) start += (S[i][j] + S[j][i]);
            }
            else {
                for(int j=i+1; j<N; j++) if(!team[j]) link += (S[i][j] + S[j][i]);
            }
        }

        min = Math.min(min, Math.abs(link-start));
    }

    static int parse(String s){
        return Integer.parseInt(s);
    }

}
