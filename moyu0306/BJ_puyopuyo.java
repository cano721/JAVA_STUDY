import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];
        for(int i=0; i<12; i++){
            map[i]= br.readLine().toCharArray();
        }
        while(true){
            boolean flag = false;
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(map[i][j]!='.') flag |= mark(i,j,map[i][j]);
                }
            }
            gravity();
            if(flag) answer++;
            else break;
        }
        System.out.println(answer);

    }

    public static boolean mark(int a, int b, char c){
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[12][6];
        q.offer(new int[]{a,b});

        while(!q.isEmpty()){
            int[] point = q.poll();
            for(int i=0; i<4; i++){
                int posY = point[0]+dx[i];
                int posX = point[1]+dy[i];
                if(posY<0||posY>=12||posX<0||posX>=6||visited[posY][posX]) continue;
                if(map[posY][posX] == c){
                    count++;
                    visited[posY][posX] = true;
                    q.offer(new int[]{posY,posX});
                }

            }
        }
        if(count>=4){
            for(int i=0; i<12; i++){
                for(int j=0; j<6; j++){
                    if(visited[i][j]) map[i][j] ='.';
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public static void gravity(){
        for(int i=11; i>=0; i--){
            for(int j=0; j<6; j++){
                if(map[i][j]!='.'){
                    for(int k=i+1; k<12; k++){
                        if(map[k][j]!='.'){
                            char tmp = map[k-1][j];
                            map[k-1][j] = map[i][j];
                            map[i][j] = tmp;
                            break;
                        }else if(k==11){
                            map[k][j] = map[i][j];
                            map[i][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }