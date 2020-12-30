package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N1516 {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = parse(br.readLine());
        int[] in = new int[n+1];
        int[] time = new int[n+1];
        int[] cost = new int[n+1];
        LinkedList<Integer>[] edge = new LinkedList[n+1];
        for(int i=1; i<=n; i++) edge[i] = new LinkedList<>();

        for(int i=1; i<=n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = parse(st.nextToken());

            int e = parse(st.nextToken());
            while(e != -1){
                edge[e].add(i);
                in[i]++;

                e = parse(st.nextToken());
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(in[i] == 0) {
                System.out.print(i+" ");
                q.add(i);
                cost[i] = time[i];
            }
        }

        while(!q.isEmpty()){
            int num = q.poll();

            for(int e : edge[num] ){
                if(cost[e] < cost[num] + time[e]){
                    cost[e] = cost[num] + time[e];
                }
                if(--in[e] == 0) q.add(e);
            }
        }

        for(int c=1; c<=n; c++) System.out.println(cost[c]);
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
