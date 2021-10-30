/*
    재귀로 구현
    옮기는 함수를 만들고 말 그대로 구현해볼 예정

    실패..

    https://st-lab.tistory.com/96
    블로그 참조함

*/


import java.util.*;
import java.io.*;

public class BJ11729_하노이탑이동순서 {
    
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int n = Integer.parseInt(br.readLine());

        bw.write((int)Math.pow(2,n)-1 + "\n");
        move(n,1,2,3);
        bw.flush();
        bw.close();
    }


    public static void move(int n,int start,int mid, int end) throws IOException{

        if(n == 1){
            bw.write(start + " " + end + "\n");
            return;
        }

        //n-1개를 미드로 옮기기
        move(n-1,start,end,mid);

        //1개 옮기기
        bw.write(start + " " + end + "\n");

        //n-1개 다시 이동
        move(n-1,mid,start,end);
    }
}
