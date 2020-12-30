package BOJ.BOJ;

import java.util.*;

public class N2292 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int answer = 1;
        long val = 1; int depth = 1;
        long start = 1; long end = 1;
        while(true) {
            if(N>=start && N<=end) {
                System.out.print(answer);
                return;
            }

            val = 6*depth;
            start = end+1;
            end += val;

            depth++; answer++;
        }
    }
}
