package BOJ.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N1039 {
    static Queue<int[]> q = new LinkedList<>();
    static int N, K;
    static boolean[][] chk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chk = new boolean[1000001][K+1];
        chk[N][0] = true;

        q.add(new int[]{N, 0});
        int result = -1; int len = (int) (Math.log10(N)+1);
        while(!q.isEmpty()){
            int[] n = q.poll();

            if(n[1] == K) {
                result = Math.max(result, n[0]);
                continue;
            }

            for(int i=0; i<len-1; i++){
                for(int j=i+1; j<len; j++){
                    int swapNum = swap(n[0], i, j);
                    if(swapNum == -1 || chk[swapNum][n[1]+1]) continue;
                    chk[swapNum][n[1]+1] = true;
                    q.add(new int[]{swapNum, n[1]+1});
                }
            }
        }

        System.out.print(result);
    }

    static int swap(int val, int x, int y) {
        char[] arr = ("" + val).toCharArray();

        if(x==0 && arr[y]=='0') return -1;

        char tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;

        String ans = "";
        for (char c : arr) ans += c;

        return Integer.parseInt(ans);
    }
}
