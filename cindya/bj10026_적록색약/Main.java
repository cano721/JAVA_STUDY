package cindya.bj10026_적록색약;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[][] original = new String[n][n], changed = new String[n][n];
        int orgCnt = 0, chgCnt = 0;

        // original : 원래 배열, changed : R을 전부 G로 바꾼 배열
        for(int i = 0; i < n; i++){
            String nl = br.readLine();
            original[i] = nl.split("");
            changed[i] = nl.replaceAll("R", "G").split("");
        }
        br.close();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // original 확인
                String c = original[i][j];
                if(!c.equals(" ")) {
                    orgCnt++;
                    original[i][j] = " ";
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});

                    // 같은 색깔인 구역은 다 q에 넣고 확인
                    // q에 들어간 구역은 전부 " "로 값을 바꿔서 다시 확인하지 않도록 함
                    while(!q.isEmpty()){
                        int[] coordi = q.poll();
                        int x = coordi[0], y = coordi[1];

                        // 위
                        if(x > 0 && c.equals(original[x - 1][y])){
                            q.add(new int[]{x - 1, y});
                            original[x - 1][y] = " ";
                        }
                        // 아래
                        if(x < n - 1 && c.equals(original[x + 1][y])){
                            q.add(new int[]{x + 1, y});
                            original[x + 1][y] = " ";
                        }
                        // 왼쪽
                        if(y > 0 && c.equals(original[x][y - 1])){
                            q.add(new int[]{x, y - 1});
                            original[x][y - 1] = " ";
                        }
                        // 오른쪽
                        if(y < n - 1 && c.equals(original[x][y + 1])){
                            q.add(new int[]{x, y + 1});
                            original[x][y + 1] = " ";
                        }
                    }
                }

                // chenged 확인
                c = changed[i][j];
                if(!c.equals(" ")) {
                    chgCnt++;
                    changed[i][j] = " ";
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});

                    // 같은 색깔인 구역은 다 q에 넣고 확인
                    // q에 들어간 구역은 전부 " "로 값을 바꿔서 다시 확인하지 않도록 함
                    while(!q.isEmpty()){
                        int[] coordi = q.poll();
                        int x = coordi[0], y = coordi[1];

                        // 위
                        if(x > 0 && c.equals(changed[x - 1][y])){
                            q.add(new int[]{x - 1, y});
                            changed[x - 1][y] = " ";
                        }
                        // 아래
                        if(x < n - 1 && c.equals(changed[x + 1][y])){
                            q.add(new int[]{x + 1, y});
                            changed[x + 1][y] = " ";
                        }
                        // 왼쪽
                        if(y > 0 && c.equals(changed[x][y - 1])){
                            q.add(new int[]{x, y - 1});
                            changed[x][y - 1] = " ";
                        }
                        // 오른쪽
                        if(y < n - 1 && c.equals(changed[x][y + 1])){
                            q.add(new int[]{x, y + 1});
                            changed[x][y + 1] = " ";
                        }
                    }
                }
            }
        }

        System.out.println(orgCnt + " " + chgCnt);
    }
}
