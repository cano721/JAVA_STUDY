
/**
 * 입력값 500만
 * 입력데이터 2^25
 * 
 * 실패함 ㅠ
 * 
 * 비트마스킹 풀이
 * 
 * 1. 비트셋 활용 풀이
 * 
 * 입력값을 받으면서,
 * 현재 데이터가 비트셋에 없었으면 비트셋 표기 및 출력
 * 있었으면 패스
 * 
 * 2. 배열 + 비트 마스크
 * 
 * 배열을 비트셋같이 쓸거임 arr
 * 
 * int 자료형은 2^32가 최대이므로, 비트로 32까지 표기가능
 * 
 * 배열의 크기는 입력데이터가 2^25까지였으므로, 32(2^5)로 나눈 2^20으로 지정
 * 
 * 숫자 a가 들어오면
 * a / 32 = 몫,a % 32 = 나머지 하여 저장
 * arr[몫] = 나머지(int 형태지만 31까지를 체크할 수 있는 배열로 쓸거임)
 * 
 * 입력값이 들어올때마다 해당하는 몫과 나머지가 기존에 있었는지 체크
 * 있었으면 패스 없었다면 저장하고 출력
 */
import java.util.*;
import java.io.*;

public class BJ13701_중복제거 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        // // 1번 비트셋 풀이
        // BitSet bitSet= new BitSet();
        // st = new StringTokenizer(br.readLine());
        // while(st.hasMoreTokens()){
        //     int a = Integer.parseInt(st.nextToken());
            // if(!bitSet.get(a)){
        //         bitSet.set(a);
        //         bw.write(a + " ");
        //     }
        // }
        // bw.flush();
        // bw.close();


        // 2번 배열 + 비트마스킹 풀이
        int[] number = new int[1<<20];

        st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()){
            int a = Integer.parseInt(st.nextToken());
            int x = a / 32;
            int y = a % 32;
            // 해당 몫과 나머지가 기존에 있었는지 체크
            if((number[x] & (1<<y)) != (1<<y)){
                // 해당 몫 위치에 나머지 원소 저장
                number[x] |= (1<<y);
                bw.write(a +" ");
            }
        }
        bw.flush();
        bw.close();
    }
}
