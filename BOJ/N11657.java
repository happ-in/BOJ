package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N11657 {
    static int INF = 5000001;
    static int N, M;
    static Bus[] bus;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = parse(st.nextToken()); M = parse(st.nextToken());
        bus = new Bus[M];

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int f = parse(st.nextToken());
            int t = parse(st.nextToken());
            int c = parse(st.nextToken());

            bus[i] = new Bus(f, t, c);
        }

        long[] dist = new long[N+1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        for(int i=1; i<N; i++){
            for(int j=0; j<M; j++){
                if(dist[bus[j].from] == INF) continue;
                dist[bus[j].to] = Math.min(dist[bus[j].to],
                        dist[bus[j].from] + bus[j].cost);
            }
        }

        boolean cycle = false;
        for(int i=0; i<M; i++){
            if(dist[bus[i].from] == INF) continue;
            if(dist[bus[i].from] + bus[i].cost < dist[bus[i].to]){
                cycle = true;
                break;
            }
        }

        if(cycle) System.out.print("-1");
        else{
            for(int i=2; i<=N; i++) System.out.println( dist[i]==INF ? "-1" : dist[i]);
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }

    static class Bus{
        int from, to, cost;
        public Bus(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}
