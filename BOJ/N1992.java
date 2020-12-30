package BOJ.BOJ;

import java.io.*;

public class N1992 {
    static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = parse(br.readLine());
        char[][] arr = new char[N][N];

        for(int i=0; i<N; i++){
            arr[i] = br.readLine().toCharArray();
        }

        sb = new StringBuilder();
        dfs(arr, N);

        System.out.println(sb.toString());
    }

    static void dfs(char[][] arr, int num){
        if(isSame(arr)) {
            sb.append(arr[0][0]);
            return;
        }

        int half = num/2;
        char[][] left_top = new char[half][half];
        char[][] right_top = new char[half][half];
        char[][] left_bottom = new char[half][half];
        char[][] right_bottom = new char[half][half];

        for(int i=0; i<num; i++){
            for(int j=0; j<num; j++){
                if(i>=0 && i<half && j>=0 && j<half) left_top[i][j] = arr[i][j];
                else if(i>=0 && i<half && j>=half && j<num) right_top[i][j-half] = arr[i][j];
                else if(i>=half && i<num && j>=0 && j<half) left_bottom[i-half][j] = arr[i][j];
                else right_bottom[i-half][j-half] = arr[i][j];
            }
        }

        sb.append("(");

        appendSb(left_top, half);
        appendSb(right_top, half);
        appendSb(left_bottom, half);
        appendSb(right_bottom, half);

        sb.append(")");

    }

    static void appendSb(char[][] arr, int val){
        if(isSame(arr)){
            sb.append(arr[0][0]);
        } else {
            dfs(arr, val);
        }
    }

    static boolean isSame(char[][] arr){
        char ch = arr[0][0];

        for(char[] arr1 : arr){
            for(char arr2 : arr1){
                if(ch != arr2) return false;
            }
        }

        return true;
    }

    static int parse(String s) { return Integer.parseInt(s); }
}
