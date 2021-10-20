import java.util.*;
import java.io.*;

public class BJ1018_체스판다시칠하기 {

    public static boolean[][] arr;
    public static int answer = 64;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());

        arr = new boolean[height][width];


        // true false로 배열에 담기(w면 true b면 false)
        for(int i = 0; i < height; i++){
            String s = br.readLine();
            for(int j = 0; j <s.length(); j++){
                if(s.charAt(j) == 'W'){
                    arr[i][j] = true;
                }else{
                    arr[i][j] = false;
                }
            }
        }

        // 8*8을 자를 수 있는 칸까지 반복
        for(int i =0; i < height-7; i++){
            for(int j = 0; j < width-7; j++){
                //자른 8*8 배열 돌리면서 색칠체크
                check(i,j);
            }
        }

        System.out.println(answer);
        
    }

    // 색칠 체크하기
    public static void check(int i, int j){

        // 첫 시작 컬러 확인
        boolean start = arr[i][j];
        // 다시 색칠해야하는 갯수
        int temp = 0;
        for(int a = i; a < i+8; a++){
            for(int b = j; b < j+8; b++){

                // 색칠해야하는 것들 발견 시 ++
                if(arr[a][b] != start){
                    temp++;
                }
                // 열 바뀔때마다 변경
                start = !start;
            }
            // 행 바뀔때마다 변경
            start = !start;
        }

        // 시작 컬러를 반대라고 생각했을때는 64-temp
        int minTemp = Math.min(temp,64-temp);
        // 현재 정답과 비교
        answer = Math.min(answer,minTemp);
    }
}
