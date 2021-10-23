/*
    F4 = F3+F2 이 형태이므로
    F4 = (F2+F1) + (F1+F0) 으로 가능
    F4 = ((F1+F0)+F1) + (F1+F0)
    F4 = ((1+0)+1) + (1+0)
    F4 = 3

    F를 함수로 만들고

    F(0) = 0
    F(1) = 1 로 종단 조건을 설정한 후

    종단조건에서 종료가 안나면
    F(n) = f(n-1) + f(n-2)로 설정해두면

    재귀를 돌면서 다 더해지고 정답이 나옴
*/

import java.io.*;
import java.util.*;

public class BJ10870_파보나치수5 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        System.out.println(fibo(n));
    }

    public static int fibo(int num){

        // 종단조건
        if(num == 0) return 0;
        if(num == 1) return 1;

        // 재귀 돌리기
        return fibo(num-1)+fibo(num-2);
    }
}