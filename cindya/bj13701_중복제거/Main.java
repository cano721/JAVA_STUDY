package cindya.bj13701_중복제거;

import java.io.*;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[1<<20]; // 2^25 / 2^5 => 2^20만큼의 배열을 생성

        while (st.hasMoreTokens()){
            int a = Integer.parseInt(st.nextToken());
            int d = a / 32;
            int r = 1 << (a % 32);
            // int 하나가 32bit이므로 32개의 0이 있다고 생각하면
            // d번째 인덱스의 (a % 32)번째 자리를 1을 표시
            if((A[d]&r) == 0){ // A의 d번째 인덱스의 (a % 32)번째 수가 1이 아니면
                A[d] += r; // A[d]의 (a % 32)번째 수를 1로 바꾸고
                bw.write(a + " "); // 출력
            }
        }

        /*BitSet bs = new BitSet();

        while (st.hasMoreTokens()){
            int a = Integer.parseInt(st.nextToken());
            if(bs.get(a)) continue; // a가 bs에 없으면 넘어감
            // 있으면
            bs.set(a); // a를 저장하고
            bw.write(a + " "); // 출력
        }*/

        br.close();
        bw.close();
    }
}
