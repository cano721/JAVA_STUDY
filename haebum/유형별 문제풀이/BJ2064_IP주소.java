
/**
 * 
 * 
 * 
 * 입력데이터 2^6
 * 
 * 비트마스크를 이용한 풀이
 * 
 * 1. IP주소 전처리로 인트 형 변환 및 배열 저장
 * 
 * 2. IP주소들로 네트워크 마스크 찾기
 * 
 * 3. IP주소와 네트워크 마스크의 교집합으로 네트워크 주소 찾기
 * 
 * 4. 찾은 마스크와 주소를 전처리하여 String형태로 변환 출력
 * 
 * 
 * 2번 설명
 * 
 * IP주소들의 최상단 비트(MSB)를 확인하면서 내려가며,
 * 서로 다른부분이 있다면 0. 다 같다면 1로 체크.
 * 
 * 3번 설명
 * 
 * 위의 방식대로 서브넷 마스크를 구했다면,
 * 사용하는 부분인 호스트 영역만 0이고 나머진 다 1
 * 
 * 그리고 ip주소와의 교집합을 구하면 
 * 호스트 영역은 0으로만 남고 ip주소의 네트워크 주소가 남게 된다.
 * 
 */
import java.util.*;
import java.io.*;

public class BJ2064_IP주소 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        //IP주소 저장 배열
        int[] netIp = new int[n];

        // 초기값
        int netAddress = 0;
        int netMask = 0;

        //IP주소 인트 형변환 및 저장
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(),".");
            int temp = 0;
            for(int j = 0; j < 4; j++){
                int m = Integer.parseInt(st.nextToken());
                // 기존꺼 8칸 밀기
                temp <<= 8;
                // 현재꺼 더하기
                temp += m;
            }
            // 구한 ip 저장
            netIp[i] = temp;
        }


        // 넷마스크 구하기
        for(int i = 31; i >= 0; i--){
            int bit = 1<<i;
            boolean check = false;

            for(int j = 1; j < n; j++){
                if((netIp[0] & bit) != (netIp[j] & bit)){
                    check = true;
                    break;
                }
            }

            if(check){
                break;
            }else{
                netMask |= bit;
            }
        }
        
        // // 넷 주소 구하기
        netAddress = netIp[0]&netMask;

        //인트 - 스트링 형변환
        String address = "";
        String mask = "";

        // 각 파트 별 숫자 찾기 변수
        int check = (1<<8)-1; // 다켜진 비트집합

        int k = 3; //제일 위의 파트부터 구하면서 내려올것.
        while(true){
            int anum = netAddress>>(8*k) & check;
            int mnum = netMask>>(8*k) & check;

            address += String.valueOf(anum);
            mask += String.valueOf(mnum);

            netAddress &= ((1<<(8*k))-1); //8*k번째 원소 왼쪽은 다 0으로 만들어주는 식
            netMask &= ((1<<(8*k))-1);
            k--;

            if(k == -1){
                break;
            }
            address += ".";
            mask += ".";
        }

        System.out.println(address);
        System.out.println(mask);
    }
}
