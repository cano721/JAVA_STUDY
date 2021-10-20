import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; //= new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(br.readLine());
        int weight[][] = new int[N][N];  
        
        for(int i=0;i<N;i++ ) {
            st= new StringTokenizer(br.readLine());
            for(int j=0;j<=1;j++) {
                weight[j][i]=Integer.parseInt(st.nextToken()); 
            }  
        }
        for(int i=0;i<N;i++) {
            int count=1;
            for(int j=0;j<N;j++) {
                if(weight[0][i]<weight[0][j]&&(weight[1][i]<weight[1][j])){
                    count++;
                }
            }
            System.out.print(count+" ");
        }
        }
}
