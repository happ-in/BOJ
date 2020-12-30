package BOJ.BOJ;

import java.util.*;
import java.io.*;

public class N1644 {
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        boolean[] isPrime = new boolean[N+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for(int i=2; i*i<=N; i++){
            if(isPrime[i]){
                for(int j=i*i; j<=N; j+=i)
                    isPrime[j] = false;
            }
        }

        ArrayList<Integer> arr = new ArrayList<>();
        for(int i=2; i<=N; i++) if(isPrime[i]) arr.add(i);

        int s=0; int e=0; int result=0; int cnt=0;
        while(s<=e){
            if(result == N) { cnt++; result -= arr.get(s++);}
            else if(result > N) result -= arr.get(s++);
            else if(e == arr.size()) break;
            else result += arr.get(e++);
        }

        System.out.print(cnt);
    }
}
