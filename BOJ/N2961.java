package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N2961 {
    static int N;
    static long min = Long.MAX_VALUE;

    static long[][] taste;
    static boolean[] visited;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        taste = new long[N][2];
        for(int i=0; i<N; i++) {
            taste[i][0] = sc.nextLong();
            taste[i][1] = sc.nextLong();
        }

        visited = new boolean[N];
        dfs(1, 0, 0);
        System.out.print(min);
    }

    static void dfs(long sour, long bitter, int idx) {
        if(bitter!=0) { min = Math.min(min, Math.abs(sour-bitter)); }
        if(idx==N) { return; }

        visited[idx] = true;
        dfs(sour*taste[idx][0], bitter+taste[idx][1], idx+1);

        visited[idx] = false;
        dfs(sour, bitter, idx+1);
    }
}
