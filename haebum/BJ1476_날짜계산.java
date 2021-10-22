/*
    완전탐색으로 풀이
    1년부터 시작해서 년도를 1년씩 증가시키고
    그에따른 E,S,M 을 1씩 증가시키면서 범위 초과 시 1로 초기화
    원하는 E,S,M 과 동일할시에 년도 출력

*/


import java.util.*;
import java.io.*;

public class BJ1476_날짜계산 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int year = 1;
        int a= 1,b = 1,c = 1;

        while(true){
            if(a == e && s == b && m == c){
                System.out.println(year);
                break;
            }
            year++;
            a++;b++;c++;
            if(a == 16) a = 1;
            if(b == 29) b = 1;
            if(c == 20) c = 1;
        }
    }

}
