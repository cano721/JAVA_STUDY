
/**
 * 입력데이터 2^6
 * 
 * 
 * 
 * 1. 각 컴퓨터의 IP주소를 받기
 * 
 * 2. IP주소를 토대로 네트워크 주소와 네트워크 마스크 찾기
 * 
 * 비트마스크를 이용한 풀이
 * 
 * 네트워크주소     11010000
 * 마스크           11111000
 * ip주소          11010101
 * ip주소          11010001
 * ip주소          11010111
 * 각 IP주소의 교집합이 네트워크 주소
 * 
 * 각 IP주소의 합집합을 네트워크 주소와 XOR로 다른부분 찾은 후
 * NOT으로 반전 주기
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

        int[] netIp = new int[n];
        // 초기값
        // (1<<32를 하면 인트범위를 벗어난다. 인트범위 다 켜진 비트는 -1을 뜻함[32개])
        int netAddress = -1; // 교집합 구하기위해 전체 비트 켜놓기
        int netMask = 0; // 합집합 구하기 위해 전체 비트 꺼놓기

        //스트링 -> 인트 형변환 및 교집합,합집합 구하기
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
