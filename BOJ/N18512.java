package BOJ.BOJ;

import java.io.*;
import java.util.*;

public class N18512 {

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/com/company/test.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Scanner sc = new Scanner(System.in);

        int jump_A = sc.nextInt();
        int jump_B = sc.nextInt();
        int pos_A = sc.nextInt();
        int pos_B = sc.nextInt();

        while(pos_A <= 1000000) {
            if(pos_A == pos_B) {
                System.out.print(pos_A);
                return;
            } else if(pos_A < pos_B) {
                pos_A += jump_A;
            } else {
                pos_B += jump_B;
            }
        }

        System.out.print("-1");
    }
}
