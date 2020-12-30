package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N13458 {

    static int N;
    static long B, C;
    static long[] A;
    static long answer;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/com/company/test.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());
        A = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            A[i] = parse(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        B = parse(st.nextToken());
        C = parse(st.nextToken());

        answer = N;
        for(int i=0; i<N; i++){
            // 총감독이 감시 가능한 인원 제외
            A[i] = A[i] - B;
            if(A[i]<=0) continue;

            // 필요한 부감독 인원 체크
            long tmp = A[i] / C; answer += tmp;
            A[i] = A[i]-(tmp*C);
            if(A[i]<=C) answer++;
        }

        System.out.print(answer);
    }

    private static int parse(java.lang.String s) {
        return Integer.parseInt(s);
    }
}
