import java.io.*;
import java.util.*;

public class Main {

    static int N, M, map[][], min, virusNum;
    static ArrayList<Virus> virusList; 
    static int[] selected;
    static Queue<Virus> q; 
    static boolean[][] visited;
    static int[][] d = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

    static private class Virus { 
        int x, y;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        virusList = new ArrayList<>();
        q = new LinkedList<>();
        min = 2501; 

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) { 
                    virusList.add(new Virus(i, j));
                }
            }
        }

        virusNum = virusList.size(); 
        selected = new int[M]; 
        getMVirus(0, 0); 

        if (min == 2501) { 
            bw.write(-1 + "\n");
        } else {
            bw.write(min + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void getMVirus(int cnt, int start) { 
        if (cnt == M) { 
            min = Math.min(min, spreadVirus(selected)); 
            return;
        }

        for (int i = start; i < virusNum; i++) {
            selected[cnt] = i;
            getMVirus(cnt + 1, i + 1);
        }
    }

    private static int spreadVirus(int[] selected) { 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }
        q.clear(); 

        for (int i = 0; i < M; i++) {
            Virus vv = virusList.get(selected[i]);
            visited[vv.x][vv.y] = true;
            q.add(vv);
        }

        int cnt = 0; 
        int size = 0;
        int blanks = 0; 
        while (!q.isEmpty()) {
            boolean countUp = false; 

            if (cnt > min) {
                return 2501;
            }

            size = q.size(); 
            for (int j = 0; j < size; j++) { 
                Virus cur = q.poll();
                visited[cur.x][cur.y] = true;

                for (int i = 0; i < 4; i++) {
                    int dx = cur.x + d[i][0];
                    int dy = cur.y + d[i][1];
                    if (dx >= 0 && dx < N && dy >= 0 && dy < N && !visited[dx][dy]) {
                        if (map[dx][dy] == 0) {  
                            visited[dx][dy] = true;
                            q.add(new Virus(dx, dy));
                            countUp = true; 
                        } else if (map[dx][dy] == 2) { 
                            visited[dx][dy] = true;
                            q.add(new Virus(dx, dy));
                        }
                    }
                }
            }

            if (!countUp) { 
                blanks++;
            } else { 
                cnt = cnt + blanks + 1;
                blanks = 0;
            }
        }

        for (int i = 0; i < N; i++) { 
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 0) {
                    return 2501;
                }
            }
        }

        return cnt; 
    }
}