import java.io.*;
import java.util.*;

class Main{
    static int[][] arr;
    static int n,m,k,w,mid;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
    
        mid = (w*w)/2;
        arr = new int[m][n];
        
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        
        for(int i = 0; i < m-w+1; i++){
            for(int j = 0; j < n-w+1; j++){
                median(i,j);
                
            }

             bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    
    public static void median(int x, int y) throws IOException{
        
        List<Integer> list = new ArrayList<>();
        
  
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + w; j++) {
                list.add(arr[i][j]);
            }
        }

        Collections.sort(list);

        bw.write(list.get(mid)+" ");
    }

}
