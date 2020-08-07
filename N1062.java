package com.company.bakjoon;

import java.io.*;
import java.util.*;

public class N1062 {
    static int N, K;
    static String[] arr;
    static boolean[] alphabet = new boolean[26];
    static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 5;

        if(K<0) {
            System.out.print(0);
            return;
        }

        arr = new String[N];
        for(int i=0; i<N; i++) {
            String s = br.readLine();
            s = s.replace("anta", "");
            s = s.replaceAll("tica", "");
            arr[i] = s;
        }

        Arrays.fill(alphabet, false);
        char[] set = new char[] {'a', 'n', 't', 'i', 'c'};
        for(char c : set) alphabet[c - 'a'] = true;

        dfs(0, 0);

        System.out.print(ans);
    }

    static void dfs(int idx, int depth){
        if(depth == K) { check(); return; }

        for(int i=idx; i<26; i++){
            if(alphabet[i]) continue;
            alphabet[i] = true;
            dfs(i+1, depth+1);
            alphabet[i] = false;
        }
    }

    static void check(){
        int count = 0;

        for(String str : arr){
            boolean tf = true;

            for(int i=0; i<str.length(); i++){
                if(alphabet[str.charAt(i) - 'a']) continue;
                tf = false; break;
            }

            if(tf) count++;
        }

        ans = Math.max(ans, count);
    }
}
