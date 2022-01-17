import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int max;
    static int house;
    static int[][] map;
    static int[][] arr; //방 확인용
    static boolean[][] visited;
    static Queue<Point> q = new LinkedList<>();
    static ArrayList<Integer> maxRoom= new ArrayList<>();
    static int dx[] = {0,-1,0,1};
    static int dy[] = {-1,0,1,0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        arr = new int[N+1][M+1];
        visited = new boolean[N+1][M+1];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        house = solve1();
        System.out.println(house);
        System.out.println(max);
        if(house == M * N) {
            System.out.println(2);
        }
        else {
            System.out.println(solve2());
        }
        // solve2();
    }
    static public int solve1() {

        int room = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (visited[i][j])
                    continue;

                room++;
                visited[i][j] = true;
                q.add(new Point(i,j));

                int size = 0; //방 크기

                while(!q.isEmpty()){

                    Point temp = q.poll();
                    int x = temp.x;
                    int y = temp.y;

                    size++;
                    arr[x][y] = room; //방 번호 메모

                    int val = map[x][y];

                    int[] wall = {val & 1, val &2, val &4, val & 8};

                    for(int k=0;k<4;k++){
                        int nx = x+dx[k];
                        int ny = y +dy[k];

                        if(nx < 0 || nx >= N || ny < 0 || ny >= M|| visited[nx][ny] || wall[k] != 0)
                            continue;

                        visited[nx][ny]=true;
                        q.add(new Point(nx,ny));

                    }
                }
                maxRoom.add(size);
                max = Math.max(max,size);
            }
        }
        return room+1;
    }
    static public int solve2() {
//        for (int i = 0; i < N; i++) {
//            System.out.println();
//            for (int j = 0; j < M; j++) {
//                System.out.print(arr[i][j]);
//
//            }
//        }
        visited = new boolean[N+1][M+1];
        int max2 = 0;

        for(int k=0;k<house;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<M;j++){
                    if(arr[i][j]!=k ||visited[i][j])
                        continue;

                    visited[i][j]=true;
                    q.add(new Point(i,j));

                    while (!q.isEmpty()){
                        Point temp = q.poll();
                        int x = temp.x;
                        int y = temp.y;

                        for(int dir = 0;dir<4;dir++){
                            int nx = x+dx[dir];
                            int ny = y+dy[dir];

                            if(nx < 0 || nx >= N || ny < 0 || ny >= M|| visited[nx][ny])
                                continue;

                            if(arr[nx][ny] != k) {
                                max2 = Math.max(max2, maxRoom.get(k) + maxRoom.get(arr[nx][ny]));
                                continue;
                            }
                            visited[nx][ny] = true;
                            q.add(new Point(nx,ny));
                        }
                    }
                }

            }
        }
        return max2;
    }
    static class Point {
        int x,y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
