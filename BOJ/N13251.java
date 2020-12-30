package BOJ.BOJ;

import java.util.*;

public class N13251 {
    static int M, K;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();

        arr = new int[M];
        int total = 0;
        for(int i=0; i<M; i++) { arr[i] = sc.nextInt(); total += arr[i];}

        K = sc.nextInt();

        double bottom = 1;
        for(int i=total; i>total-K; i--) bottom = bottom * i;

        double top = 0;
        for(int i=0; i<M; i++){
            if(arr[i]<K) continue;
            double tmp = 1;
            for(int j=arr[i]; j>arr[i]-K; j--) tmp *= j;

            top += tmp;
        }

        System.out.print(top/bottom);
    }
}
