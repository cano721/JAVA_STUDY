/*
구현 - 나무재테크
*/
import java.io.*;
import java.util.*;
 
public class Main {
    static int arr[][], map[][];
    static int dx[] = {0,1,1,1,0,-1,-1,-1};
    static int dy[] = {-1,-1,0,1,1,1,0,-1};
    static ArrayList<Integer> list[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        arr = new int[n+1][n+1];
        map = new int[n+1][n+1];
        
        list = new ArrayList[n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                list[i][j] = new ArrayList<>();
                arr[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
                
            }  
        }
        
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            list[u][v].add(age);
        }
        
        int a = 0;
        while(a<K) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(list[i][j].size() > 0) {
                        int end = 0;
                        Collections.sort(list[i][j]);
                        
                        ArrayList<Integer> tmp = new ArrayList<>();
                        for(int k=0; k<list[i][j].size(); k++) {
                            int age = list[i][j].get(k);
                            if(map[i][j] >= age) {
                                map[i][j] -= age;
                                tmp.add(age+1);
                            }else {
                                end += age/2;
                            }
                        }
                        list[i][j] = new ArrayList<>();
                        for(int val : tmp)
                            list[i][j].add(val);
                        
                        map[i][j] += end;
                    }
                }
            }
            
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(list[i][j].size() > 0) {
                        
                        for(int k=0; k<list[i][j].size(); k++) {
                            int age = list[i][j].get(k);
                            if(age % 5 == 0) {
                                for(int d=0; d<8; d++) {
                                    int newX = j + dx[d];
                                    int newY = i + dy[d];
                                    if(0<newX && newX<=n && 0<newY && newY<=n) {
                                        list[newY][newX].add(1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    map[i][j] += arr[i][j];
            a++;
        }
        
        int answer = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                answer += list[i][j].size();
            }
        }
        System.out.println(answer);
    }
}
    
 