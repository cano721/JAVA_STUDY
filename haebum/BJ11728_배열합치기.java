import java.io.*;
import java.util.*;

/*
    정렬되어있는 두 배열에 포인터를 하나 놔둬서 비교 후 출력
    포인터가 끝까지 갔다면 다른 배열 다 출력

*/

public class BJ11728_배열합치기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //배열 생성 및 담기
        int[] arr1 = new int[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr1[i] = Integer.parseInt(st2.nextToken());
        }

        int[] arr2 = new int[m];
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            arr2[i] = Integer.parseInt(st3.nextToken());
        }

        int one = 0;
        int two = 0;
        while(true){
            // 다 끝났으면 종료
            if(one == n && two == m) break;
            // 첫번째 배열은 다 출력했으면 두번째 배열 증가시키면서 출력
            else if(one == n){
                bw.write(arr2[two++] + " ");
            }
            // 현재 합이 m보다 작으면 end값을 더하고 다음 포인트로 이동
            else if(two == m){
                bw.write(arr1[one++] + " ");
            }
            else if(arr1[one] > arr2[two]){
                bw.write(arr2[two++] + " ");
            }else{
                bw.write(arr1[one++] + " ");
            }
        }
        bw.flush();
        bw.close();
    }
}
