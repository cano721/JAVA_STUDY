/**
 * 입력데이터 2^6
 * 
 * 
 * 비트마스크를 이용한 풀이
 * 
 * 64로 나눠서 나올 수 있는 막대는
 * 64 32 16 8 4 2 1
 * 
 * 결국 비트단위임
 * 
 * 숫자를 비트로 봤을때 저런 막대들의 조합이 됨
 * 
 * bitCount로 쓰인 숫자(막대)개수 체크
 * 출력
 */

import java.util.*;
import java.io.*;

public class BJ1094_막대기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int x = Integer.parseInt(br.readLine());

        System.out.println(Integer.bitCount(x));
    }
}
