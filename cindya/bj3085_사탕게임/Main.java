package cindya.bj3085_사탕게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] board = new String[n][];
        int max = 0, rowCnt, colCnt;
        String rowColor, colColor;

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().split("");
        }
        br.close();


        // 바꾸지 않은 상태에서 가장 긴 구간을 확인
        for(int i = 0; i < n; i++){
            colCnt = rowCnt = 1;
            rowColor = board[i][0];
            colColor = board[0][i];
            for(int j = 1; j < n; j++) {
                // 가로 확인
                if(board[i][j].equals(rowColor)) rowCnt++;
                else{
                    rowCnt = 1;
                    rowColor = board[i][j];
                }
                max = Math.max(max, rowCnt);

                // 세로 확인
                if(board[j][i].equals(colColor)) colCnt++;
                else{
                    colCnt = 1;
                    colColor = board[j][i];
                }
                max = Math.max(max, colCnt);
            }
        }

        // 두 개씩 바꿔가며 영향이 있는 구간만 다시 확인
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n - 1; j++){
                String tmp, c1, c2, c3;
                int cnt1, cnt2, cnt3;

                // 가로 변경
                if(!board[i][j].equals(board[i][j + 1])){
                    // 점 두 개 교환
                    tmp = board[i][j];
                    board[i][j] = board[i][j + 1];
                    board[i][j + 1] = tmp;

                    c1 = board[i][0];
                    c2 = board[0][j];
                    c3 = board[0][j + 1];

                    cnt1 = cnt2 = cnt3 = 1;

                    for(int k = 1; k < n; k++){
                        // 바꾼 두 점을 포함하는 i행 확인
                        if(board[i][k].equals(c1)) cnt1++;
                        else{
                            cnt1 = 1;
                            c1 = board[i][k];
                        }
                        max = Math.max(max, cnt1);

                        // j열 확인
                        if(board[k][j].equals(c2)) cnt2++;
                        else{
                            cnt2 = 1;
                            c2 = board[k][j];
                        }
                        max = Math.max(max, cnt2);

                        // (j + 1)열 확인
                        if(board[k][j + 1].equals(c3)) cnt3++;
                        else{
                            cnt3 = 1;
                            c3 = board[k][j + 1];
                        }
                        max = Math.max(max, cnt3);
                    }

                    // 점 두 개 원위치
                    tmp = board[i][j];
                    board[i][j] = board[i][j + 1];
                    board[i][j + 1] = tmp;
                }

                // 세로 변경
                if(!board[j][i].equals(board[j + 1][i])){
                    // 점 두 개 교환
                    tmp = board[j][i];
                    board[j][i] = board[j + 1][i];
                    board[j + 1][i] = tmp;

                    c1 = board[0][i];
                    c2 = board[j][0];
                    c3 = board[j + 1][0];

                    cnt1 = cnt2 = cnt3 = 1;

                    for(int k = 1; k < n; k++){
                        // 바꾼 두 점을 포함하는 i열 확인
                        if(board[k][i].equals(c1)) cnt1++;
                        else{
                            cnt1 = 1;
                            c1 = board[k][i];
                        }
                        max = Math.max(max, cnt1);

                        // j행 확인
                        if(board[j][k].equals(c2)) cnt2++;
                        else{
                            cnt2 = 1;
                            c2 = board[j][k];
                        }
                        max = Math.max(max, cnt2);

                        // (j + 1)행 확인
                        if(board[j + 1][k].equals(c3)) cnt3++;
                        else{
                            cnt3 = 1;
                            c3 = board[j + 1][k];
                        }
                        max = Math.max(max, cnt3);
                    }

                    // 점 두개 원위치
                    tmp = board[j][i];
                    board[j][i] = board[j + 1][i];
                    board[j + 1][i] = tmp;
                }
            }
        }
        System.out.println(max);
    }
}
