package BOJ.BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class N16637 {
    static int N;
    static int ans = Integer.MIN_VALUE;
    static int[] nums;
    static char[] ops;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = parse(br.readLine());
        ops = new char[N/2];
        nums = new int[N/2 + 1];

        String s = br.readLine();
        int x=0; int y=0;
        for(int i=0; i<N; i++){
            if(i%2==0) nums[x++] = parse(s.substring(i,i+1));
            else ops[y++] = s.charAt(i);
        }

        dfs(0, nums[0]);
        System.out.print(ans);
    }

    static int cal(int a, int b, char op){
        if(op == '+') return a+b;
        else if (op == '-') return a-b;
        else return a*b;
    }

    static void dfs(int idx, int result){
        if(idx>=N/2) {
            ans = Math.max(ans, result);
            return;
        }

        // 괄호 X
        int tmp = cal(result, nums[idx+1], ops[idx]);
        dfs(idx+1, tmp);

        // 뒤에 오는 수에 괄호
        if(idx<N/2-1) {
            tmp = cal(result, cal(nums[idx+1], nums[idx+2], ops[idx+1]), ops[idx]);
            dfs(idx+2, tmp);
        }
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
