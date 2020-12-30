package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N1697 {
    static int MAX = 100000;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();

        boolean[] visited = new boolean[MAX+1];
        visited[N] = true;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{N, 0});

        while(!q.isEmpty()) {
            int[] n = q.poll();

            if(n[0] == K) {
                System.out.print(n[1]);
                break;
            }

            for(int i=0; i<3; i++) {
                int next = 0;
                if(i==0) {
                    next = n[0]+1;
                } else if(i==1) {
                    next = n[0]-1;
                } else {
                    next = n[0]*2;
                }

                if(next<0 || next>MAX || visited[next]) continue;
                visited[next] = true;
                q.add(new int[]{next, n[1]+1});
            }
        }
    }
}
