package com.company.bakjoon;

import java.io.*;

public class N2941 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        s = s.replaceAll("c=", "c");
        s = s.replaceAll("c-", "c");
        s = s.replaceAll("dz=", "d");
        s = s.replaceAll("d-", "d");
        s = s.replaceAll("lj", "l");
        s = s.replaceAll("nj", "n");
        s = s.replaceAll("s=", "s");
        s = s.replaceAll("z=", "z");

        System.out.print(s.length());
    }
}
