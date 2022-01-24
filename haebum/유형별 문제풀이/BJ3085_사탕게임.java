/*
    1. 2차원배열을 만들어서 그 안에 사탕 담기
    2. 2차원배열을 돌면서 우,하방향 사탕 색상 다를 시 변경
    3. 변경한 상태에서 행방향,열방향 같은 색상 사탕 체크
    4. 현재 정답과 비교하여 맥스값으로 변경
    5. 체크한 후엔 다시 사탕 원상태로 변경하거나 새로운 맵에서 변경
        1) deepcopy를 이용한 새로운 맵 생성 후에 변경 -> 2차원이므로 메소드 작성해서 써야함..
        2) 변경했던걸 다시 원상태 코드 작성 -> 채택


    시간복잡도는 50*50*50*50
*/

import java.util.*;
import java.io.*;

public class BJ3085_사탕게임 {

    public static int n,answer = 0;
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 측정한 높이의 수
        n = Integer.parseInt(br.readLine());

        
        // 사탕 배열에 담기
        char[][] arr = new char[n][n];

        for(int i = 0; i < n; i++){
            String input = br.readLine();
            for(int j = 0; j < n; j++){
                arr[i][j] = input.charAt(j);
            }
        }

        //변경 안했을 때 돌면서 체크
        check(arr);

        // 사탕배열 돌면서 색상 다를 시 변경
        for(int i = 0; i < n-1; i++){
            for(int j =0; j < n; j++){
                // 열방향 변경
                if(arr[i][j] != arr[i+1][j]){
                    char temp = arr[i][j];
                    arr[i][j] = arr[i+1][j];
                    arr[i+1][j] = temp;
                    // 변경 후 같은 사탕 개수 체크
                    check(arr);
                    // 원상복구
                    temp = arr[i+1][j];
                    arr[i+1][j] = arr[i][j];
                    arr[i][j] = temp;
                }
                // 행방향 변경
                if(arr[j][i] != arr[j][i+1]){
                    char temp = arr[j][i];
                    arr[j][i] = arr[j][i+1];
                    arr[j][i+1] = temp;
                    // 변경 후 같은 사탕 개수 체크
                    check(arr);
                    //원상복구
                    temp = arr[j][i+1];
                    arr[j][i+1] = arr[j][i];
                    arr[j][i] = temp;

                }
            }
        }
        // 정답 출력
        System.out.println(answer);
    }


    public static void check(char[][] checkarr){
        for(int x = 0; x < n; x++){
            // 같은 사탕의 개수(행,열)
            int cnt = 1, cnt2 = 1;
            for(int y = 0; y <n-1; y++){
                // 행방향 체크
                if(checkarr[x][y] == checkarr[x][y+1]) cnt++;
                // 다르면 초기화
                else {
                    cnt = 1;
                }
                // 맥스값으로 정답 변경
                answer = Math.max(answer,cnt);
                // 열방향 체크
                if(checkarr[y][x] == checkarr[y+1][x]) cnt2++;
                else{
                    cnt2 = 1;
                }
                answer = Math.max(answer,cnt2);
            }
        }
        
    }
}

