package com.company.bakjoon;

import java.util.*;
import java.io.*;

public class N1003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tc = sc.nextInt();

        LinkedList<Tuple> fibo = new LinkedList<>();
        fibo.add(new Tuple(1, 0));
        fibo.add(new Tuple(0, 1));

        for(int i=2; i<41; i++){
            Tuple t1 = fibo.get(i-1);
            Tuple t2 = fibo.get(i-2);
            fibo.add(new Tuple(t1.zero+t2.zero, t1.one+t2.one));
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<tc; i++){
            int n = sc.nextInt();
            sb.append(fibo.get(n).zero + " " + fibo.get(n).one + "\n");
        }

        System.out.print(sb.toString());
    }

    static class Tuple{
        int zero; int one;
        public Tuple(int zero, int one){
            this.zero = zero;
            this.one = one;
        }
    }
}
