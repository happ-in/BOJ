package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N2493 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Pos> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            int n = Integer.parseInt(st.nextToken());
            while(!stack.isEmpty()){
                if(stack.peek().height >= n){
                    sb.append(stack.peek().num+" ");
                    break;
                }
                stack.pop();
            }

            if(stack.isEmpty()) sb.append("0 ");
            stack.add(new Pos(n, i));
        }

        System.out.print(sb.toString());
    }

    static class Pos{
        int height; int num;

        public Pos(int height, int num){
            this.height = height;
            this.num = num;
        }
    }
}
