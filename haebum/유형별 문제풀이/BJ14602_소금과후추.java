/*
    윈도우의 범위만큼 돌수 있는 i,j를 지정하여 for문 생성

    그 안에서 윈도우 범위만큼 for문을 돌면서 리스트에 원소 담기.
    원소 정렬하여 중앙값 출력.(최악 n^2)

    총 4중 for문

    30*30*30*30 3^4*10^4 대략 10^6 안에 풀림;
    
*/

import java.util.*;
import java.io.*;

public class BJ14602_소금과후추 {

    public static int m,n,k,w,m_index;
    public static int[][] arr = new int[31][31];
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        
        // 배열담기
        for(int i = 0; i < m; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        // 중앙값
        m_index = (w*w)/2;

        // 윈도우만큼 돌 수 있는 범위
        for(int i = 0; i < m-w+1; i++){
            for(int j = 0; j < n-w+1; j++){
                solve(i,j);
            }
            // 줄 바꾸기
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    //슬라이딩 윈도우만큼 돌면서 중앙값 찾기 함수
    public static void solve(int x, int y) throws IOException{
        // 중앙값 출력하기 위한 리스트 생성
        LinkedList<Integer> list = new LinkedList<>();

        // 윈도우만큼 돌기
        for(int a = x; a < x+w; a++){
            for(int b = y; b < y+w; b++){
                list.add(arr[a][b]);
            }
        }

        // 리스트 정렬
        Collections.sort(list);
        
        // 중앙값 출력
        bw.write(list.get(m_index)+" ");
    }
}
